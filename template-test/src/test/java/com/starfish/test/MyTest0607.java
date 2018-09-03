package com.starfish.test;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class MyTest0607 {

    public static void main(String[] args) {
        Map<Integer, String> userId2NameMap = new HashMap<>();
        userId2NameMap.put(1, "zhangsan");
        userId2NameMap.put(2, "lisi");
        userId2NameMap.put(3, "wangwu");
        System.out.println(JSON.toJSONString(userId2NameMap));
    }

}
