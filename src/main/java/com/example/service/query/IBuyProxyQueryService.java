package com.example.service.query;

import com.example.service.query.impl.BaseQueryService;

import java.util.List;
import java.util.Map;

public interface IBuyProxyQueryService extends BaseQueryService {

    List<Map<String,Object>> getBuyProxyList(int pageNumber,int pageSize);
}
