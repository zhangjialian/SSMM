package com.starfish.dao;

import com.starfish.bo.UserBO;
import com.starfish.model.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangjialian on 2017/11/9.
 */
public interface UserDAO {

    List<UserDO> getUserList(Map map);

    int insertUser(UserBO userBO);

    UserBO getUserById(int userId);

    /**
     * 根据id修改用户信息
     * @param userBO
     * @return
     */
    int updateUserById(UserBO userBO);

    /**
     * 根据username和email查询
     * @param username
     * @param email
     * @return
     */
    UserDO queryByUserNameAndEmail(@Param("username") String username, @Param("email") String email);

}
