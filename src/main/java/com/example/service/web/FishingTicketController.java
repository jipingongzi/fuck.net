package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.dto.FishingTicketDto;
import com.example.service.statistics.impl.FishingTicketStatictis;
import com.example.service.statistics.vo.FishingTicketVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fishing")
public class FishingTicketController {
    @Autowired
    private final FishingTicketStatictis fishingTicketStatictis;

    public FishingTicketController(FishingTicketStatictis fishingTicketStatictis) {
        this.fishingTicketStatictis = fishingTicketStatictis;
    }
    @GetMapping(value = "/orders")
    public RestResponse getFishingOrders(@RequestParam(required = false) String orderId,
                                         @RequestParam(required = false) String user,
                                         @RequestParam(required = false) String guaranteeSite,
                                         @RequestParam(required = false) String leaveForStartTime,
                                         @RequestParam(required = false) String leaveForEndTime,
                                         @RequestParam(required = false) Integer Status,
                                         @RequestParam(required = false) String orderStartTime,
                                         @RequestParam(required = false) String orderEndTime,
                                         @RequestParam(required = false,defaultValue = "1") Integer pageNumber,
                                         @RequestParam(required = false,defaultValue = "20") Integer pageSize){
        List<FishingTicketVo> fishingTicketVoList = new ArrayList<>();
        FishingTicketVo fishingTicketVo = new FishingTicketVo();
        fishingTicketVo.setOrderId(orderId);
        fishingTicketVo.setUserName(user);
        fishingTicketVo.setGuaranteeSite(guaranteeSite);
        fishingTicketVo.setLeaveForStartTime(leaveForStartTime);
        fishingTicketVo.setLeaveForEndTime(leaveForEndTime);
        fishingTicketVo.setStatus(Status);
        fishingTicketVo.setOrderStartTime(orderStartTime);
        fishingTicketVo.setOrderEndTime(orderEndTime);
        fishingTicketVo.setPageNumber(pageNumber);
        fishingTicketVo.setPageSize(pageSize);
        fishingTicketVoList = fishingTicketStatictis.getFishingTicketInfo(fishingTicketVo);
        log.info("获取垂钓订单信息");
        return RestResponse.buildResponse(fishingTicketVoList);
    }
}
