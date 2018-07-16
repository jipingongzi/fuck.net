package com.example.service.query;

import java.util.List;
import java.util.Map;

public interface IOrderQueryService extends BaseQueryService {

    /**
     * 查询商品订单列表
     */
    List<Map<String,Object>> getGoodsOrderList(int pageNumber, int pageSize);

    /**
     * 查询节日订单列表
     */
    List<Map<String,Object>> getFestivalOrderList(int pageNumber, int pageSize);

    /**
     * 查询充值订单列表
     */
    List<Map<String,Object>> getRechargeOrderList(int pageNumber, int pageSize);

    /**
     * 查询退款订单列表
     */
    List<Map<String,Object>> getRefundOrderList(int pageNumber, int pageSize);

    /**
     * 查询供应点商品订单列表
     */
    List<Map<String,Object>> getSupplyGoodsOrderList(int pageNumber, int pageSize);

    /**
     * 查询供应点节日订单列表
     */
    List<Map<String,Object>> getSupplyFestivalOrderList(int pageNumber, int pageSize);
}
