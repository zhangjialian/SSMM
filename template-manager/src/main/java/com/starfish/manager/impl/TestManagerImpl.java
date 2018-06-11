package com.starfish.manager.impl;

import com.starfish.common.user.UserBO;
import com.starfish.dao.UserDao;
import com.starfish.manager.TestManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestManagerImpl implements TestManager {

    @Resource
    private UserDao userDao;

    /**
     * 测试连续两次新增的情况下，锁的作用范围
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransaction() throws Exception {
        UserBO userBO = userDao.getUserById(1);

        userBO.setName("张哈哈");
        System.out.println(userBO);

        userDao.updateUserById(userBO);
        System.out.println(12312);
    }

    @Override
    public void insertTest() throws Exception {
        System.out.println("--insertTest--");
    }

}
