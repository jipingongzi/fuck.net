package com.example.service.query;

import com.example.service.query.impl.BaseQueryService;

import java.util.List;
import java.util.Map;

public interface IUserQueryService extends BaseQueryService {

    /**
     * 查询用户列表
     */
    List<Map<String,Object>> getUserList(int pageNumber,int pageSize,String amdin);

    /**
     * 查询代扣列表
     */
    List<Map<String,Object>> getCosytList(int pageNumber,int pageSize);
}
