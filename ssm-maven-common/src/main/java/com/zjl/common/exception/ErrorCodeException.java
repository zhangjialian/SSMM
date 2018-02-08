package com.zjl.common.exception;


import com.zjl.common.systemEnum.ErrorCodeEnum;

/**
 * �������쳣
 * 
 * @author anling.xlj
 *
 */
public class ErrorCodeException extends Exception {

	private static final long serialVersionUID = -2396422934408894887L;

	private ErrorCodeEnum errorCode;

	/**
	 * ��������Ĺ��캯��
	 * 
	 * @param errorCode
	 */
	public ErrorCodeException(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public ErrorCodeEnum getErrorEnum() {
		return errorCode;
	}

}
