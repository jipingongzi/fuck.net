package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单明细dto
 */
@Data
@AllArgsConstructor
public class OrderItemDto {

    /**
     * 商品分类
     * @see com.example.service.common.GoodsType
     */
    private String productType;
    /**
     * 商品类别
     */
    private String productCategory;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private int productNumber;
    /**
     * 退货状态
     */
    private String refundStatusDisplay;
    /**
     * 退货金额
     */
    private double refundAmount;
    /**
     * 退货日期 yyyy-MM-dd HH:mm:ss
     */
    private String refundTime;
}
