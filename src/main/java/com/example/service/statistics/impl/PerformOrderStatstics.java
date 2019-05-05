package com.example.service.statistics.impl;

import com.example.service.statistics.IPerformOrderStatistics;
import com.example.service.statistics.dto.PerformOrderDto;
import com.example.service.statistics.vo.PerformOrderVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformOrderStatstics implements IPerformOrderStatistics {

    @Autowired
    private final EntityManager entityManager;

    public PerformOrderStatstics(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<PerformOrderDto> getPerformOrder(PerformOrderVo performOrderVo) {
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "\tt1.OrderNumber AS orderId,\n" +
                "\tt2.Phone AS orderUser,\n" +
                "\tt3.Name AS performName,\n" +
                "\tt3.StartTime AS performStartTime,\n" +
                "\tt3.EndTime AS performEndTime,\n" +
                "\tt4.Name AS performCategory,\n" +
                "\tt5.Name AS performTheater,\n" +
                "\tt6.Name AS performRoom,\n" +
                "\tt3.OrderEndTime AS orderEndTime,\n" +
                "\tt1.CTime AS orderTime,\n" +
                "\tt1.TotalMoney AS orderAmount,\n" +
                "\tt1.SubscribeTicketCount AS reservationNumber,\n" +
                "\tt1.Status AS ordrStatus \n" +
                "FROM\n" +
                "\tYUNYI_PerformanceOrder t1\n" +
                "\tLEFT JOIN YUNYI_Member t2 ON t1.MemberID = t2.ID\n" +
                "\tLEFT JOIN YUNYI_Performance t3 ON t1.PID = t3.ID\n" +
                "\tLEFT JOIN YUNYI_PerformanceType t4 ON t3.PTID = t4.ID\n" +
                "\tLEFT JOIN YUNYI_Theater t5 ON t3.TID = t5.ID\n" +
                "\tLEFT JOIN YUNYI_TheaterRoom t6 ON t3.TRID = t6.ID\n");
        sql.append(sqlCondition(performOrderVo));
        Query query = entityManager.createNativeQuery(sql.toString());
        query.unwrap(SQLQuery.class).addScalar("orderId", StandardBasicTypes.STRING)
                .addScalar("user",StandardBasicTypes.STRING)
                .addScalar("performName",StandardBasicTypes.STRING)
                .addScalar("performStartTime",StandardBasicTypes.STRING)
                .addScalar("performEndTime",StandardBasicTypes.STRING)
                .addScalar("performCategory",StandardBasicTypes.STRING)
                .addScalar("performTheater",StandardBasicTypes.STRING)
                .addScalar("performRoom",StandardBasicTypes.STRING)
                .addScalar("orderEndTime",StandardBasicTypes.STRING)
                .addScalar("orderTime",StandardBasicTypes.STRING)
                .addScalar("orderAmount",StandardBasicTypes.STRING)
                .addScalar("reservationNumber",StandardBasicTypes.STRING)
                .addScalar("ordrStatus",StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(PerformOrderDto.class));
        List<PerformOrderDto> performOrderDtoList = new ArrayList<>();
        performOrderDtoList = query.getResultList();
        return performOrderDtoList;
    }
    private String sqlCondition(PerformOrderVo performOrderVo){
        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1\n");
        if(performOrderVo == null){
            return null;
        }
        if(!StringUtils.isEmpty(performOrderVo.getOrderId())){
            stringBuilder.append(" AND t1.OrderNumber = '%").append(performOrderVo.getOrderId()).append("%',\n");
        }
        if(!StringUtils.isEmpty(performOrderVo.getPerformName())){
            stringBuilder.append(" AND t3.Name = '%").append(performOrderVo.getPerformName()).append("%',\n");
        }
        if(!StringUtils.isEmpty(performOrderVo.getUser())){
            stringBuilder.append(" AND t2.Phone = '%").append(performOrderVo.getUser()).append("%',\n");
        }
        if(!StringUtils.isEmpty(performOrderVo.getPerformTheater())){
            stringBuilder.append(" AND t5.Name = '%").append(performOrderVo.getPerformTheater()).append("%',\n");
        }
        if(!StringUtils.isEmpty(performOrderVo.getPerformRoom())){
            stringBuilder.append(" AND t6.Name = '%").append(performOrderVo.getPerformRoom()).append("%',\n");
        }
        if(!StringUtils.isEmpty(performOrderVo.getPerformCategory())){
            stringBuilder.append(" AND t4.Name = '%").append(performOrderVo.getPerformCategory()).append("%',\n");
        }
        if(!StringUtils.isEmpty(performOrderVo.getOrdrStatus())){
            stringBuilder.append(" AND t1.Status = '%").append(performOrderVo.getOrdrStatus()).append("%',\n");
        }
        if(performOrderVo.getPerformStartTime() != null){
            stringBuilder.append(" AND t3.StartTime > '").append(performOrderVo.getPerformStartTime()).append("',\n");
        }
        if(performOrderVo.getPerformEndTime() != null){
            stringBuilder.append(" AND t3.StartTime < '").append(performOrderVo.getPerformEndTime()).append("',\n");
        }
        if(performOrderVo.getPerformStartTime() != null && performOrderVo.getPerformEndTime() != null){
            stringBuilder.append(" AND t3.StartTime BETWEEN '").append(performOrderVo.getPerformStartTime())
                    .append("' AND '").append(performOrderVo.getPerformEndTime()).append("',\n");
        }
        if(performOrderVo.getOrderStartTime() != null){
            stringBuilder.append(" AND t1.CTime > '").append(performOrderVo.getOrderStartTime()).append("',\n");
        }
        if(performOrderVo.getOrderEndTime() != null){
            stringBuilder.append(" AND t1.CTime < '").append(performOrderVo.getOrderEndTime()).append("',\n");
        }
        if(performOrderVo.getPerformStartTime() != null && performOrderVo.getPerformEndTime() != null){
            stringBuilder.append(" AND t.CTime BETWEEN '").append(performOrderVo.getOrderStartTime())
                    .append("' AND '").append(performOrderVo.getOrderEndTime()).append("',\n");
        }
        return stringBuilder.toString();
    }
}
