package com.example.service.statistics.dto;

import lombok.Data;

/**
 * 用户列表统计dto
 */
@Data
public class UserListDto {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户账号
     */
    private String userAccount;
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

    public UserListDto(long userId, String userAccount, String serviceName) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.serviceName = serviceName;
    }

    public UserListDto buildAmount(double amountTotal,double amountRecharge,double amountSubsidy){
        this.amountTotal = amountTotal;
        this.amountRecharge = amountRecharge;
        this.amountSubsidy = amountSubsidy;
        return this;
    }
    public UserListDto buildOrderAmount(double orderAmountTotal,double orderAmountRecharge,double orderAmountSubsidy){
        this.orderAmountTotal = orderAmountTotal;
        this.orderAmountRecharge = orderAmountRecharge;
        this.orderAmountSubsidy = orderAmountSubsidy;
        return this;
    }

}
