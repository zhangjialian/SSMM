package com.starfish.test;

import com.starfish.bo.UserBO;
import org.junit.Test;

public class MyTest0107 {

    @Test
    public void test(){

        try{
            UserBO userBO = null;
            System.out.println(userBO.getName());
        }catch (Exception e){
            System.out.println("exception");
        }

    }

    private int getData(){
        int i = 0;
        try{
            i = 1;
            return i;
        } catch (Exception e){
            //
        } finally {
            i = 2;
        }
        return i;
    }

    private UserBO getUserDO(){
        UserBO userBO = new UserBO();
        try{
            userBO.setName("123123");
            return userBO;
        } catch (Exception e){
            //
        } finally {
            userBO.setName("345345");
        }
        return userBO;
    }

}
