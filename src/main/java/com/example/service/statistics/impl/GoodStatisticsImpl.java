package com.example.service.statistics.impl;

import com.example.service.common.DateUtil;
import com.example.service.common.ExtremeType;
import com.example.service.common.GoodsType;
import com.example.service.statistics.IGoodStatistics;
import com.example.service.statistics.dto.GoodsListDto;
import com.example.service.statistics.dto.GoodsSaleExtremeDto;
import com.example.service.statistics.dto.GoodsTypeStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class GoodStatisticsImpl implements IGoodStatistics {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GoodStatisticsImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GoodsTypeStatisticsDto> getGoodsTypeStatistics(Long startTime, Long endTime) throws ExecutionException, InterruptedException {
        List<GoodsTypeStatisticsDto> result = new ArrayList<>();
        Future<GoodsTypeStatisticsDto> normalGoodsTypeStatistics = CompletableFuture.supplyAsync(() -> getGoodsTypeStatistics(GoodsType.NORMAL_GOODS,startTime,endTime));
        Future<GoodsTypeStatisticsDto> festivalGoodsTypeStatistics = CompletableFuture.supplyAsync(() -> getGoodsTypeStatistics(GoodsType.FESTIVAL_GOODS,startTime,endTime));
        Future<GoodsTypeStatisticsDto> proxyGoodsTypeStatistics = CompletableFuture.supplyAsync(() -> getGoodsTypeStatistics(GoodsType.PROXY_GOODS,startTime,endTime));
        result.add(normalGoodsTypeStatistics.get());
        result.add(festivalGoodsTypeStatistics.get());
        result.add(proxyGoodsTypeStatistics.get());
        return result;
    }

    @Override
    public List<GoodsSaleExtremeDto> getGoodsSaleExtreme(ExtremeType extreme, int limitNumber, GoodsType goodsType) {
        String orderRule = "DESC";
        if(extreme == ExtremeType.WORST){
            orderRule = "ASC";
        }
        //获取销售数量
        String sql = "SELECT t.GoodsID AS id,t.number,g.Name FROM (SELECT TOP " + limitNumber + " SUM(Amount) AS number,GoodsID FROM YUNYI_OrderGoods WHERE TypeID = ? GROUP BY GoodsID ORDER BY number " + orderRule + ") t LEFT JOIN YUNYI_Goods g ON g.ID = t.GoodsID";
        List<GoodsSaleExtremeDto> result = jdbcTemplate.queryForList(sql,goodsType.getValue()).stream().map(r -> {
            GoodsSaleExtremeDto dto = new GoodsSaleExtremeDto();
            dto.setGoodId(Long.valueOf(r.get("id").toString()));
            dto.setGoodsName(r.get("Name").toString());
            dto.setSaleNumber(Integer.valueOf(r.get("number").toString()));
            return dto;
        }).collect(Collectors.toList());
        //获取库存
        String storeSql = "SELECT Store,ID FROM YUNYI_Goods WHERE ID in(" + result.stream().map(g -> g.getGoodId() + "").collect(Collectors.joining(",")) + ")";
        jdbcTemplate.queryForList(storeSql).forEach(s -> {
            Long goodId = Long.valueOf(s.get("ID").toString());
            int storeNumber = Integer.valueOf(s.get("Store").toString());
            for (int i = 0; i < result.size(); i++) {
                if(result.get(i).getGoodId() == goodId){
                    result.get(i).setStoreNumber(storeNumber);
                }
            }
        });
        return result;
    }

    @Override
    public List<GoodsListDto> getGoodsList(GoodsType goodsType, Long startTime, Long endTime, int pageNumber, int pageSize) {
        StringBuilder sql = new StringBuilder("SELECT g.id,temp.saleNumber,temp.salePrice,g.Name,c.Name AS CategoryName,g.Price,g.Weight,g.Store,g.SerialNum FROM (\n" +
                "\tSELECT SUM(og.Amount) AS SaleNumber,SUM(og.Price) AS SalePrice,og.GoodsID \n" +
                "\tFROM YUNYI_OrderGoods og \n" +
                "\tLEFT JOIN YUNYI_Order o ON og.OrderID = o.ID\n" +
                "\tWHERE og.TypeID = 3\n");
        if(startTime != null){
            sql.append("AND o.InsertTime >='" + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(endTime != null){
            sql.append("AND o.InsertTime <='" + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        sql.append("\tGROUP BY og.GoodsID\n" +
                ") temp\n" +
                "LEFT JOIN YUNYI_Goods g ON temp.GoodsID = g.ID\n" +
                "LEFT JOIN YUNYI_Category c ON g.CategoryID = c.ID\n");
        return jdbcTemplate.queryForList(getPageQuery(sql.toString(),"saleNumber",pageSize,pageNumber)).stream().map(i -> {
            long goodId = Long.valueOf(i.get("id").toString());
            String goodName = i.get("Name").toString();
            String goodType = i.get("CategoryName").toString();
            String goodNo = i.get("SerialNum").toString();
            double goodPrice = Double.valueOf(i.get("Price").toString());
            String goodUnit = i.get("Weight").toString();
            int storeNumber = Integer.valueOf(i.get("Store").toString());
            int saleNumber = Integer.valueOf(i.get("SaleNumber").toString());
            double salePrice = Double.valueOf(i.get("SalePrice").toString());
            return new GoodsListDto(goodId,goodName,goodType,goodNo,goodPrice,goodUnit,storeNumber,saleNumber,salePrice);
        }).collect(Collectors.toList());
    }

    private GoodsTypeStatisticsDto getGoodsTypeStatistics(GoodsType goodsType,Long startTime,Long endTime){
        //获取销售数量与金额
        StringBuilder numberAndAmountSql = new StringBuilder("SELECT ISNULL(SUM(Amount),0) AS number ,ISNULL(SUM(Price),0.00) AS amount FROM YUNYI_OrderGoods og LEFT JOIN YUNYI_Order o ON o.ID = og.OrderID WHERE TypeID = ?");
        //获取库存
        StringBuilder storeSql = new StringBuilder("SELECT ISNULL(SUM(store),0) AS number FROM YUNYI_Goods WHERE TypeID = ? AND IsPutaway = 2");
        //获取订单数量
        StringBuilder orderSql = new StringBuilder("SELECT ISNULL(COUNT(DISTINCT OrderID),0) AS number FROM YUNYI_OrderGoods og LEFT JOIN YUNYI_Order o ON o.ID = og.OrderID WHERE TypeID = ?");
        if(startTime != null){
            numberAndAmountSql.append(" AND o.InsertTime >= '" + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
            orderSql.append(" AND o.InsertTime >= '" + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(endTime != null){
            numberAndAmountSql.append(" AND o.InsertTime <= '" + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
            orderSql.append(" AND o.InsertTime <= '" + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }

        List<Map<String,Object>> numberAndAmount = jdbcTemplate.queryForList(numberAndAmountSql.toString(),goodsType.getValue());
        int saleNumber = Integer.valueOf(numberAndAmount.get(0).get("number").toString());
        double saleAmount = Double.valueOf(numberAndAmount.get(0).get("amount").toString());

        List<Map<String,Object>> store = jdbcTemplate.queryForList(storeSql.toString(),goodsType.getValue());
        int storeNumber = Integer.valueOf(store.get(0).get("number").toString());

        List<Map<String,Object>> order = jdbcTemplate.queryForList(orderSql.toString(),goodsType.getValue());
        int orderNumber = Integer.valueOf(order.get(0).get("number").toString());
        GoodsTypeStatisticsDto result = new GoodsTypeStatisticsDto(goodsType.getDescription(),saleNumber,saleAmount,storeNumber,orderNumber);
        return result;
    }
}
