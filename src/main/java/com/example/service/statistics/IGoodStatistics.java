package com.example.service.statistics;

import com.example.service.common.ExtremeType;
import com.example.service.common.GoodsType;
import com.example.service.query.BaseQueryService;
import com.example.service.statistics.dto.GoodsListDto;
import com.example.service.statistics.dto.GoodsSaleExtremeDto;
import com.example.service.statistics.dto.GoodsTypeStatisticsDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 商品统计查询
 */
public interface IGoodStatistics extends BaseQueryService {

    /**
     * 获取商品分类统计
     * @param startTime 下单开始时间
     * @param endTime 下单结束时间
     */
    List<GoodsTypeStatisticsDto> getGoodsTypeStatistics(Long startTime,Long endTime) throws ExecutionException, InterruptedException;

    /**
     * 获取商品销售最佳或最差统计
     * @param extreme 最佳或最差
     * @see  com.example.service.common.ExtremeType
     * @param limitNumber 个数
     * @param goodsType 商品分类
     * @see com.example.service.common.GoodsType
     */
    List<GoodsSaleExtremeDto> getGoodsSaleExtreme(ExtremeType extreme, int limitNumber, GoodsType goodsType);

    /**
     * 商品列表查询
     * @param goodsType 商品分类
     * @see com.example.service.common.GoodsType
     * @param startTime 下单开始时间
     * @param endTime 下单结束时间
     * @param pageNumber 页码
     * @param pageSize 行数
     */
    List<GoodsListDto> getGoodsList(GoodsType goodsType,Long startTime,Long endTime,int pageNumber,int pageSize);
}
