package com.cherrycc.template.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author BG349176
 * @param <E>
 */
@Data
public class BaseResponse<E> implements Serializable {
	private static final long serialVersionUID = 7239094706144266342L;

    /**
     * 状态码
     */
	private boolean success = true;

	/**
	 * 失败原因
	 */
	private String failedMsg;

    /**
     * 返回的实体
     */
    private E result;

}
