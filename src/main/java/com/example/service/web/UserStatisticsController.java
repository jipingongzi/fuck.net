package com.example.service.web;

import com.example.service.common.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户统计
 */
@RestController
@RequestMapping("/user")
public class UserStatisticsController {

    /**
     * 用户基本信息统计：
     * 属性：用户数量 订单数量 订单金额
     */
    @GetMapping("/base")
    public RestResponse goodsBaseStatistics(@RequestParam("startTime")long startTime,
                                            @RequestParam("endTime")long endTime){
        return RestResponse.buildResponse("");
    }
    /**
     * 下单数量最多的用户
     * 属性：用户名 下单数量
     */
    @RequestMapping("/order/number/most")
    public RestResponse orderMostUser(@RequestParam(value = "startTime",required = false)Long startTime,
                                      @RequestParam(value = "endTime",required = false)Long endTime,
                                      @RequestParam(value = "number",required = false,defaultValue = "5")Integer number){
        return RestResponse.buildResponse("");
    }

    /**
     * 用户列表统计
     * 属性：用户id 用户账号 所属服务处 余额（元） 充值余额（元） 补贴余额（元） 订单总金额（元） 订单充值金额（元） 订单补贴金额（元）
     */
    @RequestMapping("/list")
    public RestResponse list(@RequestParam(value = "startTime",required = false)Long startTime,
                             @RequestParam(value = "endTime",required = false)Long endTime,
                             @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                             @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse("");
    }
    /**
     * 用户详情统计
     * 属性：用户id 用户账号 所属服务处 所处供应点
     * 余额（元） 充值余额（元） 补贴余额（元）
     * 订单总金额（元） 订单充值金额（元） 订单补贴金额（元） 订单退款金额（元）
     * 代扣总金额（元） 代扣充值金额（元） 代扣补贴金额（元）
     */
    @RequestMapping("/detail")
    public RestResponse detail(@RequestParam(value = "userId")String userId){
        return RestResponse.buildResponse("");
    }

    /**
     * 充值列表
     * 属性：金额（元） 时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/recharge/list")
    public RestResponse rechargeList(@RequestParam(value = "userId")String userId,
                                     @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                     @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse("");
    }

    /**
     * 补贴列表
     * 属性：金额（元） 时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/subsidy/list")
    public RestResponse subsidyList(@RequestParam(value = "userId")String userId,
                                    @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse("");
    }

    /**
     * 代扣列表
     * 属性：金额（元） 时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/proxy/list")
    public RestResponse proxyList(@RequestParam(value = "userId")String userId,
                                  @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse("");
    }

    /**
     * 用户订单列表
     * 属性：订单id 订单编号 用户账号
     * 商品数量 订单金额（元） 充值金额（元） 补贴金额（元）
     * 下单时间（yyyy-MM-dd HH:mm:ss） 预计取货日期（yyyy-MM-dd）
     * 所属供应点 是否取货 货源名称 领货时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/order/list")
    public RestResponse orderList(@RequestParam(value = "userId")String userId,
                                  @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                  @RequestParam(value = "orderStartTime",required = false)Long orderStartTime,
                                  @RequestParam(value = "orderEndTime",required = false)Long orderEndTime,
                                  @RequestParam(value = "pickStartTime",required = false)Long pickStartTime,
                                  @RequestParam(value = "pickEndTime",required = false)Long pickEndTime){
        return RestResponse.buildResponse("");
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
    @RequestMapping("/order/detail")
    public RestResponse orderDetail(@RequestParam("orderId")String orderId){
        return RestResponse.buildResponse("");
    }
}
