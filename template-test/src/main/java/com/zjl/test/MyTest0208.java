package com.zjl.test;

import com.zjl.manager.TestManager;
import org.junit.Test;

import javax.annotation.Resource;

public class MyTest0208 extends BaseTest{

    @Resource
    private TestManager testManager;

    @Test
    public void test() throws Exception {
        testManager.insertTest();
    }
}
