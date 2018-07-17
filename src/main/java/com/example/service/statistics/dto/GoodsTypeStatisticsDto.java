package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsTypeStatisticsDto {

    /**
     * 商品分类
     * @see com.example.service.common.GoodsType
     */
    private String goodType;
    /**
     * 销售数量
     */
    private int saleNumber;
    /**
     * 销售金额（元）
     */
    private double saleAmount;
    /**
     * 库存数量
     */
    private int storeNumber;
    /**
     * 订单数量
     */
    private int orderNumber;
}
