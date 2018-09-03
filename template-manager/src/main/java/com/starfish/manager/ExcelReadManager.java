package com.starfish.manager;

import com.starfish.bo.UserBO;

import java.io.InputStream;
import java.util.List;

/**
 * Created by zjf on 2017/11/15.
 */
public interface ExcelReadManager {

    /**
     * 批量读取上传文件的用户信息
     * @param inputStream
     * @return
     * @throws Exception
     */
    List<UserBO> batchReadUser(InputStream inputStream) throws Exception;

}
