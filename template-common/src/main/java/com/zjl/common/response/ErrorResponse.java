package com.zjl.common.response;

import com.zjl.common.systemEnum.ErrorCodeEnum;

/**
 * 响应失败的请求
 * @param <T>
 */
public class ErrorResponse<T> extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706330384328182339L;


	/**
	 * 错误代码
	 */
	private String errorCode;

	/**
	 * 结果说明
	 */
	private String errorDesc;
	
	/*****************************
	 *需要返回的结果
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