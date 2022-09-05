package com.details.thread.communication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ：zlp
 * @date ：2022/9/5 15:22
 * @version: 1.0
 */
public class KillDemo {

    /**
     * 启动十个用户线程
     * 库存六个
     * 生成一个合并队列
     * 每个用户拿到自己的请求响应
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        KillDemo demo = new KillDemo();
        demo.mergeJob();
        Thread.sleep(3000);
        List<Future<Result>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final Long orderId = i + 10L;
            final Long userId = Long.valueOf(i);
            Future<Result> submit = executorService.submit(() -> {
                countDownLatch.countDown();
                return demo.operateCount(new UserRequestVo(orderId, userId, 1));
            });
            futures.add(submit);
        }
        futures.forEach(f -> {
            try {
                Result result = f.get(300, TimeUnit.MICROSECONDS);
                System.out.println(Thread.currentThread().getName() + "客户端请求响应" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 模拟数据库行
     */
    private Integer stock = 10;

    private BlockingDeque<RequestPromise> blockingDeque = new LinkedBlockingDeque<>(10);

    public Result operateCount(UserRequestVo userRequestVo) throws InterruptedException {
        // 阈值判断
        // 队列创建
        RequestPromise requestPromise = new RequestPromise(userRequestVo);
        synchronized (requestPromise) {
            // begin 把这几行代码放在同步块中，保证入队列的是获取这个对象的锁之后
            boolean offer = blockingDeque.offer(requestPromise, 100, TimeUnit.MICROSECONDS);
            if (!offer) {
                return new Result(false, "系统繁忙");
            }
            // end
            try {
                // 释放锁之前先获取锁
                requestPromise.wait(200);
                if(requestPromise.getResult() == null){
                    return new Result(false, "等待超时");
                }
            } catch (InterruptedException e) {
                // 打印日志信息
                e.printStackTrace();
            }
        }
        return requestPromise.getResult();
    }

    public void mergeJob() {
        new Thread(() -> {
            List<RequestPromise> list = new ArrayList<>();
            while (true) {
                if (blockingDeque.isEmpty()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                // 数据过多，消费慢于生产，会内存溢出
                /*while (blockingDeque.peek() != null) {
                    list.add(blockingDeque.poll());
                }*/
                int size = blockingDeque.size();
                for (int i = 0; i < size; i++) {
                    list.add(blockingDeque.poll());
                }
                System.out.println(Thread.currentThread().getName() + " 合并扣减库存：" + list.size());
                int sum = list.stream().mapToInt(e -> e.getUserRequestVo().getCount()).sum();
                // 不复杂的情况下  考虑两种情况
                if (sum <= stock) {
                    stock -= sum;
                    list.forEach(l -> {
                        l.setResult(new Result(true, "成功1"));
                        synchronized (l) {
                            l.notify();
                        }
                    });
                    continue;
                }
                // 总的库存不够的情况
                list.forEach(l -> {
                    Integer count = l.getUserRequestVo().getCount();
                    if (count <= stock) {
                        stock -= count;
                        l.setResult(new Result(true, "成功2"));
                    } else {
                        l.setResult(new Result(false, "库存不够"));
                    }
                    synchronized (l) {
                        l.notify();
                    }
                });
                list.clear();
            }
        }, "mergeJob").start();
    }

}

@Data
@AllArgsConstructor
@ToString
class RequestPromise {
    private Result result;
    private UserRequestVo userRequestVo;

    public RequestPromise(UserRequestVo userRequestVo) {
        this.userRequestVo = userRequestVo;
    }
}

@Data
@AllArgsConstructor
@ToString
class Result {
    private boolean success;
    private String msg;
}

@Data
@AllArgsConstructor
@ToString
class UserRequestVo {
    private Long orderId;

    private Long userId;

    /**
     * 扣减数量
     */
    private Integer count;
}
