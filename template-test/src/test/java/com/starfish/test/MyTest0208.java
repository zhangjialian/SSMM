package com.starfish.test;

import com.starfish.manager.TestManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTest0208 extends BaseTest{

    @Autowired
    private TestManager testManager;

    @Test
    public void test() throws Exception {
        testManager.test1();
    }
}
