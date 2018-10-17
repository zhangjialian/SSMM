package com.cherrycc.template.test;

import com.cherrycc.template.utils.AesUtil;

public class AESTest {

    private static final String KEY = "besttestbesttest";

    public static void main(String[] args) {
        String plainText = "10450783652";
        //加密
        String encText = AesUtil.aesEncrypt(KEY, plainText);
        //将加密的结果转化为16进制
        System.out.println(encText);
        String code16 = AesUtil.parseByte2HexString(encText.getBytes());
        System.out.println(code16);
        String code2 = new String(AesUtil.parseHexStr2Byte(code16));
        System.out.println(code2);
    }

    //@Test
    public void test1(){
        String plainText = "800012343234";
        //加密
        String encText = AesUtil.aesEncrypt(KEY, plainText);
        //将加密的结果转化为16进制
        System.out.println(AesUtil.parseByte2HexString(encText.getBytes()));
    }
}


