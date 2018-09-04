package com.cherrycc.template.service.impl;

import com.cherrycc.template.bo.UserBO;
import com.cherrycc.template.common.Pager;
import com.cherrycc.template.common.exception.ErrorCodeException;
import com.cherrycc.template.common.query.UserQuery;
import com.cherrycc.template.common.systemEnum.ErrorCodeEnum;
import com.cherrycc.template.dao.UserDAO;
import com.cherrycc.template.service.UserService;
import com.cherrycc.template.model.UserDO;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    /**
     * 获取用户列表
     * @param query
     * @param pager
     * @return
     */
    @Override
    public List<UserDO> getUserList(UserQuery query, Pager pager) {
        Map map = new HashMap();
        map.put("userQO", query);
        map.put("pager", pager);

        return userDAO.getUserList(map);
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
        int flag = userDAO.insertUser(userBO);

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

        UserBO user = userDAO.getUserById(userId);
        return user;
    }
}
