package com.zjl.common.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ruhnnҵ��������
 * 
 * @since 1.0
 * @version 2016��3��21�� ����6:51:31
 * @author zhangby5
 */
public class ResponseResult<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7239094706144266342L;

	/**
	 * ִ�гɹ����б�
	 */
	private List<E> successList;

	/**
	 * ִ��ʧ�ܵ��б�
	 */
	private List<E> faileList;

	/**
	 * ʧ��ԭ��
	 */
	private String failedReason;

	/**
	 * ������<strong>Ĭ�Ϸ��̰߳�ȫ</strong>
	 */
	public ResponseResult() {
		super();
		this.successList = new ArrayList<E>();
		this.faileList = new ArrayList<E>();
	}

	/**
	 * ������
	 *
	 * @param sync
	 *            �Ƿ����̰߳�ȫ�Ľ����<br>
	 *            true:<strong>�̰߳�ȫ</strong><br>
	 *            false:<strong>���̰߳�ȫ</strong>
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
	 * ���ӽ����
	 * 
	 * @param result
	 */
	public void addBaseResult(ResponseResult<E> result) {
		this.getSuccessList().addAll(result.getSuccessList());
		this.getFaileList().addAll(result.getFaileList());
	}

	/**
	 * ��ӳɹ������
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
	 * ���ʧ�ܽ����
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
	 * �Ƴ��ɹ������
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
	 * �Ƴ�ʧ�ܽ����
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
	 * ���ӳɹ��Ľ��
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
	 * ����ʧ�ܵĽ��
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
	 * �Ƴ��ɹ��Ľ��
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
	 * �Ƴ�ʧ�ܵĽ��
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
	 * ��ȡ�ɹ����б�
	 * 
	 * @return �ɹ����б�
	 */
	public List<E> getSuccessList() {
		return this.successList;
	}

	/**
	 * ���óɹ����б�
	 * 
	 * @param successList
	 *            �ɹ����б�
	 */
	public void setSuccessList(List<E> successList) {
		this.successList = successList;
	}

	/**
	 * ��ȡʧ�ܵ��б�
	 * 
	 * @return ʧ�ܵ��б�
	 */
	public List<E> getFaileList() {
		return this.faileList;
	}

	/**
	 * ����ʧ�ܵ��б�
	 * 
	 * @param faileList
	 *            ʧ�ܵ��б�
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
