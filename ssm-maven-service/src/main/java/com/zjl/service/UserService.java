package com.zjl.service;

import com.zjl.common.user.UserBO;
import com.zjl.common.response.SuccessResponse;

/**
 * Created by zjf on 2017/11/14.
 */
public interface UserService {

    public SuccessResponse<UserBO> getUserById(int id);

}
