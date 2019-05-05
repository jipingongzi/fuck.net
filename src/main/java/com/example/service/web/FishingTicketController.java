package com.example.service.web;

import com.example.service.common.RestResponse;
import com.example.service.statistics.dto.FishingTicketDto;
import com.example.service.statistics.impl.FishingTicketStatictis;
import com.example.service.statistics.vo.FishingTicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fishing")
public class FishingTicketController {
    @Autowired
    private final FishingTicketStatictis fishingTicketStatictis;

    public FishingTicketController(FishingTicketStatictis fishingTicketStatictis) {
        this.fishingTicketStatictis = fishingTicketStatictis;
    }
    @RequestMapping(value = "/orders")
    public RestResponse getFishingOrders(@RequestBody(required = false) FishingTicketVo fishingTicketVo){
        List<FishingTicketDto> fishingTicketDtoList = new ArrayList<>();
        fishingTicketDtoList = fishingTicketStatictis.getFishingTicketInfo(fishingTicketVo);
        return RestResponse.buildResponse(fishingTicketDtoList);
    }
}
