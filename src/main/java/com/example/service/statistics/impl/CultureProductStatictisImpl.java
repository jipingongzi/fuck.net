package com.example.service.statistics.impl;

import com.example.service.statistics.ICultureProductStatistic;
import com.example.service.statistics.dto.CultureProductOrderDto;
import com.example.service.statistics.vo.CultureProductOrderVo;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class CultureProductStatictisImpl implements ICultureProductStatistic {
    private final EntityManager entityManager;

    public CultureProductStatictisImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CultureProductOrderDto> getCultureProductOrders(CultureProductOrderVo cultureProductOrderVo) {
        StringBuilder sqlForResult = new StringBuilder("SELECT TOP\n" +
                "\t7 numComImg.* \n" +
                "FROM\n" +
                "\t(\n" +
                "SELECT\n" +
                "\tROW_NUMBER () OVER ( ORDER BY t1.CTIME DESC ) AS rownumber,\n" +
                "\tt1.OrderNumber AS orderNumber,\n" +
                "\tt2.Phone AS userName,\n" +
                "\tt3.Name AS performName,\n" +
                "\tt3.StartTime AS performStartTime,\n" +
                "\tt3.EndTime AS performEndTime,\n" +
                "\tt4.Name AS performType,\n" +
                "\tt5.Name AS performTheater,\n" +
                "\tt6.Name AS performTeaterRoom,\n" +
                "\tt3.OrderEndTime AS orderEndTime,\n" +
                "\tt1.CTime AS orderCreateTime,\n" +
                "\tt1.TotalMoney AS totalMoney,\n" +
                "\tt1.SubscribeTicketCount AS subscribeTicketCount,\n" +
                "\tt1.Status AS orderStatus\n" +
                "\tFROM YUNYI_PerformanceOrder t1\n" +
                "\tLEFT JOIN YUNYI_Member t2 ON t1.MemberID = t2.ID\n" +
                "\tLEFT JOIN YUNYI_Performance t3 ON t1.TID = t3.ID\n" +
                "\tLEFT JOIN YUNYI_PerformanceType t4 ON t3.PTID = t4.ID\n" +
                "\tLEFT JOIN YUNYI_Theater t5 ON t3.TID = t5.ID\n" +
                "\tLEFT JOIN YUNYI_TheaterRoom t6 ON t3.TRID = t6.ID\n")
                .append(getSqlForCondition(cultureProductOrderVo)
                        .append("\t) AS numComImg \n" +
                "WHERE\n" +
                "\trownumber BETWEEN \n"));
        Integer start = (cultureProductOrderVo.getPageNumber() - 1) * cultureProductOrderVo.getPageSize() + 1;
        Integer end = start + cultureProductOrderVo.getPageSize();
        sqlForResult.append(start).append(" AND ").append(end).append("\n");
        Query query = entityManager.createNativeQuery(sqlForResult.toString());
        query.unwrap(SQLQuery.class).addScalar("rownumber", StandardBasicTypes.INTEGER)
                .addScalar("orderNumber",StandardBasicTypes.STRING)
                .addScalar("userName",StandardBasicTypes.STRING)
                .addScalar("performName",StandardBasicTypes.STRING)
                .addScalar("performStartTime",StandardBasicTypes.STRING)
                .addScalar("performEndTime",StandardBasicTypes.STRING)
                .addScalar("performType",StandardBasicTypes.STRING)
                .addScalar("performTheater",StandardBasicTypes.STRING)
                .addScalar("performTeaterRoom",StandardBasicTypes.STRING)
                .addScalar("orderEndTime",StandardBasicTypes.STRING)
                .addScalar("orderCreateTime",StandardBasicTypes.STRING)
                .addScalar("totalMoney",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("subscribeTicketCount",StandardBasicTypes.INTEGER)
                .addScalar("orderStatus",StandardBasicTypes.INTEGER);
        List<CultureProductOrderDto> cultureProductOrderDtoList = query.getResultList();
        return cultureProductOrderDtoList;
    }
    private StringBuilder getSqlForCondition(CultureProductOrderVo cultureProductOrderVo){
        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1\n");
        if(cultureProductOrderVo.getServiceId() != 0){
            stringBuilder.append(" AND t2.ServiceID = ").append(cultureProductOrderVo.getServiceId()).append("\n");
        }
        if(cultureProductOrderVo == null){
            return new StringBuilder("");
        }
        if(!StringUtils.isEmpty(cultureProductOrderVo.getOrderNumber())){
            stringBuilder.append(" AND t1.OrderNumber LIKE '").append(cultureProductOrderVo.getOrderNumber()).append("'\n");
        }
        if(!StringUtils.isEmpty(cultureProductOrderVo.getPerformTheaterRoom())){
            stringBuilder.append(" AND t6.ID = '").append(cultureProductOrderVo.getPerformTheaterRoom()).append("'\n");
        }
        if(!StringUtils.isEmpty(cultureProductOrderVo.getUserName())){
            stringBuilder.append(" AND t2.Phone LIKE '").append(cultureProductOrderVo.getUserName()).append("'\n");
        }
        if(!StringUtils.isEmpty(cultureProductOrderVo.getOrderStatus())){
            stringBuilder.append(" AND t1.Status = '").append(cultureProductOrderVo.getOrderStatus()).append("'\n");
        }
        if(cultureProductOrderVo.getLeaveForStartTime() != null && !StringUtils.isEmpty(cultureProductOrderVo.getLeaveForStartTime())){
            stringBuilder.append(" AND t1.LeaveForTime > '").append(cultureProductOrderVo.getLeaveForStartTime().minusDays(1)).append("'\n");
        }
        if(cultureProductOrderVo.getLeaveForEndTime() != null && !StringUtils.isEmpty(cultureProductOrderVo.getLeaveForEndTime())){
            stringBuilder.append(" AND t1.LeaveForTime < '").append(cultureProductOrderVo.getLeaveForEndTime().plusDays(1)).append("'\n");
        }

        if(cultureProductOrderVo.getOrderStartTime() != null && !StringUtils.isEmpty(cultureProductOrderVo.getOrderStartTime())){
            stringBuilder.append(" AND t1.CTime > '").append(cultureProductOrderVo.getOrderStartTime().minusDays(1)).append("'\n");
        }
        if(cultureProductOrderVo.getOrderEndTime() != null && !StringUtils.isEmpty(cultureProductOrderVo.getOrderEndTime())){
            stringBuilder.append(" AND t1.CTime < '").append(cultureProductOrderVo.getOrderEndTime().plusDays(1)).append("'\n");
        }
        if(cultureProductOrderVo.getPerformType() != null && !StringUtils.isEmpty(cultureProductOrderVo.getPerformType())){
            stringBuilder.append(" AND t4.ID = ").append(cultureProductOrderVo.getPerformType()).append("\n");
        }

        return stringBuilder;
    }
}
