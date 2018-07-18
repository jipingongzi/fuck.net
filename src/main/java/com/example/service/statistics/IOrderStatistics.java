package com.example.service.statistics;

import com.example.service.query.BaseQueryService;
import com.example.service.statistics.dto.OrderDetailDto;
import com.example.service.statistics.dto.OrderListDto;
import com.example.service.statistics.dto.OrderListStatisticsDto;

import java.util.List;

public interface IOrderStatistics extends BaseQueryService {
    /**
     * 查询订单列表
     * @param userId 用户id
     * @param orderStartTime 下单开始时间
     * @param orderEndTime 下单结束时间
     * @param pickStartTime 领货开始时间
     * @param pickEndTime 领货结束时间
     * @param pageNumber 页码
     * @param pageSize 行数
     */
    List<OrderListDto> getOrderList(long userId,Long orderStartTime,Long orderEndTime,Long pickStartTime,Long pickEndTime,int pageNumber,int pageSize);

    /**
     * 查询订单列表
     * @param userId 用户id
     * @param orderStartTime 下单开始时间
     * @param orderEndTime 下单结束时间
     * @param pickStartTime 领货开始时间
     * @param pickEndTime 领货结束时间
     */
    OrderListStatisticsDto getOrderListStatistics(long userId,Long orderStartTime, Long orderEndTime, Long pickStartTime, Long pickEndTime);

    /**
     * 查询订单详情
     */
    OrderDetailDto getOrderDetail(String orderId);
}
