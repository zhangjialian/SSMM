package com.zjl.common.response;

import java.io.Serializable;


public class BaseResult implements Serializable {

	private static final long serialVersionUID = 8292053430594967895L;

	private boolean success = true;		// �����Ƿ�ɹ�
	
	/**
	 * ��ǰ�����Ƿ�ɹ�
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * ���ò������
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	
	
}
