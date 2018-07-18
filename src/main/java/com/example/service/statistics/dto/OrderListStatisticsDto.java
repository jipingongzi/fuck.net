package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单列表汇总
 */
@Data
@AllArgsConstructor
public class OrderListStatisticsDto {

    /**
     * 订单数量
     */
    private int orderNumber;
    /**
     * 订单总金额
     */
    private double orderAmount;
}
