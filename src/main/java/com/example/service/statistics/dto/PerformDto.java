package com.example.service.statistics.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 演出信息
 */
@Data
public class PerformDto {

    private Integer id;

    //演出id
    private Integer performId;

    //演出名称
    private String name;

    //演出开始时间
    private LocalDateTime stratTime;

    //演出结束时间
    private LocalDateTime endTime;

    //下单开始时间
    private LocalDateTime orderStartTime;

    //下单结束时间
    private LocalDateTime orderEndTime;

    //限购数量\0、不限购；大于0为限购数量
    private Integer buyCountLimit;

    //单图演出封面地址
    private String coverImgUrl;

    //演出简介
    private String summary;
}
