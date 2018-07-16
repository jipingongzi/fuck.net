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
    @GetMapping("/base/statistics")
    public RestResponse goodsBaseStatistics(@RequestParam("startTime")long startTime,
                                            @RequestParam("endTime")long endTime){
        return RestResponse.buildResponse("");
    }
    /**
     * 下单数量最多的用户
     */
    @RequestMapping("/order/number/most")
    public RestResponse orderMostUser(@RequestParam(value = "startTime",required = false)Long startTime,
                                      @RequestParam(value = "endTime",required = false)Long endTime,
                                      @RequestParam(value = "number",required = false,defaultValue = "5")Integer number){
        return RestResponse.buildResponse("");
    }


}
