package com.starfish.test;

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
            taskExecutor.execute(new Thread());
        }

        int type = 5;

        Thread.sleep(5 * 500);
        System.out.println();
    }

}



