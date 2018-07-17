package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品列表dto
 */
@Data
@AllArgsConstructor
public class GoodsListDto {
    /**
     * 商品id
     */
    private long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品分类
     */
    private String goodType;
    /**
     * 商品编号
     */
    private String goodNo;
    /**
     * 商品价格（元）
     */
    private double goodPrice;
    /**
     * 商品规格
     */
    private String goodUnit;
    /**
     * 库存数量
     */
    private int storeNumber;
    /**
     * 销售数量
     */
    private int saleNumber;
    /**
     * 销售金额（元）
     */
    private double saleAmount;
}
