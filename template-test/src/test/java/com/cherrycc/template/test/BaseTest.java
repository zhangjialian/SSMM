package com.cherrycc.template.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author BG349176
 * @date 2018/8/21 10:36
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-test.xml"})
public class BaseTest {

    @Autowired
    private DataSourceTransactionManager txManager;

    // TODO: 2018/10/17 为什么存储transDefinition时事务回滚不起作用
    private ThreadLocal<TransactionStatus> tsLocal = new ThreadLocal<>();

    @Before
    public void beforeTest(){
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        //开启新事务
        transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transStatus = txManager.getTransaction(transDefinition);
        tsLocal.set(transStatus);
    }

    @After
    public void afterTest(){
        txManager.rollback(tsLocal.get());
    }

}
