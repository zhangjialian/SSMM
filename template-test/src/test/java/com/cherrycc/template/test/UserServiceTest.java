package com.cherrycc.template.test;

import com.cherrycc.template.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zjf on 2017/12/7.
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void test(){
        System.out.println(userService);
    }

}
