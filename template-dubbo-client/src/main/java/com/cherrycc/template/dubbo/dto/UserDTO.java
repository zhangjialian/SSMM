package com.cherrycc.template.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author BG349176
 * @date 2018/9/5 15:30
 */
@Data
public class UserDTO implements Serializable {
    private Integer id;
    private String username;
    private String name;
    private String email;
}
