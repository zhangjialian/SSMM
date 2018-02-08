package com.zjl.common.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ruhnn业务处理结果集
 * 
 * @since 1.0
 * @version 2016年3月21日 下午6:51:31
 * @author zhangby5
 */
public class ResponseResult<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7239094706144266342L;

	/**
	 * 执行成功的列表
	 */
	private List<E> successList;

	/**
	 * 执行失败的列表
	 */
	private List<E> faileList;

	/**
	 * 失败原因
	 */
	private String failedReason;

	/**
	 * 构造器<strong>默认非线程安全</strong>
	 */
	public ResponseResult() {
		super();
		this.successList = new ArrayList<E>();
		this.faileList = new ArrayList<E>();
	}

	/**
	 * 构造器
	 *
	 * @param sync
	 *            是否构造线程安全的结果集<br>
	 *            true:<strong>线程安全</strong><br>
	 *            false:<strong>非线程安全</strong>
	 */
	public ResponseResult(boolean sync) {
		if (sync) {
			this.successList = Collections.synchronizedList(new ArrayList<E>());
			this.faileList = Collections.synchronizedList(new ArrayList<E>());
		} else {
			this.successList = new ArrayList<E>();
			this.faileList = new ArrayList<E>();
		}

	}

	/**
	 * 增加结果集
	 * 
	 * @param result
	 */
	public void addBaseResult(ResponseResult<E> result) {
		this.getSuccessList().addAll(result.getSuccessList());
		this.getFaileList().addAll(result.getFaileList());
	}

	/**
	 * 添加成功结果集
	 * 
	 * @param successList
	 */
	public void addSuccessItems(List<E> successList) {
		if (this.getSuccessList() == null) {
			this.successList = new ArrayList<E>();
		}
		this.getSuccessList().addAll(successList);
	}

	/**
	 * 添加失败结果集
	 * 
	 * @param failedList
	 */
	public void addFaileItems(List<E> failedList) {
		if (this.getFaileList() == null) {
			this.faileList = new ArrayList<E>();
		}
		this.getFaileList().addAll(failedList);
	}

	/**
	 * 移除成功结果集
	 * 
	 * @param successList
	 */
	public void removeSuccessItems(List<E> successList) {
		if (this.getSuccessList() == null) {
			return;
		}
		this.getSuccessList().removeAll(successList);
	}

	/**
	 * 移除失败结果集
	 * 
	 * @param failedList
	 */
	public void removeFaileItems(List<E> failedList) {
		if (this.getFaileList() == null) {
			return;
		}
		this.getFaileList().removeAll(failedList);
	}

	/**
	 * 增加成功的结果
	 * 
	 * @param e
	 */
	public void addSuccessItem(E e) {
		if (this.getSuccessList() == null) {
			this.successList = new ArrayList<E>();
		}
		this.getSuccessList().add(e);
	}

	/**
	 * 增加失败的结果
	 * 
	 * @param e
	 */
	public void addFaileItem(E e) {
		if (this.getFaileList() == null) {
			this.faileList = new ArrayList<E>();
		}
		this.getFaileList().add(e);
	}

	/**
	 * 移除成功的结果
	 * 
	 * @param e
	 */
	public void removeSuccessItem(E e) {
		if (this.getSuccessList() == null) {
			return;
		}
		this.getSuccessList().remove(e);
	}

	/**
	 * 移除失败的结果
	 * 
	 * @param e
	 */
	public void removeFaileItem(E e) {
		if (this.getFaileList() == null) {
			return;
		}
		this.getFaileList().remove(e);
	}

	/**
	 * 获取成功的列表
	 * 
	 * @return 成功的列表
	 */
	public List<E> getSuccessList() {
		return this.successList;
	}

	/**
	 * 设置成功的列表
	 * 
	 * @param successList
	 *            成功的列表
	 */
	public void setSuccessList(List<E> successList) {
		this.successList = successList;
	}

	/**
	 * 获取失败的列表
	 * 
	 * @return 失败的列表
	 */
	public List<E> getFaileList() {
		return this.faileList;
	}

	/**
	 * 设置失败的列表
	 * 
	 * @param faileList
	 *            失败的列表
	 */
	public void setFaileList(List<E> faileList) {
		this.faileList = faileList;
	}

	public String getFailedReason() {
		return this.failedReason;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

}
