package com.starfish.service;

import com.starfish.common.user.UserBO;
import com.starfish.common.response.SuccessResponse;

/**
 * Created by zjf on 2017/11/14.
 */
public interface UserService {

    public SuccessResponse<UserBO> getUserById(int id);

}
