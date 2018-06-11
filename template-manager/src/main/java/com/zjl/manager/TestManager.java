package com.zjl.manager;

public interface TestManager {

    /**
     * 测试连续两次新增的情况下，锁的作用范围
     * @throws Exception
     */
    void testTransaction() throws Exception;

    /**
     * 测试insert
     * @throws Exception
     */
    void insertTest() throws Exception;

}
