package com.example.service.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情dto
 */
@Data
public class OrderDetailDto {

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 供应点名称
     */
    private String siteName;
    /**
     * 订单总金额
     */
    private double orderAmountTotal;
    /**
     * 下单时间 yyyy-MM-dd HH:mm:ss
     */
    private String orderTime;
    /**
     * 预计领货日期 yyyy-MM-dd
     */
    private String shouldPickDate;
    /**
     * 领货状态描述
     */
    private String pickStatusDisplay;
    /**
     * 实际领货时间 yyy-MM-dd HH:mm:ss
     */
    private String realPickTime;
    /**
     * 订单明细
     */
    private List<OrderItemDto> items;

    public OrderDetailDto(String orderId, String orderNo, String userAccount, String siteName, double orderAmountTotal, String orderTime, String shouldPickDate, String pickStatusDisplay, String realPickTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.userAccount = userAccount;
        this.siteName = siteName;
        this.orderAmountTotal = orderAmountTotal;
        if(orderTime.contains(".")){
            orderTime = orderTime.split("\\.")[0];
        }
        this.orderTime = orderTime;
        this.shouldPickDate = shouldPickDate;
        this.pickStatusDisplay = pickStatusDisplay;
        if(realPickTime.contains(".")){
            realPickTime = realPickTime.split("\\.")[0];
        }
        if(realPickTime.contains("/")){
            realPickTime = realPickTime.replaceAll("/","-");
        }
        this.realPickTime = realPickTime;
    }

    public OrderDetailDto addItem(OrderItemDto item){
        if(CollectionUtils.isEmpty(this.items)){
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        return this;
    }
}
