package com.cherrycc.template.dubbo.service;

import com.cherrycc.template.dubbo.base.DubboResponse;
import com.cherrycc.template.dubbo.client.UserQueryService;
import com.cherrycc.template.dubbo.dto.UserDTO;
import com.cherrycc.template.dubbo.dto.UserRequest;

/**
 * @author BG349176
 * @date 2018/9/5 15:34
 */
public class UserQueryServiceImpl implements UserQueryService {

    @Override
    public DubboResponse<UserDTO> queryUserList(UserRequest userRequest) {
        return null;
    }

}
