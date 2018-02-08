package com.zjl.common.excel;

import java.io.Serializable;

/**
 * 提交身份证信息的错误信息
 * 
 * @since 1.0
 * @version 2016年5月9日 下午5:21:02
 * @author lcc
 */

public class RowErrorInfo implements Serializable {

	private static final long serialVersionUID = 4193629436092146618L;
	/**
	 * 出错的行号
	 */
	private int rowNo;

	/**
	 * 错误信息
	 */
	private String errorInfo;

	public RowErrorInfo() {
	}

	public RowErrorInfo(int rowNo, String errorInfo) {
		this.rowNo = rowNo;
		this.errorInfo = errorInfo;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
