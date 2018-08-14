package com.starfish.common.systemEnum;

/**
 * 错误码定义
 * 
 * @author anling.xlj
 * 
 */
public enum ErrorCodeEnum {
	B01("B01", "业务异常"),

	//系统级别
	P01("P01", "参数错误"),

	//登录
	LG01("LG01", "用户未登录"),
	LG02("LG02", "userId为0，登录失败"),
	LG03("LG03", "登录失败，根据id找不到对应用户"),

	MQ01("MQ01", "消息发送失败"),

	//用户
	UR01("UR01", "用户名已存在，新增失败"),

	//Excel文件导入
	FI01("FI01", "excel读取出错")
	;


	private String code;
	private String desc;

	ErrorCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}