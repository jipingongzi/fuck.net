package com.example.service.query;

import java.util.List;
import java.util.Map;

public interface IBuyProxyQueryService extends BaseQueryService {

    List<Map<String,Object>> getBuyProxyList(int pageNumber,int pageSize);
}
