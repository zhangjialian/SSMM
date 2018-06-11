package com.starfish.manager.impl;

import com.starfish.common.Pager;
import com.starfish.common.user.UserBO;
import com.starfish.common.user.UserDO;
import com.starfish.common.user.UserQO;
import com.starfish.common.exception.ErrorCodeException;
import com.starfish.common.systemEnum.ErrorCodeEnum;
import com.starfish.dao.UserDao;
import com.starfish.manager.UserManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangjialian on 2017/11/9.
 */

@Service
public class UserManagerImpl implements UserManager {

    @Resource
    private UserDao userDao;

    /**
     * 获取用户列表
     * @param query
     * @param pager
     * @return
     */
    @Override
    public List<UserDO> getUserList(UserQO query, Pager pager) {
        Map map = new HashMap();
        map.put("userQO", query);
        map.put("pager", pager);

        return userDao.getUserList(map);
    }

    /**
     * 新增用户
     * @param userBO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(UserBO userBO) throws Exception{
        int flag = userDao.insertUser(userBO);

        if(true){
            throw new ErrorCodeException(ErrorCodeEnum.UR01);
        }

        return 0;
    }

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    @Override
    public UserBO getUserById(int userId){
        if(userId == 0){
            return null;
        }

        UserBO user = userDao.getUserById(userId);
        return user;
    }
}
