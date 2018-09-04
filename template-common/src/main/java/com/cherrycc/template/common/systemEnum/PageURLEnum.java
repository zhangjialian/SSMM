package com.cherrycc.template.common.systemEnum;

/**
 * 常用页面url
 * 
 * @author anling.xlj
 * 
 */
public enum PageURLEnum {
	LOGIN("LOGIN", "用户登录", "/login/index"),
	SUBMIT_LOGIN("SUBMIT_LOGIN", "提交登录", "/login/submitLogin"),
	LOGOUT("LOGOUT", "退出登录", "/login/logout"),
	ADMIN_INDEX("ADMIN_INDEX", "系统主页", "/user/getUserList")
	;

	private String code;
	private String desc;
	private String url;

	PageURLEnum(String code, String desc, String url) {
		this.code = code;
		this.desc = desc;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}