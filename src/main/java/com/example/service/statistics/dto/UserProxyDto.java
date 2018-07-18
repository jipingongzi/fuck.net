package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户代扣记录dto
 */
@Data
public class UserProxyDto {

    /**
     * 金额明细
     */
    private String amountSummary;
    /**
     * 时间
     */
    private String time;
    /**
     * 摘要
     */
    private String tip;
    /**
     * 经手人
     */
    private String handler;
    /**
     * 操作人
     */
    private String admin;

    public UserProxyDto(double total,double balance,double subsidy,String time,String tip,String handler,String admin){
        this.amountSummary = buildAmountSummary(total,balance,subsidy);
        this.time = time;
        this.tip = tip;
        this.handler = handler;
        this.admin = admin;
    }
    private String buildAmountSummary(double total,double balance,double subsidy){
        StringBuilder summary = new StringBuilder("总金额：￥" + total);
        summary.append("\r\n");
        summary.append("(");
        summary.append("充值金额：￥" + balance);
        summary.append("补贴金额：￥" + subsidy);
        summary.append(")");
        return summary.toString();
    }
}
