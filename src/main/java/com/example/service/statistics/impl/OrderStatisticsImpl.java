package com.example.service.statistics.impl;

import com.example.service.common.DateUtil;
import com.example.service.common.GoodsType;
import com.example.service.statistics.IOrderStatistics;
import com.example.service.statistics.dto.OrderDetailDto;
import com.example.service.statistics.dto.OrderItemDto;
import com.example.service.statistics.dto.OrderListDto;
import com.example.service.statistics.dto.OrderListStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderStatisticsImpl implements IOrderStatistics {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderStatisticsImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderListDto> getOrderList(long userId, Long orderStartTime, Long orderEndTime, Long pickStartTime, Long pickEndTime, int pageNumber, int pageSize) {
        StringBuilder baseSql = new StringBuilder("SELECT \n" +
                "o.OrderNo,\n" +
                "o.ID,\n" +
                "m.Phone,\n" +
                "o.GoodsAmount,\n" +
                "o.TotalPrice,\n" +
                "o.Balance,\n" +
                "o.Subsidy,\n" +
                "o.InsertTime,\n" +
                "o.PickupDate,\n" +
                "o.ReceiveTime,\n" +
                "s.Name\n" +
                "FROM YUNYI_Order o\n" +
                "LEFT JOIN YUNYI_Supply s ON o.SupplyID = s.ID\n" +
                "LEFT JOIN YUNYI_Member m ON o.MemberID = m.ID\n" +
                "WHERE o.State = 2 AND m.ID = ?");
        if(pickStartTime != null){
            baseSql.append(" AND o.PickupDate >= " + DateUtil.timeStamp2Date(pickStartTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(pickEndTime != null){
            baseSql.append(" AND o.PickupDate <= " + DateUtil.timeStamp2Date(pickEndTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(orderStartTime != null){
            baseSql.append(" AND o.InsertTime >= " + DateUtil.timeStamp2Date(orderStartTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(orderEndTime != null){
            baseSql.append(" AND o.InsertTime <= " + DateUtil.timeStamp2Date(orderEndTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        List<Map<String,Object>> result = jdbcTemplate.queryForList(getPageQuery(baseSql.toString(),"InsertTime",pageSize,pageNumber),userId);
        List<String> orderIds = result.stream().map(i -> i.get("ID").toString()).collect(Collectors.toList());

        String farmSql = "SELECT DISTINCT f.Name ,og.OrderID\n" +
                "FROM YUNYI_OrderGoods og \n" +
                "LEFT JOIN YUNYI_Farm f ON f.ID = og.FarmID\n" +
                "WHERE og.OrderID in (" + orderIds.stream().map(i -> "'" + i + ",").collect(Collectors.joining(",")) + ")";
        List<Map<String,Object>> farmResult = jdbcTemplate.queryForList(farmSql);

        return result.stream().map(i -> {
            String orderId = i.get("ID").toString();
            String orderNo = i.get("OrderNo").toString();
            String userAccount = i.get("Phone").toString();
            String siteName = i.get("Name").toString();
            StringBuilder supplyName = new StringBuilder("");
            farmResult.forEach(f -> {
                if(orderId.equals(f.get("OrderID").toString()) && supplyName.toString().contains(f.get("Name").toString())){
                    supplyName.append(f.get("Name").toString() + " ");
                }
            });
            int productNumber = Integer.valueOf(i.get("GoodsAmount").toString());
            double orderAmountTotal = Double.valueOf(i.get("TotalPrice").toString());
            double orderAmountRecharge = Double.valueOf(i.get("Balance").toString());
            double orderAmountSubsidy = Double.valueOf(i.get("Subsidy").toString());
            String orderTime = i.get("InsertTime").toString();
            String shouldPickDate = i.get("PickUpDate").toString();
            String realPickTime = i.get("ReceiveTime") != null ? i.get("ReceiveTime").toString() : "";
            String pickStatusDisplay = "已领取";
            if(StringUtils.isEmpty(realPickTime)){
                pickStatusDisplay = "未领取";
            }
            return new OrderListDto(orderId,orderNo,userAccount,
                    siteName,supplyName.toString(),
                    productNumber,orderAmountTotal,orderAmountRecharge,orderAmountSubsidy,orderTime,
                    shouldPickDate,pickStatusDisplay,realPickTime);
        }).collect(Collectors.toList());
    }

    @Override
    public OrderListStatisticsDto getOrderListStatistics(long userId, Long orderStartTime, Long orderEndTime, Long pickStartTime, Long pickEndTime) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "ISNULL(SUM(o.TotalPrice),0) AS amount,\n" +
                "ISNULL(COUNT(o.ID),0) AS number\n" +
                "FROM YUNYI_Order o\n" +
                "LEFT JOIN YUNYI_Supply s ON o.SupplyID = s.ID\n" +
                "LEFT JOIN YUNYI_Member m ON o.MemberID = m.ID\n" +
                "WHERE o.State = 2 AND m.ID = ?)");
        if(pickStartTime != null){
            sql.append(" AND o.PickupDate >= " + DateUtil.timeStamp2Date(pickStartTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(pickEndTime != null){
            sql.append(" AND o.PickupDate <= " + DateUtil.timeStamp2Date(pickEndTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(orderStartTime != null){
            sql.append(" AND o.InsertTime >= " + DateUtil.timeStamp2Date(orderStartTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(orderEndTime != null){
            sql.append(" AND o.InsertTime <= " + DateUtil.timeStamp2Date(orderEndTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql.toString(),userId);
        if(CollectionUtils.isEmpty(result)){
            return new OrderListStatisticsDto(0,0);
        }else {
            Map<String,Object> item = result.get(0);
            int orderNumber = Integer.valueOf(item.get("number").toString());
            double orderAmount = Double.valueOf(item.get("amount").toString());
            return new OrderListStatisticsDto(orderNumber,orderAmount);
        }
    }

    @Override
    public OrderDetailDto getOrderDetail(String orderId) {
        String detailSql = "SELECT \n" +
                "o.OrderNo,\n" +
                "o.ID,\n" +
                "m.Phone,\n" +
                "o.TotalPrice,\n" +
                "o.InsertTime,\n" +
                "o.PickupDate,\n" +
                "o.ReceiveTime,\n" +
                "s.Name\n" +
                "FROM YUNYI_Order o\n" +
                "LEFT JOIN YUNYI_Supply s ON o.SupplyID = s.ID\n" +
                "LEFT JOIN YUNYI_Member m ON o.MemberID = m.ID\n";
        Map<String,Object> detail = jdbcTemplate.queryForList(detailSql).get(0);
        String orderNo = detail.get("OrderNo").toString();
        String userAccount = detail.get("Phone").toString();
        String siteName = detail.get("Name").toString();
        double orderAmountTotal = Double.valueOf(detail.get("TotalPrice").toString());
        String orderTime = detail.get("InsertTime").toString();
        String shouldPickDate = detail.get("PickUpDate").toString();
        String realPickTime = detail.get("ReceiveTime") != null ? detail.get("ReceiveTime").toString() : "";
        String pickStatusDisplay = "已领取";
        if(StringUtils.isEmpty(realPickTime)){
            pickStatusDisplay = "未领取";
        }
        OrderDetailDto result = new OrderDetailDto(orderId,orderNo,userAccount,siteName,orderAmountTotal,orderTime,shouldPickDate,pickStatusDisplay,realPickTime);
        String itemSql = "SELECT \n" +
                "TypeID,CategoryName,GoodsName,Amount,Price,RefundState,ISNULL(RefundPrice,0),RefundTime\n" +
                "FROM YUNYI_OrderGoods og\n" +
                "WHERE og.OrderID = ?";
        jdbcTemplate.queryForList(itemSql,orderId).forEach(i -> {
            int goodType = Integer.valueOf(i.get("TypeID").toString());
            String productType = "";
            if(goodType == GoodsType.NORMAL_GOODS.getValue()){
                productType = GoodsType.NORMAL_GOODS.getDescription();
            }else if(goodType == GoodsType.FESTIVAL_GOODS.getValue()){
                productType = GoodsType.FESTIVAL_GOODS.getDescription();
            }else if(goodType == GoodsType.PROXY_GOODS.getValue()){
                productType = GoodsType.PROXY_GOODS.getDescription();
            }
            String productCategory = i.get("CategoryName").toString();
            String productName = i.get("GoodsName").toString();
            int productNumber = Integer.valueOf(i.get("Amount").toString());
            int refundStatus = Integer.valueOf(i.get("RefundState").toString());
            String refundStatusDisplay = "-";
            if(refundStatus != 0){
                refundStatusDisplay = "已退款";
            }
            double refundAmount = Double.valueOf(i.get("RefundPrice").toString());
            String refundTime = i.get("RefundTime").toString();
            result.addItem(new OrderItemDto(productType,productCategory,productName,productNumber,refundStatusDisplay,refundAmount,refundTime));
        });
        return result;
    }
}
