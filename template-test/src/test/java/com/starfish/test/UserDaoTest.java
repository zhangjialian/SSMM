package com.starfish.test;

import com.starfish.dao.UserDAO;
import com.starfish.model.UserDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BG349176
 * @date 2018/8/21 10:36
 */
public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void test1(){
        String username = "zhangjialian";
        String email = "835397999@qq.com";

        UserDO userDO = userDAO.queryByUserNameAndEmail(username, email);
        Assert.assertNotNull(userDO);
    }

}
