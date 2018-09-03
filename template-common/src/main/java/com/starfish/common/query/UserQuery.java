package com.starfish.common.query;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author zhangjialian
 * @date 2017/11/9
 */
@Data
public class UserQuery implements Serializable{
    private static final long serialVersionUID = -887850140811692753L;

    private Integer id;

    private String username;

    private String name;

    private String email;
}
