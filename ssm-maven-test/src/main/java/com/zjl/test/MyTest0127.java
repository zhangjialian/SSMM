package com.zjl.test;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

public class MyTest0127 extends BaseTest{

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    public void test() throws Exception{
        System.out.println(taskExecutor);

        int[] count = {0};

        for(int i = 0; i < 50; i++){
            taskExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": begin");
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": end");
                count[0]++;
            });
        }

        Thread.sleep(5 * 500);
        System.out.println();
    }

}



