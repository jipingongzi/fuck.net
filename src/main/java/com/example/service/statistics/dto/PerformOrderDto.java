package com.example.service.statistics.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 演出订单查询
 */
@Data
public class PerformOrderDto {
    //订单编号
    private String orderId;
    //下单用户
    private String user;
    //演出名称
    private String performName;
    //演出开始时间
    private LocalDateTime performStartTime;
    //演出结束时间
    private LocalDateTime performEndTime;
    //演出分类
    private String performCategory;
    //演出剧场
    private String performTheater;
    //演出厅
    private String performRoom;
    //下单截止时间
    private LocalDateTime orderEndTime;
    //下单时间
    private LocalDateTime orderTime;
    //订单金额
    private Double orderAmount;
    //预约张数
    private Integer reservationNumber;
    //订单状态
    private String ordrStatus;

}
