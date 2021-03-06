package com.example.service.statistics.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CultureProductModel {
    private Integer rowNumber;
    private String orderNumber;
    private String userName;
    private String performName;
    private String performStartTime;
    private String performEndTime;
    private String performType;
    private String performTheater;
    private String performTheaterRoom;
    private String orderEndTime;
    private String orderCreateTime;
    private BigDecimal totalMoney;
    private Integer subscribeTicketCount;
    private String orderStatus;
}
