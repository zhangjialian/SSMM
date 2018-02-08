package com.zjl.common.response;

import com.zjl.common.systemEnum.ErrorCodeEnum;

/**
 * ��Ӧʧ�ܵ�����
 * @param <T>
 */
public class ErrorResponse<T> extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706330384328182339L;


	/**
	 * �������
	 */
	private String errorCode;

	/**
	 * ���˵��
	 */
	private String errorDesc;
	
	/*****************************
	 *��Ҫ���صĽ��
	 */
	private T result;
	
	
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setErrorCode(ErrorCodeEnum errorCodeEnum) {
		super.setSuccess(false);
		setErrorCode(errorCodeEnum.getCode());
		setErrorDesc(errorCodeEnum.getDesc());
	}

	public ErrorResponse() {
		super.setSuccess(false);
	}
	
	public ErrorResponse(ErrorCodeEnum errorCodeEnum) {
		super.setSuccess(false);
		setErrorCode(errorCodeEnum.getCode());
		setErrorDesc(errorCodeEnum.getDesc());
	}
	
	
}