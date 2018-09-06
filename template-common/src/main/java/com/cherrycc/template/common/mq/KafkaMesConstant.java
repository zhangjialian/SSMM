package com.cherrycc.template.common.mq;

/**
 * kafka常量
 * @author BG349176
 * @date 2018/9/6 18:13
 */
public class KafkaMesConstant {

    public static final String SUCCESS_CODE = "00000";
    public static final String SUCCESS_MES = "成功";

    /*kakfa-code*/
    public static final String KAFKA_SEND_ERROR_CODE = "30001";
    public static final String KAFKA_NO_RESULT_CODE = "30002";
    public static final String KAFKA_NO_OFFSET_CODE = "30003";

    /*kakfa-mes*/
    public static final String KAFKA_SEND_ERROR_MES = "发送消息超时,联系相关技术人员";
    public static final String KAFKA_NO_RESULT_MES = "未查询到返回结果,联系相关技术人员";
    public static final String KAFKA_NO_OFFSET_MES = "未查到返回数据的offset,联系相关技术人员";

}
