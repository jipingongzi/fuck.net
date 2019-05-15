package com.example.service.statistics.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FishingTicketVo {
    //序号
    private Integer number;
    //订单ID，主键
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
    private String Status;
    //页码
    private Integer pageNumber;
    //页大小
    private Integer pageSize;
    //总页数
    private Integer totalNumber;
    //前往时间开始时间
    private LocalDate leaveForStartTime;
    //前往时间结束时间
    private LocalDate leaveForEndTime;
    //下单开始时间
    private LocalDate orderStartTime;
    //下单结束时间
    private LocalDate orderEndTime;
    //操作
    private String operation;
    private Integer serviceId;
}
