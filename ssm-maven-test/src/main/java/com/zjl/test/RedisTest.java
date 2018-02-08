package com.zjl.test;

import com.zjl.common.util.RedisUtil;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

public class RedisTest extends BaseTest {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;


    @Test
    public void test(){



    }

}
