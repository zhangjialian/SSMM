package com.cherrycc.template.dao;

import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.model.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author zhangjialian
 * @date 2017/11/9
 */
@Repository
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
