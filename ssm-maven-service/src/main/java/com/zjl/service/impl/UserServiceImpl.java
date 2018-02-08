package com.zjl.service.impl;

import com.zjl.common.user.UserBO;
import com.zjl.common.response.SuccessResponse;
import com.zjl.manager.UserManager;
import com.zjl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zjf on 2017/11/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserManager userManager;

    @Override
    public SuccessResponse<UserBO> getUserById(int id) {
        SuccessResponse<UserBO> responseResult = new SuccessResponse<>();

        if(id == 0){
            return responseResult;
        }

        UserBO userBO = userManager.getUserById(id);

        if(userBO == null){
            return responseResult;
        }

        responseResult.setResult(userBO);
        return responseResult;
    }
}
