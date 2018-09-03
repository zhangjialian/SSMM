package com.starfish.test;

import com.alibaba.fastjson.JSON;
import com.starfish.bo.UserBO;
import com.starfish.dao.UserDAO;
import com.starfish.manager.TestManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BG349176
 * @date 2018/8/14 17:03
 */
public class Mytest0814 extends BaseTest {

    @Autowired
    private TestManager testManager;

    @Autowired
    private UserDAO userDAO;

    private static final Logger logger = LoggerFactory.getLogger(Mytest0814.class);

    @Test
    public void test1() throws Exception {
        UserBO userBO = userDAO.getUserById(1);
        logger.info("info@test1, userBO={}", JSON.toJSONString(userBO));
    }

}
