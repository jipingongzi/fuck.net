package com.example.service.application;

import com.alibaba.fastjson.JSONObject;
import com.example.service.exception.InvalidOperationException;

/**
 * 系统业务处理
 */
public interface ISystemApplicationService {

    /**
     * 管理员登陆
     * @param userName 用户名
     * @param password 密码
     * @exception InvalidOperationException 用户名或密码错误
     * @return 管理员信息：
     * userName:管理员名称
     * serviceId:所属服务处
     */
    JSONObject login(String userName, String password) throws InvalidOperationException;
}
