package com.example.service.statistics;

import com.example.service.statistics.dto.CultureProductOrderDto;
import com.example.service.statistics.vo.CultureProductOrderVo;

import java.util.List;

public interface ICultureProductStatistic {

    //查询文化产品订单
    List<CultureProductOrderDto> getCultureProductOrders(CultureProductOrderVo cultureProductOrderVo);
}
