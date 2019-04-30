package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.vo.PerformOrderVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/perform")
public class PerformOrderController {
    //文化产品-演出订单查询
    @RequestMapping(value = "/orders")
    public RestResponse getPerformOrders(@RequestBody PerformOrderVo performOrderVo){

    }
}
