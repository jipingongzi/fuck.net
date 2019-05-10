package com.example.service.statistics.impl;

import com.example.service.statistics.dto.FishingDetailDto;
import com.example.service.statistics.dto.FishingTicketDto;
import com.example.service.statistics.vo.FishingTicketVo;
import com.example.service.statistics.vo.PerformOrderVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class FishingTicketStatictis {

    private final EntityManager entityManager;

    @Autowired
    public FishingTicketStatictis(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<FishingTicketVo> getFishingTicketInfo(FishingTicketVo fishingTicketVo){
        StringBuilder stringBuilder = new StringBuilder("SELECT\n" +
                "\tt1.ID AS id,\n" +
                "\tt1.OrderNumber AS OrderId,\n" +
                "\tt2.Phone AS userName,\n" +
                "\tt3.NAME AS guaranteeSite,\n" +
                "\tt1.LeaveForTime AS leaveForTime,\n" +
                "\tt1.ReceptionProjectCount AS ReceptionProjectCount,\n" +
                "\tt1.CTime AS CTime,\n" +
                "\tt1.OrderTotalMoney AS orderTotalMoney,\n" +
                "\tt1.OrderBalancTotal AS orderBalancTotal,\n" +
                "\tt1.OrderSubsidyTotal AS orderSubsidyTotal,\n" +
                "\tt1.Status AS Status\n" +
                "FROM\n" +
                "\tYUNYI_GuaranteeServiceOrder t1\n" +
                "\tLEFT JOIN YUNYI_Member t2 ON t1.MemberID = t2.ID\n" +
                "\tLEFT JOIN YUNYI_GuaranteeServiceManage t3 ON t1.GSMID = t3.ID");
        stringBuilder.append(sqlCondition(fishingTicketVo));
        Query query = entityManager.createNativeQuery(stringBuilder.toString());
        query.unwrap(SQLQuery.class).addScalar("id",StandardBasicTypes.INTEGER)
                .addScalar("orderId", StandardBasicTypes.STRING)
                .addScalar("userName",StandardBasicTypes.STRING)
                .addScalar("guaranteeSite",StandardBasicTypes.STRING)
                .addScalar("leaveForTime",StandardBasicTypes.STRING)
                .addScalar("ReceptionProjectCount",StandardBasicTypes.INTEGER)
                .addScalar("CTime",StandardBasicTypes.STRING)
                .addScalar("OrderTotalMoney",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("OrderBalancTotal",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("OrderSubsidyTotal",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("Status",StandardBasicTypes.INTEGER)
                .setResultTransformer(Transformers.aliasToBean(FishingTicketDto.class));
        List<FishingTicketDto> fishingTicketDtoList = query.getResultList();
        //增加序号、修改订单状态
        List<FishingTicketVo> fishingTicketVoList = new ArrayList<>();
        Integer i = 1;
        for(FishingTicketDto fishingTicketDto:fishingTicketDtoList){
            FishingTicketVo fishingTicketVo1 = new FishingTicketVo();
            BeanUtils.copyProperties(fishingTicketDto,fishingTicketVo1);
            fishingTicketVo1.setNumber(i++);
//            fishingTicketVo1.setOperation("详情");
//            fishingTicketVo.setOrderTotalMoney(fishingTicketDto.getOrderBalancTotal().add(fishingTicketDto.getOrderSubsidyTotal()));
            switch (fishingTicketDto.getStatus()){
                case 1:
                    fishingTicketVo1.setStatus("已预约");
                    break;
                case 2:
                    fishingTicketVo1.setStatus("预约成功");
                    break;
                case 3:
                    fishingTicketVo1.setStatus("已取消");
                    break;
                default:
                    break;
            }
            fishingTicketVoList.add(fishingTicketVo1);
        }
        return fishingTicketVoList;
    }
    private String sqlCondition(FishingTicketVo fishingTicketVo){
        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1\n");
        if(fishingTicketVo.getServiceId() != 0){
            stringBuilder.append(" AND t2.ServiceID = ").append(fishingTicketVo.getServiceId()).append("\n");
        }
        if(fishingTicketVo == null){
            return "";
        }
        if(!StringUtils.isEmpty(fishingTicketVo.getOrderId())){
            stringBuilder.append(" AND t1.OrderNumber LIKE '").append(fishingTicketVo.getOrderId()).append("'\n");
        }
        if(!StringUtils.isEmpty(fishingTicketVo.getGuaranteeSite())){
            stringBuilder.append(" AND t3.NAME LIKE '").append(fishingTicketVo.getGuaranteeSite()).append("'\n");
        }
        if(!StringUtils.isEmpty(fishingTicketVo.getUserName())){
            stringBuilder.append(" AND t2.Phone LIKE '").append(fishingTicketVo.getUserName()).append("'\n");
        }
        if(!StringUtils.isEmpty(fishingTicketVo.getStatus())){
            stringBuilder.append(" AND t1.Status = '").append(fishingTicketVo.getStatus()).append("'\n");
        }
        if(fishingTicketVo.getLeaveForStartTime() != null && !StringUtils.isEmpty(fishingTicketVo.getLeaveForStartTime())){
            stringBuilder.append(" AND t1.LeaveForTime > '").append(fishingTicketVo.getLeaveForStartTime()).append("'\n");
        }
        if(fishingTicketVo.getLeaveForEndTime() != null && !StringUtils.isEmpty(fishingTicketVo.getLeaveForEndTime())){
            stringBuilder.append(" AND t1.LeaveForTime < '").append(fishingTicketVo.getLeaveForEndTime()).append("'\n");
        }
//        if(fishingTicketVo.getLeaveForStartTime() != null && fishingTicketVo.getLeaveForEndTime() != null){
//            stringBuilder.append(" AND t1.LeaveForTime BETWEEN '").append(fishingTicketVo.getLeaveForStartTime())
//                    .append("' AND '").append(fishingTicketVo.getLeaveForEndTime()).append("'\n");
//        }
        if(fishingTicketVo.getOrderStartTime() != null && !StringUtils.isEmpty(fishingTicketVo.getOrderStartTime())){
            stringBuilder.append(" AND t1.CTime > '").append(fishingTicketVo.getOrderStartTime()).append("'\n");
        }
        if(fishingTicketVo.getOrderEndTime() != null && !StringUtils.isEmpty(fishingTicketVo.getOrderEndTime())){
            stringBuilder.append(" AND t1.CTime < '").append(fishingTicketVo.getOrderEndTime()).append("'\n");
        }
//        if(fishingTicketVo.getOrderStartTime() != null && fishingTicketVo.getOrderEndTime() != null){
//            stringBuilder.append(" AND t.CTime BETWEEN '").append(fishingTicketVo.getOrderStartTime())
//                    .append("' AND '").append(fishingTicketVo.getOrderEndTime()).append("'\n");
//        }
        return stringBuilder.toString();
    }

    public List<FishingDetailDto> getOrderDetsil(Integer orderId){
        StringBuilder stringBuilder = new StringBuilder("SELECT\n" +
                "\tt2.*,\n" +
                "CASE\n" +
                "\tt2.GSPMTypeID2 \n" +
                "\tWHEN 1 THEN\n" +
                "\t'炒菜' \n" +
                "\tWHEN 2 THEN\n" +
                "\t'火锅' \n" +
                "\tEND AS MealType, t3.NAME AS tabName \n" +
                "FROM\n" +
                "\tYUNYI_GuaranteeServiceOrderDetails t2\n" +
                "\tLEFT JOIN YUNYI_GuaranteeServiceProjectManage t3 ON t2.GSPMID = t3.ID \n" +
                "WHERE\n" +
                "\tt2.OrderID = ");
        stringBuilder.append(orderId);
        Query query = entityManager.createNativeQuery(stringBuilder.toString());
        query.unwrap(SQLQuery.class).addScalar("ID",StandardBasicTypes.INTEGER)
                .addScalar("OrderID",StandardBasicTypes.INTEGER)
                .addScalar("GSPMID",StandardBasicTypes.INTEGER)
                .addScalar("GSPMTypeID",StandardBasicTypes.INTEGER)
                .addScalar("GSPMTypeID2",StandardBasicTypes.INTEGER)
                .addScalar("ParticipantCount",StandardBasicTypes.INTEGER)
                .addScalar("RetinueCount",StandardBasicTypes.INTEGER)
                .addScalar("RepastRoomID",StandardBasicTypes.INTEGER)
                .addScalar("AnglingWeight",StandardBasicTypes.INTEGER)
                .addScalar("AnglingStatus",StandardBasicTypes.INTEGER)
                .addScalar("OverStepWeight",StandardBasicTypes.INTEGER)
                .addScalar("Price",StandardBasicTypes.DOUBLE)
                .addScalar("TotalMoney",StandardBasicTypes.DOUBLE)
                .addScalar("OperatorPerson",StandardBasicTypes.STRING)
                .addScalar("MealType",StandardBasicTypes.STRING)
                .addScalar("tabName",StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(FishingDetailDto.class));
        List<FishingDetailDto> fishingDetailDtoList = new ArrayList<>();
        fishingDetailDtoList = query.getResultList();
        return fishingDetailDtoList;
    }
}
