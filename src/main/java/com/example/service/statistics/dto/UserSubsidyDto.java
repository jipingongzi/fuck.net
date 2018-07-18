package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户补贴记录dto
 */
@Data
@AllArgsConstructor
public class UserSubsidyDto {
    /**
     * 充值金额
     */
    private double amount;
    /**
     * 充值时间 yyyy-MM-dd HH:mm:ss
     */
    private String time;
}
