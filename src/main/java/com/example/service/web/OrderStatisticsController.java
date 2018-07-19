package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.IOrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单统计
 */
@RestController
@RequestMapping("/order")
public class OrderStatisticsController {

    private final IOrderStatistics orderStatistics;

    @Autowired
    public OrderStatisticsController(IOrderStatistics orderStatistics) {
        this.orderStatistics = orderStatistics;
    }

    /**
     * 用户订单列表
     * 属性：订单id 订单编号 用户账号
     * 商品数量 订单金额（元） 充值金额（元） 补贴金额（元）
     * 下单时间（yyyy-MM-dd HH:mm:ss） 预计取货日期（yyyy-MM-dd）
     * 所属供应点 是否取货 货源名称 领货时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/list")
    public RestResponse orderList(@RequestParam(value = "userId")long userId,
                                  @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                  @RequestParam(value = "orderStartTime",required = false)Long orderStartTime,
                                  @RequestParam(value = "orderEndTime",required = false)Long orderEndTime,
                                  @RequestParam(value = "pickStartTime",required = false)Long pickStartTime,
                                  @RequestParam(value = "pickEndTime",required = false)Long pickEndTime){
        return RestResponse.buildResponse(orderStatistics.getOrderList(userId,orderStartTime,orderEndTime,pickStartTime,pickEndTime,pageNumber,pageSize));
    }

    /**
     * 用户订单列表
     * 属性：订单id 订单编号 用户账号
     * 商品数量 订单金额（元） 充值金额（元） 补贴金额（元）
     * 下单时间（yyyy-MM-dd HH:mm:ss） 预计取货日期（yyyy-MM-dd）
     * 所属供应点 是否取货 货源名称 领货时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/list/statistics")
    public RestResponse orderListStatistics(@RequestParam(value = "userId")long userId,
                                  @RequestParam(value = "orderStartTime",required = false)Long orderStartTime,
                                  @RequestParam(value = "orderEndTime",required = false)Long orderEndTime,
                                  @RequestParam(value = "pickStartTime",required = false)Long pickStartTime,
                                  @RequestParam(value = "pickEndTime",required = false)Long pickEndTime){
        return RestResponse.buildResponse(orderStatistics.getOrderListStatistics(userId,orderStartTime,orderEndTime,pickStartTime,pickEndTime));
    }

    /**
     * 用户订单详情
     * 属性：订单id 订单编号 用户账号
     * 订单金额（元） 供应点名称 下单时间（yyyy-MM-dd HH:mm:ss）
     * 预计取货日期（yyyy-MM-dd） 是否取货 领货时间（yyyy-MM-dd HH:mm:ss）
     *
     * 订单明细列表：
     * 商品分类（普通，节日，代购） 商品类别 商品名称 数量 金额（元） 退货状态 退款金额 退货时间
     */
    @RequestMapping("/detail")
    public RestResponse orderDetail(@RequestParam("orderId")String orderId){
        return RestResponse.buildResponse(orderStatistics.getOrderDetail(orderId));
    }
}
