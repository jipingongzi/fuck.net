package com.example.service.statistics.dto;

import lombok.Data;

/**
 * 用户详情dto
 */
@Data
public class UserDetailDto {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 所属供应点
     */
    private String siteName;
    /**
     * 所属服务处
     */
    private String serviceName;
    /**
     * 余额总额
     */
    private double amountTotal;
    /**
     * 余额充值
     */
    private double amountRecharge;
    /**
     * 余额补贴
     */
    private double amountSubsidy;
    /**
     * 订单退款金额
     */
    private double refundAmountTotal;
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
     *  代扣总额
     */
    private double proxyAmountTotal;
    /**
     * 代扣充值
     */
    private double proxyAmountRecharge;
    /**
     * 代扣补贴
     */
    private double proxyAmountSubsidy;

    public UserDetailDto(long userId, String userAccount, String siteName, String serviceName) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.siteName = siteName;
        this.serviceName = serviceName;
    }
    public UserDetailDto buildAmount(double amountTotal,double amountRecharge,double amountSubsidy,double refundAmountTotal){
        this.amountTotal = amountTotal;
        this.amountRecharge = amountRecharge;
        this.amountSubsidy = amountSubsidy;
        this.refundAmountTotal = refundAmountTotal;
        return this;
    }
    public UserDetailDto buildOrderAmount(double orderAmountTotal,double orderAmountRecharge,double orderAmountSubsidy){
        this.orderAmountTotal = orderAmountTotal;
        this.orderAmountRecharge = orderAmountRecharge;
        this.orderAmountSubsidy = orderAmountSubsidy;
        return this;
    }
    public UserDetailDto buildProxyAmount(double proxyAmountTotal,double proxyAmountRecharge,double proxyAmountSubsidy){
        this.proxyAmountTotal = proxyAmountTotal;
        this.proxyAmountRecharge = proxyAmountRecharge;
        this.proxyAmountSubsidy = proxyAmountSubsidy;
        return this;
    }
}
