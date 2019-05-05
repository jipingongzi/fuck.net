package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.dto.PerformOrderDto;
import com.example.service.statistics.impl.PerformOrderStatstics;
import com.example.service.statistics.vo.PerformOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/perform")
public class PerformOrderController {
    @Autowired
    private final PerformOrderStatstics performOrderStatstics;

    public PerformOrderController(PerformOrderStatstics performOrderStatstics) {
        this.performOrderStatstics = performOrderStatstics;
    }

    //文化产品-演出订单查询
    @GetMapping(value = "/orders")
    public RestResponse getPerformOrders(@RequestBody(required = false) PerformOrderVo performOrderVo){
        List<PerformOrderDto> list = new ArrayList<>();
        list = performOrderStatstics.getPerformOrder(null);
        return RestResponse.buildResponse(list);
    }
}
