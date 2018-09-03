package com.starfish.test;

import com.starfish.manager.UserManager;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zjf on 2017/12/7.
 */
public class UserManagerTest extends BaseTest {

    @Resource
    private UserManager userManager;

    @Test
    public void test(){
        System.out.println(userManager);
    }

}
