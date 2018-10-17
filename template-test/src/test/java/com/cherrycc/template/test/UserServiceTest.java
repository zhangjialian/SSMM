package com.cherrycc.template.test;

import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.service.UserService;
import com.cherrycc.template.utils.MockTestUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zjf on 2017/12/7.
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void test() throws Exception{
        UserBO userBO = MockTestUtils.getJavaBean(UserBO.class);
        int i = userService.insertUser(userBO);
        Assert.assertNotEquals(0, i > 0);
    }

}
