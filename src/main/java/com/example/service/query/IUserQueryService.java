package com.example.service.query;

import com.example.service.query.impl.BaseQueryService;

import java.util.List;
import java.util.Map;

public interface IUserQueryService extends BaseQueryService {

    List<Map<String,Object>> getUserList(int pageNumber,int pageSize);
}
