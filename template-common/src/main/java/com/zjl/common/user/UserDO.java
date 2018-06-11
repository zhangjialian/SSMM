package com.zjl.common.user;

import java.io.Serializable;

/**
 * 与数据库表字段一一对应的表
 */
public class UserDO implements Serializable {
    private static final long serialVersionUID = -7002971123512050522L;

    /**
     * 编码
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 登录名
     */
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
