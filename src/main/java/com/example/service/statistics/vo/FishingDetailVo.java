package com.example.service.statistics.vo;

import lombok.Data;

import java.util.List;

@Data
public class FishingDetailVo {
    //订单详情id
    private Integer ID;
    //订单id关联订单表
    private Integer OrderID;
    //项目id
    private Integer GSPMID;
    //项目类型。1、垂钓；2、就餐；3、其他
    private Integer GSPMTypeID;
    //就餐项目类型。1、炒菜；2、火锅
    private Integer GSPMTypeID2;
    //参与人数
    private Integer ParticipantCount;
    //随行工作人员数量。(就餐需要用到此字段)
    private Integer RetinueCount;
    //就餐包间。(就餐需要用到该字段)
    private Integer RepastRoomID;
    //本次垂钓总斤数。包含超出的部分斤数
    private Integer AnglingWeight;
    //垂钓结果状态。(1、未提交；2、已提交)
    private Integer AnglingStatus;
    //免费垂钓斤数用完后超出的部分垂钓斤数。
    private Integer OverStepWeight;
    //项目单价
    private Double Price;
    //项目总金额
    private Double TotalMoney;
    //垂钓经办人
    private String OperatorPerson;
    //就餐项目类型 1炒菜2火锅
    private String MealType;
    //导航栏title
    private List<String> TabTitle;
}
