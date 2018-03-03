package com.zjl.test;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class RedisTest extends BaseTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void test(){
        System.out.println(redisTemplate);
    }

    @Test
    public void insert(){
        redisTemplate.opsForValue().set("username", "zhangjialian");
        System.out.println("insert success");
    }

    @Test
    public void get(){
        Object obj = redisTemplate.opsForValue().get("username");
        System.out.println(obj);
    }

    @Test
    public void delete(){
        // TODO: 2018/3/3
    }

}
