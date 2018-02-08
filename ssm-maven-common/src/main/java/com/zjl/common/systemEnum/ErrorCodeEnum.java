package com.zjl.common.systemEnum;

/**
 * �����붨��
 * 
 * @author anling.xlj
 * 
 */
public enum ErrorCodeEnum {
	//ϵͳ����
	P01("P01", "��������"),

	//��¼
	LG01("LG01", "�û�δ��¼"),
	LG02("LG02", "userIdΪ0����¼ʧ��"),
	LG03("LG03", "��¼ʧ�ܣ�����id�Ҳ�����Ӧ�û�"),

	//�û�
	UR01("UR01", "�û����Ѵ��ڣ�����ʧ��"),

	//Excel�ļ�����
	FI01("FI01", "excel��ȡ����")
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