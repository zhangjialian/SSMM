package com.zjl.common.systemEnum;

/**
 * 常用页面url
 * 
 * @author anling.xlj
 * 
 */
public enum SessionEnum {
	JSESSIONID("JSESSIONID", "用户登录cookie标志"),
	LOGIN_USER("LOGIN_USER", "登录的用户"),
	REQUEST_UTL("REQUEST_UTL", "重定向前请求的路由")
	;

	private String code;
	private String desc;

	SessionEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}