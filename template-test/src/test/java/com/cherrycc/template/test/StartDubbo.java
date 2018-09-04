package com.cherrycc.template.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class StartDubbo extends BaseTest{

    @Before
    public void before(){
        System.out.println("-------------- before dubbo start: --------------");
    }

    @Test
    public void test() throws IOException{
        System.out.println("-------------- dubbo started: --------------");
        System.in.read();
    }

    @After
    public void after(){
        System.out.println("-------------- after dubbo start: --------------");
    }

}
