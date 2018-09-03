package com.starfish.test;

import com.starfish.manager.TestManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author BG349176
 * @date 2018/8/20 16:16
 */

public class MyTest20180820 extends BaseTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-test.xml");

        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ac.getBean(ThreadPoolTaskExecutor.class);
        TestManager testManager = ac.getBean(TestManager.class);

        taskExecutor.submit(() -> {
            try {
                testManager.test1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
