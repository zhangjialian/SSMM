package com.zjl.common.systemEnum;

/**
 * ����ҳ��url
 * 
 * @author anling.xlj
 * 
 */
public enum SessionEnum {
	JSESSIONID("JSESSIONID", "�û���¼cookie��־"),
	LOGIN_USER("LOGIN_USER", "��¼���û�"),
	REQUEST_UTL("REQUEST_UTL", "�ض���ǰ�����·��")
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