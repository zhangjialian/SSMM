package com.zjl.common.excel;

import java.io.Serializable;

/**
 * �ύ���֤��Ϣ�Ĵ�����Ϣ
 * 
 * @since 1.0
 * @version 2016��5��9�� ����5:21:02
 * @author lcc
 */

public class RowErrorInfo implements Serializable {

	private static final long serialVersionUID = 4193629436092146618L;
	/**
	 * ������к�
	 */
	private int rowNo;

	/**
	 * ������Ϣ
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
