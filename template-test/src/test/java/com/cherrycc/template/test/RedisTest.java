package com.cherrycc.template.test;

import com.alibaba.fastjson.JSON;
import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.utils.MockTestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.Set;

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
        String userJSON = redisTemplate.opsForValue().get("loginUser");
        System.out.println(userJSON);
    }

    @Test
    public void delete(){
        String key = "username";
        redisTemplate.delete(key);
    }

    /**
     * 根据文档
     *  redis除了语义出错以外，遇到其它错误时不会回滚已执行命令
     *  用redisTemplate测试后发现事务能正常回滚，待确定
     */
    //@Test
    public void transactionTest(){
        Object o = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisTemplate.multi();
                redisTemplate.opsForValue().set("testRedisMulti", "11");
                redisTemplate.opsForValue().get("testRedisMulti");
                if(true){
                    throw new RuntimeException();
                }
                redisTemplate.opsForValue().set("testRedisMulti", "22");
                redisTemplate.opsForValue().get("testRedisMulti");
                Object rs = redisTemplate.exec();
                return rs;
            }
        }.execute(redisTemplate);

        System.out.println(o);
    }

    @Test
    public void showKeys(){
        Set<String> keySet = redisTemplate.keys("*");
        System.out.println(JSON.toJSONString(keySet));
    }

}
