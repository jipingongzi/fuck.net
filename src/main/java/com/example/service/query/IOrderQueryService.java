package com.example.service.query;

import com.example.service.query.impl.BaseQueryService;

import java.util.List;
import java.util.Map;

public interface IOrderQueryService extends BaseQueryService {

    /**
     * 查询商品订单列表
     */
    List<Map<String,Object>> getGoodsOrderList(int pageNumber, int pageSize);

}
