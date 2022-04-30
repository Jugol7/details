package com.details.transaction.service.impl;

import com.details.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 事务服务impl
 *
 * @author zlp
 * @date 2022/04/17
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    /**
     * 注入自己  有点奇怪  可从外部调用
     */
    @Autowired
    private TransactionServiceImpl self;

    /**
     * 一个公共方法供Controller调用，内部调用事务性
     * 在多层事务下（通过事务传播机制处理），可能希望子事务不影响主事务
     * 可在主事务捕获异常，来处理是否回滚事务
     */
    @Transactional(rollbackFor = Exception.class)
    public int createUserWrong1(String name) {
        try {
            // 使用代理类才生效，使用的CGLIB
            self.createUserPrivate();
        } catch (Exception ex) {
            // 可手动回滚
            log.error("create user failed because {}", ex.getMessage());
        }
        return 1;
    }

    /**
     * 标记了@Transactional的public方法才生效
     * 指定回滚的异常，可查看TransactionAspectSupport中的invokeWithinTransaction方法
     * 			try {
     * 				// This is an around advice: Invoke the next interceptor in the chain.
     * 				// This will normally result in a target object being invoked.
     * 				retVal = invocation.proceedWithInvocation();
     *                        }
     * 			catch (Throwable ex) {
     * 				// target invocation exception
     * 				completeTransactionAfterThrowing(txInfo, ex);
     * 				throw ex;
     *            }
     * 			finally {
     * 				cleanupTransactionInfo(txInfo);
     *            }
     * 			commitTransactionAfterReturning(txInfo);
     * 			return retVal;
     * 默认情况下出现 RuntimeException（非受检异常）或者Error的时候，Spring才回滚事务
     * 可查看DefaultTransactionAttribute中rollbackOn
     * 注释中表示 受检异常一般是业务异常，出现这种情况业务还是能完成，不会主动回滚；而Error与 RuntimeException非受检异常 代表非预期的结果，主动回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void createUserPrivate() throws IOException {
        log.info("create user failed because");
        self.otherTask();
    }

    //因为文件不存在，一定会抛出一个IOException
    private void otherTask() throws IOException {
        Files.readAllLines(Paths.get(""));
    }

}

