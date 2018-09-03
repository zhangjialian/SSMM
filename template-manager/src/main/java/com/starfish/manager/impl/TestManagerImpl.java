package com.starfish.manager.impl;

import com.alibaba.fastjson.JSON;
import com.starfish.bo.UserBO;
import com.starfish.dao.UserDAO;
import com.starfish.manager.TestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestManagerImpl implements TestManager {

    @Autowired
    private UserDAO userDAO;

    /**
     * 测试连续两次新增的情况下，锁的作用范围
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test1() throws Exception {
        Thread.sleep(1000);
        UserBO userBO = userDAO.getUserById(1);
        System.out.println(JSON.toJSONString(userBO));

        userBO.setName("张哈哈");
        userDAO.updateUserById(userBO);
    }

}
