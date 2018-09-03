package com.starfish.test;

import com.starfish.bo.UserBO;
import org.junit.Test;

import java.io.*;

public class MyTest0116{

    private String fileUrl = "/Users/zhangjialian/test1.txt";

    @Test
    public void test() throws Exception{

        UserBO userBO = new UserBO();
        userBO.setEmail("123456@qq.com");
        this.saveToFile(userBO, this.fileUrl);

    }

    @Test
    public void test2() throws Exception{
        UserBO userBO = this.readFromFile(this.fileUrl);
        System.out.println(userBO);
    }

    private void saveToFile(UserBO userBO, String fileUrl) throws Exception{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(fileUrl)));
        outputStream.writeObject(userBO);
    }

    private UserBO readFromFile(String fileUrl) throws Exception{
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(fileUrl)));
        UserBO userBO = (UserBO) inputStream.readObject();
        return userBO;
    }

}
