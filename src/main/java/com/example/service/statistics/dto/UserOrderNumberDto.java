package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户下单数量dto
 */
@Data
@AllArgsConstructor
public class UserOrderNumberDto {

    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 下单数量
     */
    private int orderNumber;
}
