package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户列表统计汇总
 */
@Data
@AllArgsConstructor
public class UserListStatisticsDto {

    /**
     * 订单总数
     */
    private int orderNumber;
    /**
     * 订单总金额
     */
    private double orderAmount;
    /**
     * 代扣总金额
     */
    private double proxyAmount;
}
