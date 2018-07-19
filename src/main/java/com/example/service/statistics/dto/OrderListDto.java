package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单列表dto
 */
@Data
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

    public OrderListDto(String orderId, String orderNo, String userAccount, String siteName, String supplyName, int productNumber, double orderAmountTotal, double orderAmountRecharge, double orderAmountSubsidy, String orderTime, String shouldPickDate, String pickStatusDisplay, String realPickTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.userAccount = userAccount;
        this.siteName = siteName;
        this.supplyName = supplyName;
        this.productNumber = productNumber;
        this.orderAmountTotal = orderAmountTotal;
        this.orderAmountRecharge = orderAmountRecharge;
        this.orderAmountSubsidy = orderAmountSubsidy;
        if(orderTime.contains(".")){
            orderTime = orderTime.split("\\.")[0];
        }
        this.orderTime = orderTime;
        this.shouldPickDate = shouldPickDate;
        this.pickStatusDisplay = pickStatusDisplay;
        if(realPickTime.contains(".")){
            realPickTime = realPickTime.split("\\.")[0];
        }
        if(realPickTime.contains("/")){
            realPickTime = realPickTime.replaceAll("/","-");
        }
        this.realPickTime = realPickTime;
    }
}
