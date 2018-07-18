package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单列表dto
 */
@Data
@AllArgsConstructor
public class OrderListDto {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 供应点名称
     */
    private String siteName;
    /**
     * 货源名称
     */
    private String supplyName;
    /**
     * 商品数量
     */
    private int productNumber;
    /**
     * 订单总额
     */
    private double orderAmountTotal;
    /**
     * 订单充值
     */
    private double orderAmountRecharge;
    /**
     * 订单补贴
     */
    private double orderAmountSubsidy;
    /**
     * 下单时间 yyyy-MM-dd HH:mm:ss
     */
    private String orderTime;
    /**
     * 预计领货日期 yyyy-MM-dd
     */
    private String shouldPickDate;
    /**
     * 领货状态
     */
    private String pickStatusDisplay;
    /**
     * 实际领货时间 yyyy-MM-dd HH:mm:ss
     */
    private String realPickTime;
}
