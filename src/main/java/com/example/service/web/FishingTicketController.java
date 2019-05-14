package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.dto.FishingDetailDto;
import com.example.service.statistics.impl.FishingTicketStatictis;
import com.example.service.statistics.vo.FishingDetailVo;
import com.example.service.statistics.vo.FishingTicketVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fishing")
public class FishingTicketController {

    private final FishingTicketStatictis fishingTicketStatictis;
    private final EntityManager entityManager;

    @Autowired
    public FishingTicketController(FishingTicketStatictis fishingTicketStatictis, EntityManager entityManager) {
        this.fishingTicketStatictis = fishingTicketStatictis;
        this.entityManager = entityManager;
    }
    //垂钓订单查询-垂钓管理
    @GetMapping(value = "/orders")
    public RestResponse getFishingOrders(@RequestParam(required = false) String orderId,
                                         @RequestParam(required = false) String user,
                                         @RequestParam(required = false) String guaranteeSite,
                                         @RequestParam(required = false) String leaveForStartTime,
                                         @RequestParam(required = false) String leaveForEndTime,
                                         @RequestParam(required = false) String Status,
                                         @RequestParam(required = false) String orderStartTime,
                                         @RequestParam(required = false) String orderEndTime,
                                         @RequestParam(required = false,defaultValue = "1") Integer pageNumber,
                                         @RequestParam(required = false,defaultValue = "20") Integer pageSize,
                                         @CookieValue(value = "serviceId") Integer serviceId){
        List<FishingTicketVo> fishingTicketVoList = new ArrayList<>();
        FishingTicketVo fishingTicketVo = new FishingTicketVo();
        fishingTicketVo.setOrderId(orderId);
        fishingTicketVo.setUserName(user);
        fishingTicketVo.setGuaranteeSite(guaranteeSite);
        if(leaveForStartTime != null && !leaveForStartTime.isEmpty())
            fishingTicketVo.setLeaveForStartTime(LocalDate.parse(leaveForStartTime));
        if(leaveForEndTime != null && !leaveForEndTime.isEmpty())
            fishingTicketVo.setLeaveForEndTime(LocalDate.parse(leaveForEndTime));
        fishingTicketVo.setStatus(Status);
        if(orderStartTime != null && !orderStartTime.isEmpty())
            fishingTicketVo.setOrderStartTime(LocalDate.parse(orderStartTime));
        if(orderEndTime != null && !orderEndTime.isEmpty())
            fishingTicketVo.setOrderEndTime(LocalDate.parse(orderEndTime));
        fishingTicketVo.setPageNumber(pageNumber);
        fishingTicketVo.setPageSize(pageSize);
        fishingTicketVo.setServiceId(serviceId);
        fishingTicketVoList = fishingTicketStatictis.getFishingTicketInfo(fishingTicketVo);
        log.info("获取垂钓订单信息");
        return RestResponse.buildResponse(fishingTicketVoList);
    }

//    垂钓订单查询-垂钓管理详情
    @GetMapping(value = "orderDetail")
    public RestResponse getOrderDetail(@RequestParam String orderId,
                                       @CookieValue(value = "serviceId") Integer serviceId){
        FishingTicketVo fishingTicketVo = new FishingTicketVo();
        fishingTicketVo.setOrderId(orderId);
        fishingTicketVo.setServiceId(serviceId);
        List<FishingTicketVo> fishingTicketDtoList = new ArrayList<>();
        fishingTicketDtoList = fishingTicketStatictis.getFishingTicketInfo(fishingTicketVo);
        log.info("获取垂钓订单详情");
        return RestResponse.buildResponse(fishingTicketDtoList.get(0));
    }

    @GetMapping(value = "orderType")
    public RestResponse getOrderForType(@RequestParam Integer orderId){
        List<FishingDetailDto> fishingDetailDtoList = new ArrayList<>();
        fishingDetailDtoList = fishingTicketStatictis.getOrderDetsil(orderId);
        List<FishingDetailVo> fishingDetailVoList = new ArrayList<>();
        BeanUtils.copyProperties(fishingDetailDtoList,fishingDetailVoList);
        log.info("获取垂钓订单的项目分类情况");
        return RestResponse.buildResponse(fishingDetailDtoList);
    }
}
