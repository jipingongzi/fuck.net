package com.example.service.statistics.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CultureProductOrderVo {
    private String orderNumber;
    private String userName;
    private Integer performTheaterRoom;
    private LocalDate leaveForStartTime;
    private LocalDate leaveForEndTime;
    private Integer orderStatus;
    private LocalDate orderStartTime;
    private LocalDate orderEndTime;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer performType;
    private Integer serviceId;
}
