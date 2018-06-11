package com.starfish.common.user;

import java.io.Serializable;

/**
 * Created by zhangjialian on 2017/11/9.
 */
public class UserBO implements Serializable{

    private static final long serialVersionUID = 6101632138008838925L;

    private Integer id;

    private String username;

    private String name;

    private String email;

    /**
     * 行号，用于excel处理
     */
    private int rowNo;

    /**
     * 当前行是否有效，用于excel处理
     */
    private boolean isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
