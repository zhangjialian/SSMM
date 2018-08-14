package com.starfish.common.systemEnum;

/**
 * @author BG349176
 * @date 2018/8/8 10:22
 */
public enum MyTestEnum {

    /**
     *
     */
    T01("测试1"),
    T02("测试2"),
    ;

    private String desc;

    MyTestEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
