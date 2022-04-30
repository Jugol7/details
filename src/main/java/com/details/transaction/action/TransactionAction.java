package com.details.transaction.action;

import com.details.entity.Student;
import com.details.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务操作
 *
 * @author zlp
 * @date 2022/04/17
 */
@RestController
@Slf4j
@RequestMapping("/transaction")
public class TransactionAction {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/check")
    public Student check(@RequestBody Student student){
        return student;
    }
}
