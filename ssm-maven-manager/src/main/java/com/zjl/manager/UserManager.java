package com.zjl.manager;

import com.zjl.common.Pager;
import com.zjl.common.user.UserBO;
import com.zjl.common.user.UserDO;
import com.zjl.common.user.UserQO;
import com.zjl.common.user.UserVO;

import java.util.List;

/**
 * Created by zhangjialian on 2017/11/9.
 */
public interface UserManager {

    /**
     * ��ȡ�û��б�
     * @param query
     * @param pager
     * @return
     */
    List<UserDO> getUserList(UserQO query, Pager pager);

    /**
     * ����û�
     * @param userBO
     * @return
     * @throws Exception
     */
    int insertUser(UserBO userBO) throws Exception;

    /**
     * ����ID��ѯ�û�
     * @param userId
     * @return
     */
    UserBO getUserById(int userId);

}
