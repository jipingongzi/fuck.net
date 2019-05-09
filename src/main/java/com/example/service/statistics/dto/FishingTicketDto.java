package com.example.service.statistics.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FishingTicketDto {
    //订单id
    private Integer id;
    //订单编号
    private String orderId;
    //下单用户
    private String userName;
    //保障点
    private String guaranteeSite;
    //前往时间
    private String leaveForTime;
    //接待项目数
    private Integer ReceptionProjectCount;
    //下单时间
    private String CTime;
    //订单金额
    private BigDecimal OrderTotalMoney;
    //订单账户余额
    private BigDecimal OrderBalancTotal;
    //订单补贴金额
    private BigDecimal OrderSubsidyTotal;
    //订单状态
    private Integer Status;
}
