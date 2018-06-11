package com.starfish.dao;

import com.starfish.common.user.UserBO;
import com.starfish.common.user.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangjialian on 2017/11/9.
 */

@Repository("userDao")
public interface UserDao {

    List<UserDO> getUserList(Map map);

    int insertUser(UserBO userBO);

    UserBO getUserById(int userId);

    /**
     * 根据id修改用户信息
     * @param userBO
     * @return
     */
    int updateUserById(UserBO userBO);

}
