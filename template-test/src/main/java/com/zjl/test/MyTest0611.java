package com.zjl.test;

import com.zjl.common.user.UserBO;
import org.junit.Test;

public class MyTest0611 {

    @Test
    public void test(){
        System.out.println("begin");
        try{
            UserBO userBO = null;
            userBO.getUsername();
        } catch (NullPointerException e){
            System.out.println("null point exception");
        } catch (Exception e){
            System.out.println("exception");
        }
        System.out.println("end");
    }

}
