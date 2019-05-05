package com.example.service.statistics.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PerformOrderVo {
    //序号
    private Integer number;
    //演出名称
    private String performName;
    //订单编号
    private String orderId;
    //下单用户
    private String user;
    //演出剧场
    private String performTheater;
    //演出厅
    private String performRoom;
    //演出分类
    private String performCategory;
    //订单状态
    private String ordrStatus;
    //演出开始时间
    private LocalDateTime performStartTime;
    //演出结束时间
    private LocalDateTime performEndTime;
    //下单开始时间
    private LocalDateTime orderStartTime;
    //下单结束时间
    private LocalDateTime orderEndTime;
    //页码
    private Integer pageNumber;
    //页大小
    private Integer pageSize;
}
