package com.example.service.web;

import com.example.service.statistics.dto.CultureProductOrderDto;
import com.example.service.statistics.impl.CultureProductStatictisImpl;
import com.example.service.statistics.vo.CultureProductOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.service.common.RestResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/culture")
public class CultureProductController {

    private final CultureProductStatictisImpl cultureProductStatictis;

    @Autowired
    public CultureProductController(CultureProductStatictisImpl cultureProductStatictis) {
        this.cultureProductStatictis = cultureProductStatictis;
    }

    @GetMapping("orders")
    public RestResponse getOrders(@RequestParam(required = false) String orderNumber,
                                  @RequestParam(required = false) String userName,
                                  @RequestParam(required = false) Integer performTheaterRoom,
                                  @RequestParam(required = false) String leaveForStartTime,
                                  @RequestParam(required = false) String leaveForEndTime,
                                  @RequestParam(required = false) Integer orderStatus,
                                  @RequestParam(required = false) String orderStartTime,
                                  @RequestParam(required = false) String orderEndTime,
                                  @RequestParam(required = false,defaultValue = "1") Integer pageNumber,
                                  @RequestParam(required = false,defaultValue = "7") Integer pageSize,
                                  @RequestParam(required = false) Integer performType,
                                  @CookieValue(value = "serviceId") Integer serviceId){
        CultureProductOrderVo cultureProductOrderVo = new CultureProductOrderVo();
        cultureProductOrderVo.setOrderNumber(orderNumber);
        cultureProductOrderVo.setUserName(userName);
        cultureProductOrderVo.setPerformTheaterRoom(performTheaterRoom);
        if(leaveForStartTime != null && !leaveForStartTime.isEmpty())
            cultureProductOrderVo.setLeaveForStartTime(LocalDate.parse(leaveForStartTime));
        if(leaveForEndTime != null && !leaveForEndTime.isEmpty())
            cultureProductOrderVo.setLeaveForEndTime(LocalDate.parse(leaveForEndTime));
        cultureProductOrderVo.setOrderStatus(orderStatus);
        if(orderStartTime != null && !orderStartTime.isEmpty())
            cultureProductOrderVo.setOrderStartTime(LocalDate.parse(orderStartTime));
        if(orderEndTime != null && !orderEndTime.isEmpty())
            cultureProductOrderVo.setOrderEndTime(LocalDate.parse(orderEndTime));
        cultureProductOrderVo.setPageNumber(pageNumber);
        cultureProductOrderVo.setPageSize(pageSize);
        cultureProductOrderVo.setPerformType(performType);
        cultureProductOrderVo.setServiceId(serviceId);
        List<CultureProductOrderDto> cultureProductOrderDtoList = new ArrayList<>();
        cultureProductOrderDtoList = cultureProductStatictis.getCultureProductOrders(cultureProductOrderVo);
        log.info("获取文化产品订单");
        return RestResponse.buildResponse(cultureProductOrderDtoList);
    }
}
