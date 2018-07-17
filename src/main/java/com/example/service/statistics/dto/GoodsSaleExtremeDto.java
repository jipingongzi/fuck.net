package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品销售最佳或者最差
 */
@Data
public class GoodsSaleExtremeDto {
    /**
     * 商品id
     */
    private long goodId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 销售数量
     */
    private int saleNumber;
    /**
     * 库存数量
     */
    private int storeNumber;
}
