package com.example.service.web;

import com.example.service.common.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第二版本数据查询
 */
@RestController
@RequestMapping("/goods")
public class GoodsStatisticsController {

    /**
     * 商品基本信息统计：
     * 分类：普通商品 节日商品 代购商品
     * 属性：销量 实时库存 销售金额 订单数量
     */
    @GetMapping("/base/statistics")
    public RestResponse goodsBaseStatistics(@RequestParam("startTime")long startTime,
                                            @RequestParam("endTime")long endTime){
        return RestResponse.buildResponse("");
    }

    /**
     * 销售最佳商品
     * @param number 数量
     * 属性：销量 实时库存
     */
    @GetMapping("/sale/best/statistics")
    public RestResponse saleBestStatistics(@RequestParam(value = "number",required = false,defaultValue = "5")Integer number){
        return RestResponse.buildResponse("");
    }

    /**
     * 销售最差商品
     * @param number 数量
     * 属性：销量 实时库存
     */
    @GetMapping("/sale/worst/statistics")
    public RestResponse saleWorstStatistics(@RequestParam(value = "number",required = false,defaultValue = "5")Integer number){
        return RestResponse.buildResponse("");
    }

    /**
     * 商品基本列表统计：
     * @param startTime 开始时间
     * @param endTime 结束时间
     * 属性：名称 分类 编号 价格（元） 重量 销售数量 销售金额（元） 实时库存
     */
    @GetMapping("/list/statistics")
    public RestResponse goodsListStatistics(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                            @RequestParam(value = "startTime",required = false)long startTime,
                                            @RequestParam(value = "endTime",required = false)long endTime){
        return RestResponse.buildResponse("");
    }

}
