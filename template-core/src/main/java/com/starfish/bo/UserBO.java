package com.starfish.bo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author zhangjialian
 * @date 2017/11/9
 */
@Data
public class UserBO implements Serializable{

    private static final long serialVersionUID = 6101632138008838925L;

    private Integer id;

    private String username;

    private String name;

    private String email;

}
