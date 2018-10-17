package com.cherrycc.template.test;

import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.dao.UserDAO;
import com.cherrycc.template.model.UserDO;
import com.cherrycc.template.utils.MockTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * @author BG349176
 * @date 2018/8/21 10:36
 */
public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DataSourceTransactionManager txManager;
    private ThreadLocal<TransactionStatus> tsLocal = new ThreadLocal<>();

    @Test
    public void test1(){
        String username = "zhangjialian";
        String email = "835397999@qq.com";

        UserDO userDO = userDAO.queryByUserNameAndEmail(username, email);
        Assert.assertNotNull(userDO);
    }

    @Test
    public void test2(){
        System.out.println("test2" + Thread.currentThread().getName());
        UserBO userBO = MockTestUtils.getJavaBean(UserBO.class);
        int i = userDAO.insertUser(userBO);
        Assert.assertNotEquals(0, i > 0);
    }



}
