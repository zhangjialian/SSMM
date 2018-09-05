package com.cherrycc.template.test;

import com.alibaba.fastjson.JSON;
import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.utils.MockTestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends BaseTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void test(){
        System.out.println(redisTemplate);
    }

    @Test
    public void insert(){
        UserBO userBO = MockTestUtils.getJavaBean(UserBO.class);
        redisTemplate.opsForValue().set("loginUser", JSON.toJSONString(userBO));
        System.out.println("insert success");
    }

    @Test
    public void get(){
        String userJSON = (String) redisTemplate.opsForValue().get("username");
        System.out.println(userJSON);
    }

    @Test
    public void delete(){
        // TODO: 2018/3/3
    }

}
