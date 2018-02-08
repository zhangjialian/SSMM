package com.zjl.dao;

import com.zjl.common.user.UserBO;
import com.zjl.common.user.UserDO;
import com.zjl.common.user.UserVO;
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
     * ����id�޸��û���Ϣ
     * @param userBO
     * @return
     */
    int updateUserById(UserBO userBO);

}
