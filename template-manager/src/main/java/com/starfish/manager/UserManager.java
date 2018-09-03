package com.starfish.manager;

import com.starfish.common.Pager;
import com.starfish.bo.UserBO;
import com.starfish.model.UserDO;
import com.starfish.common.query.UserQuery;

import java.util.List;

/**
 * Created by zhangjialian on 2017/11/9.
 */
public interface UserManager {

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
