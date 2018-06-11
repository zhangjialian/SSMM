package com.zjl.test;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyTest0117 {

    @Test
    public void test(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new MyCallable());
        try {
            System.out.println(future.get());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}

class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "12312123";
    }
}


class MyThread extends Thread{

}