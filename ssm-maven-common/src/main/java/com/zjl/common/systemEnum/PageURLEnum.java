package com.zjl.common.systemEnum;

/**
 * ����ҳ��url
 * 
 * @author anling.xlj
 * 
 */
public enum PageURLEnum {
	LOGIN("LOGIN", "�û���¼", "/login/index"),
	SUBMIT_LOGIN("SUBMIT_LOGIN", "�ύ��¼", "/login/submitLogin"),
	LOGOUT("LOGOUT", "�˳���¼", "/login/logout"),
	ADMIN_INDEX("ADMIN_INDEX", "ϵͳ��ҳ", "/user/getUserList")
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