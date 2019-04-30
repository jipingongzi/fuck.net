package com.example.service.statistics;

import com.example.service.statistics.dto.PerformOrderDto;
import com.example.service.statistics.vo.PerformOrderVo;

import java.util.List;

public interface IPerformOrderStatistics {
    //查询演出订单
    List<PerformOrderDto> getPerformOrder(PerformOrderVo performOrderVo);
}
