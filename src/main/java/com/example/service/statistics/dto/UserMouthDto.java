package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 按月统计用户
 */
@Data
@AllArgsConstructor
public class UserMouthDto {

    /**
     * 时间（yyyy-MM）
     */
    private String time;
    /**
     * 本月用户数量
     */
    private int userNumber;
    /**
     * 本月订单数量
     */
    private int orderNumber;
    /**
     * 本月订单金额
     */
    private double  orderAmount;
}
