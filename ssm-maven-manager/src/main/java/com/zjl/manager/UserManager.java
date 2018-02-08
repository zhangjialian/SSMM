package com.zjl.manager;

import com.zjl.common.Pager;
import com.zjl.common.user.UserBO;
import com.zjl.common.user.UserDO;
import com.zjl.common.user.UserQO;
import com.zjl.common.user.UserVO;

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
    List<UserDO> getUserList(UserQO query, Pager pager);

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
