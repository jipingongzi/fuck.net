package com.example.service.statistics.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FishingTicketVo {
    //序号
    private Integer number;
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
    //页码
    private Integer pageNumber;
    //页大小
    private Integer pageSize;
    //前往时间开始时间
    private String leaveForStartTime;
    //前往时间结束时间
    private String leaveForEndTime;
    //下单开始时间
    private String orderStartTime;
    //下单结束时间
    private String orderEndTime;
    //操作
    private String operation;
}
