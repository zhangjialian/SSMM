package com.starfish.test;

import com.starfish.manager.TestManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BG349176
 * @date 2018/8/14 17:03
 */
public class Mytest0814 extends BaseTest {

    @Autowired
    private TestManager testManager;

    @Test
    public void test1() throws Exception {
        testManager.insertTest();
    }

}
