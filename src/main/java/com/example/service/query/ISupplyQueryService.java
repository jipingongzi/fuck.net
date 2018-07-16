package com.example.service.query;

import java.util.List;
import java.util.Map;

/**
 * 供应点查询服务
 */
public interface ISupplyQueryService extends BaseQueryService {

    List<Map<String,Object>> getSupplyList(int pageNumber,int pageSize);
}
