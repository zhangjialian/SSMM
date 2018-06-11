package com.starfish.common.response;

import java.io.Serializable;


public class BaseResult implements Serializable {

	private static final long serialVersionUID = 8292053430594967895L;

	private boolean success = true;		// 操作是否成功
	
	/**
	 * 当前操作是否成功
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置操作结果
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	
	
}
