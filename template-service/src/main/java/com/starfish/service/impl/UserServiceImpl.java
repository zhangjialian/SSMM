package com.starfish.service.impl;

import com.starfish.common.user.UserBO;
import com.starfish.common.response.SuccessResponse;
import com.starfish.manager.UserManager;
import com.starfish.service.UserService;
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
