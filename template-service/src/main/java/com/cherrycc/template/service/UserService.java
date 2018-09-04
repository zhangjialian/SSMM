package com.cherrycc.template.service;

import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.common.Pager;
import com.cherrycc.template.common.query.UserQuery;
import com.cherrycc.template.model.UserDO;

import java.util.List;

/**
 * Created by zhangjialian on 2017/11/9.
 */
public interface UserService {

    /**
     * 获取用户列表
     * @param query
     * @param pager
     * @return
     */
    List<UserDO> getUserList(UserQuery query, Pager pager);

    /**
     * 添加用户
     * @param userBO
     * @return
     * @throws Exception
     */
    int insertUser(UserBO userBO) throws Exception;

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    UserBO getUserById(int userId);

}
