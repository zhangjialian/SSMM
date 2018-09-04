package com.cherrycc.template.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 与数据库表字段一一对应的表
 * @author BG349176
 */
@Data
@Table(name = "user")
//@SequenceGenerator(name = "gt_install_order_seq")
public class UserDO implements Serializable {
    private static final long serialVersionUID = -7002971123512050522L;

    /**
     * 编码
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 登录名
     */
    @Column(name = "username")
    private String username;
}
