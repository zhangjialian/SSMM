package com.cherrycc.template.dubbo.client;

import com.cherrycc.template.dubbo.base.DubboResponse;
import com.cherrycc.template.dubbo.dto.UserDTO;
import com.cherrycc.template.dubbo.dto.UserRequest;

/**
 * @author BG349176
 * @date 2018/9/5 15:26
 */
public interface UserQueryService {

    /**
     *
     * @param userRequest
     * @return
     */
    DubboResponse<UserDTO> queryUserList(UserRequest userRequest);

}
