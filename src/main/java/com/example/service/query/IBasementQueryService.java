package com.example.service.query;

import com.example.service.query.impl.BaseQueryService;

import java.util.List;
import java.util.Map;

public interface IBasementQueryService extends BaseQueryService {

    /**
     * 查询采购组列表
     */
    List<Map<String,Object>> getBasementList(int pageNumber, int pageSize);

}
