package com.zjl.manager.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorHandle implements RejectedExecutionHandler {

    Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorHandle.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            Thread.sleep(100);
            executor.execute(r);
        } catch (InterruptedException e) {
            logger.error("level0@重新添加新的任务到线程池失败, e=", e);
        } finally {
            logger.info("info@重新添加新的任务到线程池结束");
        }
    }

}
