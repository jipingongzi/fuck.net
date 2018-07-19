package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户补贴记录dto
 */
@Data
public class UserSubsidyDto {
    /**
     * 充值金额
     */
    private double amount;
    /**
     * 充值时间 yyyy-MM-dd HH:mm:ss
     */
    private String time;

    public UserSubsidyDto(double amount, String time) {
        this.amount = amount;
        if(time.contains(".")){
            time = time.split("\\.")[0];
        }
        this.time = time;
    }
}
