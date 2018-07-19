package com.example.service.statistics.impl;

import com.example.service.common.DateUtil;
import com.example.service.statistics.IUserStatistics;
import com.example.service.statistics.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserStatisticsImpl implements IUserStatistics {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserStatisticsImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserOrderNumberDto> userOrderMost(Long startTime,Long endTime,long serviceId) {
        StringBuilder sql = new StringBuilder("SELECT TOP 5 temp.number,m.Phone,m.ID FROM(\n" +
                "SELECT COUNT(o.ID) number,o.MemberID FROM YUNYI_Order o\n" +
                "WHERE o.State = 2\n");
        if(startTime != null){
            sql.append(" AND o.InsertTime >= '" + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(endTime != null){
            sql.append(" AND o.InsertTime <= '" + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        sql.append("GROUP BY o.MemberID) temp\n" +
        "LEFT JOIN YUNYI_Member m ON m.ID = temp.MemberID\n");
        //serviceId等于0是管理局人员
        if(serviceId != 0){
            sql.append("WHERE m.ServiceID  = \n" + serviceId);
        }
        sql.append("ORDER BY number DESC");
        return jdbcTemplate.queryForList(sql.toString()).stream().map(i -> {
            long userId = Long.valueOf(i.get("ID").toString());
            String userAccount = i.get("Phone").toString();
            int orderNumber = Integer.valueOf(i.get("number").toString());
            return new UserOrderNumberDto(userId,userAccount,orderNumber);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserMouthDto> userMouth(Long startTime, Long endTime, long serviceId) {
        List<UserMouthDto> result = new ArrayList<>();
        //初始用户量 2017-12 开始
        int userCount = 216;
        StringBuilder orderSql = new StringBuilder("SELECT YEAR(o.InsertTime) AS year,MONTH(o.InsertTime) AS mouth,COUNT(o.ID) AS number,SUM(o.TotalPrice) AS amount \n" +
                "FROM YUNYI_Order o\n" +
                "LEFT JOIN YUNYI_Member m ON o.MemberID = m.ID\n" +
                "WHERE o.State =2 \n");
        if(serviceId != 0){
            orderSql.append(" AND m.ServiceId = " + serviceId);
        }
        if(startTime != null){
            orderSql.append(" AND o.InsertTime >= '" + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(endTime != null){
            orderSql.append(" AND o.InsertTime <= '" + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        orderSql.append(" GROUP BY YEAR(o.InsertTime),MONTH(o.InsertTime) ORDER BY year , mouth ");
        StringBuilder userSql = new StringBuilder("SELECT YEAR(m.InsertTime) AS year,MONTH(m.InsertTime) AS mouth ,COUNT(ID) AS number" +
                " FROM YUNYI_Member m " +
                " WHERE m.IsDeleted = 0 ");
        if(serviceId != 0){
            userSql.append(" AND m.ServiceId = " + serviceId);
        }
        userSql.append(" GROUP BY YEAR(m.InsertTime),MONTH(m.InsertTime) ORDER BY year , mouth;");
        List<Map<String,Object>> orderResult = jdbcTemplate.queryForList(orderSql.toString());
        List<Map<String,Object>> userResult = jdbcTemplate.queryForList(userSql.toString());
        //月份与用户数映射 key:yyyy-MM value:userCount
        Map<String,Integer> userCountTimeMap = new HashMap<>();
        for (int i = 0; i < userResult.size(); i++) {
            Map<String,Object> u = userResult.get(i);
            String userTime = u.get("year").toString() + "-" + u.get("mouth").toString();
            if(userTime.equals("2017-12")){
                userCount = 0;
            }
            userCount +=Integer.valueOf(u.get("number").toString());
            userCountTimeMap.put(userTime,userCount);
        }
        int lastUserCount = 0;
        for (int i = 0; i < orderResult.size(); i++) {
            Map<String,Object> item = orderResult.get(i);
            String time = item.get("year").toString() + "-" + item.get("mouth");
            int number = Integer.valueOf(item.get("number").toString());
            double amount = Double.valueOf(item.get("amount").toString());
            int userNumber = userCountTimeMap.get(time) != null ? userCountTimeMap.get(time) : lastUserCount;
            lastUserCount = userNumber;
            result.add(new UserMouthDto(time,userNumber,number,amount));
        }
        return result;
    }

    @Override
    public List<UserListDto> getUserList(Long startTime, Long endTime, String keyWord, long serviceId, int pageNumber, int pageSize) {
        StringBuilder sql = new StringBuilder("SELECT s.ServiceName,temp.*,m.ID,m.Phone,(m.Balance + m.Subsidy) AS TotalPrice,m.Balance,m.Subsidy\n" +
                "FROM\n" +
                "(\n" +
                "SELECT MAX(o.PickupDate) AS PickupDate,o.MemberID,SUM(o.TotalPrice) AS OrderTotalPrice,SUM(o.Balance) AS OrderBalance,SUM(o.Subsidy) AS OrderSubsidy FROM YUNYI_Order o \n" +
                "WHERE o.State = 2\n");
        if(startTime != null){
            sql.append(" AND o.PickupDate >= " + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(endTime != null){
            sql.append(" AND o.PickupDate <= " + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        sql.append("GROUP BY o.MemberID\n" +
                ") temp\n" +
                "LEFT JOIN YUNYI_Member m ON temp.MemberID = m.ID\n" +
                "LEFT JOIN YUNYI_ServiceOffice s ON m.ServiceID = s.ID\n" +
                "WHERE m.IsDeleted = 0 ");
        sql.append(" AND (m.Phone LIKE ? OR s.ServiceName LIKE ?) ");
        if(serviceId != 0){
            sql.append(" AND m.ServiceID = " + serviceId);
        }
        if(StringUtils.isEmpty(keyWord)){
            keyWord ="";
        }
        return jdbcTemplate.queryForList(getPageQuery(sql.toString(),"PickupDate",pageSize,pageNumber),"%"+keyWord+"%","%"+keyWord+"%").
                stream().
                map(r -> {
                    Long userId = Long.valueOf(r.get("ID").toString());
                    String userAccount = r.get("Phone").toString();
                    String serviceName = r.get("ServiceName") != null ? r.get("ServiceName").toString() : "";
                    double amountTotal = Double.valueOf(r.get("TotalPrice").toString());
                    double amountRecharge = Double.valueOf(r.get("Balance").toString());
                    double amountSubsidy = Double.valueOf(r.get("Subsidy").toString());
                    double orderAmountTotal = Double.valueOf(r.get("OrderTotalPrice").toString());
                    double orderAmountRecharge = Double.valueOf(r.get("OrderBalance").toString());
                    double orderAmountSubsidy = Double.valueOf(r.get("OrderSubsidy").toString());
                    return new UserListDto(userId,userAccount,serviceName)
                            .buildAmount(amountTotal,amountRecharge,amountSubsidy)
                            .buildOrderAmount(orderAmountTotal,orderAmountRecharge,orderAmountSubsidy);
        }).collect(Collectors.toList());
    }

    @Override
    public UserListStatisticsDto getUserListStatistics(Long startTime, Long endTime, String keyWord, long serviceId) {
        if(StringUtils.isEmpty(keyWord)){
            keyWord ="";
        }
        StringBuilder orderSql = new StringBuilder("SELECT ISNULL(SUM(og.Price),0) AS amount,ISNULL(COUNT(o.ID),0) AS number " +
                "FROM YUNYI_OrderGoods og\n" +
                "LEFT JOIN YUNYI_Order o ON og.OrderID = o.ID\n" +
                "LEFT JOIN YUNYI_Member m ON o.MemberID = m.ID\n" +
                "LEFT JOIN YUNYI_ServiceOffice s ON m.ServiceID = s.ID\n" +
                "WHERE o.State = 2 AND (m.Phone LIKE ? OR s.ServiceName LIKE ?) ");
        if(startTime != null){
            orderSql.append(" AND o.PickupDate >= " + DateUtil.timeStamp2Date(startTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(endTime != null){
            orderSql.append(" AND o.PickupDate <= " + DateUtil.timeStamp2Date(endTime,"yyyy-MM-dd HH:mm:ss") + "' ");
        }
        if(serviceId != 0){
            orderSql.append(" AND m.ServiceID = " + serviceId);
        }

        StringBuilder proxySql = new StringBuilder("SELECT SUM(Money) AS amount " +
                "FROM YUNYI_WithholdLog w " +
                "LEFT JOIN YUNYI_Member m ON m.ID = w.MemberID " +
                "LEFT JOIN YUNYI_ServiceOffice s ON m.ServiceID = s.ID " +
                "WHERE (m.Phone LIKE ? OR s.ServiceName LIKE ?) ");
        if(serviceId != 0){
            proxySql.append(" AND m.ServiceID = " + serviceId);
        }
        List<Map<String,Object>> orderResult = jdbcTemplate.queryForList(orderSql.toString(),"%"+keyWord+"%","%"+keyWord+"%");
        List<Map<String,Object>> proxyResult = jdbcTemplate.queryForList(proxySql.toString(),"%"+keyWord+"%","%"+keyWord+"%");

        int orderNumber = Integer.valueOf(orderResult.get(0).get("number").toString());
        double orderAmount = Double.valueOf(orderResult.get(0).get("amount").toString());
        double proxyAmount = Double.valueOf(proxyResult.get(0).get("amount").toString());
        return new UserListStatisticsDto(orderNumber,orderAmount,proxyAmount);
    }

    @Override
    public UserDetailDto getUserDetail(long userId) {
        String detailSql = "SELECT Phone,(m.Balance + m.Subsidy) AS TotalPrice,m.Balance,m.Subsidy,(m.RefundBalance + m.RefundSubsidy) AS TotalRefund,sp.Name AS SupplyName,se.ServiceName\n" +
                "FROM YUNYI_Member m\n" +
                "LEFT JOIN YUNYI_ServiceOffice se ON m.ServiceID = se.ID\n" +
                "LEFT JOIN YUNYI_Supply sp ON m.SupplyID = sp.ID\n" +
                "WHERE m.ID = ?";
        String orderSql = "SELECT ISNULL(SUM(TotalPrice),0) AS TotalPrice,ISNULL(SUM(Balance),0) AS Balance,ISNULL(SUM(Subsidy),0) AS Subsidy\n" +
                "FROM YUNYI_Order o\n" +
                "WHERE MemberID = ? AND State = 2";
        String proxySql = "SELECT TOP 1 ISNULL(Money, 0) AS TotalPrice ,BalanceTotal,SubsidyTotal\n" +
                "FROM YUNYI_WithholdLog w \n" +
                "WHERE w.MemberID = ?\n" +
                "ORDER BY InsertTime DESC ";

        Map<String,Object> detailResult = jdbcTemplate.queryForList(detailSql,userId).get(0);
        String userAccount = detailResult.get("Phone").toString();
        String siteName = detailResult.get("SupplyName").toString();
        String serviceName = detailResult.get("ServiceName") != null ? detailResult.get("ServiceName").toString() : "";

        double amountTotal = Double.valueOf(detailResult.get("TotalPrice").toString());
        double amountRecharge = Double.valueOf(detailResult.get("Balance").toString());
        double amountSubsidy = Double.valueOf(detailResult.get("Subsidy").toString());
        double refundAmountTotal = Double.valueOf(detailResult.get("TotalRefund").toString());

        double orderAmountTotal = 0;
        double orderAmountRecharge = 0;
        double orderAmountSubsidy = 0;

        double proxyAmountTotal = 0;
        double proxyAmountRecharge = 0;
        double proxyAmountSubsidy = 0;

        List<Map<String,Object>> orderResult = jdbcTemplate.queryForList(orderSql,userId);
        if(!CollectionUtils.isEmpty(orderResult)){
            Map<String,Object> item = orderResult.get(0);
            orderAmountTotal = Double.valueOf(item.get("TotalPrice").toString());
            orderAmountRecharge = Double.valueOf(item.get("Balance").toString());
            orderAmountSubsidy = Double.valueOf(item.get("Subsidy").toString());
        }

        List<Map<String,Object>> proxyResult = jdbcTemplate.queryForList(proxySql,userId);
        if(!CollectionUtils.isEmpty(proxyResult)){
            Map<String,Object> item = proxyResult.get(0);
            orderAmountTotal = Double.valueOf(item.get("TotalPrice").toString());
            orderAmountRecharge = Double.valueOf(item.get("BalanceTotal").toString());
            orderAmountSubsidy = Double.valueOf(item.get("SubsidyTotal").toString());
        }
        return new UserDetailDto(userId,userAccount,siteName,serviceName).
                buildAmount(amountTotal,amountRecharge,amountSubsidy,refundAmountTotal).
                buildOrderAmount(orderAmountTotal,orderAmountRecharge,orderAmountSubsidy).
                buildProxyAmount(proxyAmountTotal,proxyAmountRecharge,proxyAmountSubsidy);
    }

    @Override
    public List<UserRechargeDto> getUserRechargeList(long userId, int pageNumber, int pageSize) {
        String sql = "SELECT Money,InsertTime \n" +
                "FROM YUNYI_RechargeLog\n" +
                "WHERE MemberID = ?";
        return jdbcTemplate.queryForList(getPageQuery(sql,"InsertTime",pageSize,pageNumber),userId).stream().map(i -> {
            double amount = Double.valueOf(i.get("Money").toString());
            String time = i.get("InsertTime").toString();
            return new UserRechargeDto(amount,time);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserSubsidyDto> getUserSubsidyList(long userId, int pageNumber, int pageSize) {
        String sql = "SELECT Money,InsertTime \n" +
                "FROM YUNYI_SubsidyLog\n" +
                "WHERE MemberID = ?";
        return jdbcTemplate.queryForList(getPageQuery(sql,"InsertTime",pageSize,pageNumber),userId).stream().map(i -> {
            double amount = Double.valueOf(i.get("Money").toString());
            String time = i.get("InsertTime").toString();
            return new UserSubsidyDto(amount,time);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserProxyDto> getUserProxyList(long userId, int pageNumber, int pageSize) {
        String sql = "SELECT Money,BalanceTotal,SubsidyTotal,InsertTime,Summary,HandleingByPerson,InputMan FROM YUNYI_WithholdLog  WHERE MemberID = ?";
        return jdbcTemplate.queryForList(getPageQuery(sql,"InsertTime",pageSize,pageNumber),userId).stream().map(i -> {
            double total = Double.valueOf(i.get("Money").toString());
            double balance = Double.valueOf(i.get("BalanceTotal").toString());
            double subsidy = Double.valueOf(i.get("SubsidyTotal").toString());
            String time = i.get("InsertTime").toString();
            String summary = i.get("Summary").toString();
            String handler = i.get("HandleingByPerson").toString();
            String admin = i .get("InputMan").toString();
            return new UserProxyDto(total,balance,subsidy,time,summary,handler,admin);
        }).collect(Collectors.toList());
    }
}
