package com.example.service.web;

import com.example.service.common.ExtremeType;
import com.example.service.common.GoodsType;
import com.example.service.common.RestResponse;
import com.example.service.exception.InvalidOperationException;
import com.example.service.statistics.IGoodStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 第二版本数据查询
 */
@RestController
@RequestMapping("/goods")
public class GoodsStatisticsController {

    private final IGoodStatistics goodStatistics;

    @Autowired
    public GoodsStatisticsController(IGoodStatistics goodStatistics) {
        this.goodStatistics = goodStatistics;
    }

    /**
     * 商品基本信息统计：
     * 分类：普通商品 节日商品 代购商品
     * 属性：销量 实时库存 销售金额 订单数量
     */
    @GetMapping("/base")
    public RestResponse goodsBaseStatistics(@RequestParam(value = "startTime",required = false)Long startTime,
                                            @RequestParam(value = "endTime",required = false)Long endTime) throws ExecutionException, InterruptedException {
        return RestResponse.buildResponse(goodStatistics.getGoodsTypeStatistics(startTime,endTime));
    }

    /**
     * 销售最佳商品
     * @param number 数量
     * 属性：销量 实时库存
     */
    @GetMapping("/sale/best")
    public RestResponse saleBestStatistics(@RequestParam("goodType")Integer goodType,
                                           @RequestParam(value = "number",required = false,defaultValue = "5")Integer number) throws InvalidOperationException {
        GoodsType goodsTypeEnum = getGoodType(goodType);
        return RestResponse.buildResponse(goodStatistics.getGoodsSaleExtreme(ExtremeType.BEST,number,goodsTypeEnum));
    }

    /**
     * 销售最差商品
     * @param number 数量
     * 属性：销量 实时库存
     */
    @GetMapping("/sale/worst")
    public RestResponse saleWorstStatistics(@RequestParam("goodType")Integer goodType,
                                            @RequestParam(value = "number",required = false,defaultValue = "5")Integer number) throws InvalidOperationException {
        GoodsType goodsTypeEnum = getGoodType(goodType);
        return RestResponse.buildResponse(goodStatistics.getGoodsSaleExtreme(ExtremeType.WORST,number,goodsTypeEnum));
    }

    /**
     * 商品基本列表统计：
     * @param startTime 开始时间
     * @param endTime 结束时间
     * 属性：名称 分类 编号 价格（元） 重量 销售数量 销售金额（元） 实时库存
     */
    @GetMapping("/list")
    public RestResponse goodsListStatistics(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                            @RequestParam("goodType")Integer goodType,
                                            @RequestParam(value = "startTime",required = false)Long startTime,
                                            @RequestParam(value = "endTime",required = false)Long endTime) throws InvalidOperationException {
        GoodsType goodsTypeEnum = getGoodType(goodType);
        return RestResponse.buildResponse(goodStatistics.getGoodsList(goodsTypeEnum,startTime,endTime,pageNumber,pageSize));
    }

    private GoodsType getGoodType(int value) throws InvalidOperationException {
        GoodsType goodsTypeEnum = null;
        switch (value){
            case 1 :
                goodsTypeEnum = GoodsType.NORMAL_GOODS;
                break;
            case 2 :
                goodsTypeEnum = GoodsType.FESTIVAL_GOODS;
                break;
            case 3 :
                goodsTypeEnum = GoodsType.PROXY_GOODS;
                break;
            default:
        }
        if(goodsTypeEnum == null){
            throw new InvalidOperationException("没有该分类");
        }
        return goodsTypeEnum;
    }

}
