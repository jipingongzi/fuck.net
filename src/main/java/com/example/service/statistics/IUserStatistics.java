package com.example.service.statistics;

import com.example.service.query.BaseQueryService;
import com.example.service.statistics.dto.*;

import java.util.List;

/**
 * 用户统计查询
 */
public interface IUserStatistics extends BaseQueryService {
    /**
     * 下单最多的用户
     * @param serviceId 服务处id
     */
    List<UserOrderNumberDto> userOrderMost(Long startTime,Long endTime,long serviceId);

    /**
     * 按月统计用户信息
     */
    List<UserMouthDto> userMouth(Long startTime,Long endTime,long serviceId);

    /**
     * 用户列表查询
     * @param keyWord 根据用户账号或服务处查询
     * @param startTime 开始时间（领货日期）
     * @param endTime 结束时间（领货日期）
     */
    List<UserListDto> getUserList(Long startTime,Long endTime,String keyWord,long serviceId,int pageNumber,int pageSize);
    /**
     * 用户列表查询汇总
     * @param keyWord 根据用户账号或服务处查询
     * @param startTime 开始时间（领货日期）
     * @param endTime 结束时间（领货日期）
     */
    UserListStatisticsDto getUserListStatistics(Long startTime, Long endTime, String keyWord, long serviceId);

    /**
     * 查询用户详情
     * @param userId 用户id
     */
    UserDetailDto getUserDetail(long userId);

    /**
     * 用户充值记录
     */
    List<UserRechargeDto> getUserRechargeList(long userId,int pageNumber,int pageSize);

    /**
     * 用户补贴记录
     */
    List<UserSubsidyDto> getUserSubsidyList(long userId,int pageNumber,int pageSize);

    /**
     * 用户代扣记录
     */
    List<UserProxyDto> getUserProxyList(long userId,int pageNumber,int pageSize);

}
