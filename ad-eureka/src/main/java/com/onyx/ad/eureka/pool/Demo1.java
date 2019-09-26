package com.onyx.ad.eureka.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @Description:
 * @date 2019-09-26 8:58
 */
public class Demo1 {
    public static void main(String[] args) {
        int corePoolSize = 5;
        int maxNumPoolSize = 10;
        long keepAliveTime = 5;
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxNumPoolSize, keepAliveTime, TimeUnit.SECONDS, deque);
        /**
         * 默认使用的是  new AbortPolicy()
         * AbortPolicy - 丢弃任务，并抛出拒绝执行 RejectedExecutionException 异常信息。线程池默认的拒绝策略。
         * 必须处理好抛出的异常，否则会打断当前的执行流程，影响后续的任务执行。
         *
         * executor.execute（）提交任务，由于会抛出 RuntimeException，如果没有try.catch处理异常信息的话，会中断调用者的处理流程，
         * 后续任务得不到执行（跑不完100个）。可自行测试下，很容易在控制台console中能查看到。
         */


        /**
         * CallerRunsPolicy - 当触发拒绝策略，只要线程池没有关闭的话，则使用调用线程直接运行任务。一般并发比较小，性能要求不高，不允许失败。
         * 但是，由于调用者自己运行任务，如果任务提交速度过快，可能导致程序阻塞，性能效率上必然的损失较大
         *
         * 运行后，在控制台console中能够看到的是，会有一部分的数据打印，显示的是 “main is running”，也即体现调用线程处理。
         */
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        /**
         * DiscardPolicy - 直接丢弃，其他啥都没有
         *
         * 直接丢弃任务，实际运行中，打印出的信息不会有100条。
         */
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        /**
         *  DiscardOldestPolicy -  当触发拒绝策略，只要线程池没有关闭的话，丢弃阻塞队列 workQueue 中最老的一个任务，并将新任务加入
         *
         *  实际运行，打印出的信息也会少于100条。
         */
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.setRejectedExecutionHandler(new MyRejectedPolicy());


        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
            });
        }
        executor.shutdown();
    }


    /**
     * 自定义拒绝策略
     * <p>
     * 只运行前5个任务,其他的不运行了
     *
     * 注意这里面的线程池没有关闭
     */
    public static class MyRejectedPolicy implements RejectedExecutionHandler {
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(10);
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, deque);

        private AtomicInteger num = new AtomicInteger(0);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (num.getAndIncrement() < 5) {
                executor2.execute(r);
            } else {
                throw new RejectedExecutionException("线程任务已经满了...");
            }
        }
    }


}
