package com.starfish.test;

import com.alibaba.fastjson.JSON;
import com.starfish.common.user.UserDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author BG349176
 * @date 2018/8/1 14:04
 */
public class MyTest0801 {

    @Test
    public void test1(){
        List<UserDO> userList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            UserDO userDO = new UserDO();
            userDO.setId(i);
            userDO.setName("hahaha" + i);
            userList.add(userDO);
        }
        System.out.println(JSON.toJSONString(userList));

        userList.get(3).setId(1);

        userList = new ArrayList<>();
        Map<Integer, String> userId2UserNameMap = userList.stream().collect(
                Collectors.toMap(UserDO::getId, UserDO::getName, (key1, key2) -> key2)
        );
        System.out.println(JSON.toJSONString(userId2UserNameMap));
    }

}
