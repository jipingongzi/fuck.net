package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.IUserStatistics;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IUserStatistics userStatistics;

    @Autowired
    public UserStatisticsController(IUserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }

    /**
     * 用户基本信息统计：
     * 属性：用户数量 订单数量 订单金额
     */
    @GetMapping("/base")
    public RestResponse goodsBaseStatistics(@RequestParam(value = "serviceId")long serviceId,
                                            @RequestParam(value = "startTime",required = false)Long startTime,
                                            @RequestParam(value = "endTime",required = false)Long endTime){
        return RestResponse.buildResponse(userStatistics.userMouth(startTime,endTime,serviceId));
    }
    /**
     * 下单数量最多的用户
     * 属性：用户名 下单数量
     */
    @RequestMapping("/order/number/most")
    public RestResponse orderMostUser(@RequestParam(value = "serviceId")long serviceId,
                                      @RequestParam(value = "startTime",required = false)Long startTime,
                                      @RequestParam(value = "endTime",required = false)Long endTime,
                                      @RequestParam(value = "number",required = false,defaultValue = "5")Integer number){
        return RestResponse.buildResponse(userStatistics.userOrderMost(startTime,endTime,serviceId));
    }

    /**
     * 用户列表统计
     * @param keyWord 根据用户账号或服务处查询
     * @param startTime 开始时间（领货日期）
     * @param endTime 结束时间（领货日期）
     * 属性：用户id 用户账号 所属服务处 余额（元） 充值余额（元） 补贴余额（元） 订单总金额（元） 订单充值金额（元） 订单补贴金额（元）
     */
    @RequestMapping("/list")
    public RestResponse list(@RequestParam(value = "serviceId")long serviceId,
                             @RequestParam(value = "startTime",required = false)Long startTime,
                             @RequestParam(value = "endTime",required = false)Long endTime,
                             @RequestParam(value = "keyWord",required = false)String keyWord,
                             @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                             @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse(userStatistics.getUserList(startTime,endTime,keyWord,serviceId,pageNumber,pageSize));
    }
    /**
     * 用户列表统计
     * @param keyWord 根据用户账号或服务处查询
     * @param startTime 开始时间（领货日期）
     * @param endTime 结束时间（领货日期）
     * 属性：订单总数 订单总金额（元） 代扣总金额（元）
     */
    @RequestMapping("/list/statistics")
    public RestResponse listStatistics(@RequestParam(value = "serviceId")long serviceId,
                             @RequestParam(value = "startTime",required = false)Long startTime,
                             @RequestParam(value = "endTime",required = false)Long endTime,
                             @RequestParam(value = "keyWord",required = false)String keyWord){
        return RestResponse.buildResponse(userStatistics.getUserListStatistics(startTime,endTime,keyWord,serviceId));
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
    public RestResponse rechargeList(@RequestParam(value = "userId")long userId,
                                     @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                     @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse(userStatistics.getUserRechargeList(userId,pageNumber,pageSize));
    }

    /**
     * 补贴列表
     * 属性：金额（元） 时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/subsidy/list")
    public RestResponse subsidyList(@RequestParam(value = "userId")long userId,
                                    @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse(userStatistics.getUserSubsidyList(userId,pageNumber,pageSize));
    }

    /**
     * 代扣列表
     * 属性：金额（元） 时间（yyyy-MM-dd HH:mm:ss）
     */
    @RequestMapping("/proxy/list")
    public RestResponse proxyList(@RequestParam(value = "userId")long userId,
                                  @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        return RestResponse.buildResponse(userStatistics.getUserProxyList(userId,pageNumber,pageSize));
    }
}
