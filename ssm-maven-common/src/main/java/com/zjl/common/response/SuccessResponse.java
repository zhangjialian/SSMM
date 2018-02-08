package com.zjl.common.response;

/**
 * ��Ӧ�ɹ�������
 * @param <T>
 */
public class SuccessResponse<T> extends BaseResult {

	private static final long serialVersionUID = -6939354078506119260L;

	/**
	 * �ɹ����
	 */
	private T result;

	private int pageSize=0;
	
	private int pageNo=0;
	
	/**
	 * ��������
	 */
	private long count=0;
	
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	
	
	
}