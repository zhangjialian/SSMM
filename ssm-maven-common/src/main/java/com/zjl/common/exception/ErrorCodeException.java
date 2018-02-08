package com.zjl.common.exception;


import com.zjl.common.systemEnum.ErrorCodeEnum;

/**
 * 错误码异常
 * 
 * @author anling.xlj
 *
 */
public class ErrorCodeException extends Exception {

	private static final long serialVersionUID = -2396422934408894887L;

	private ErrorCodeEnum errorCode;

	/**
	 * 带错误码的构造函数
	 * 
	 * @param errorCode
	 */
	public ErrorCodeException(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获取错误码
	 * 
	 * @return
	 */
	public ErrorCodeEnum getErrorEnum() {
		return errorCode;
	}

}
