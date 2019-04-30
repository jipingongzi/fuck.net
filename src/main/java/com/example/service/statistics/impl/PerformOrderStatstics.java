package com.example.service.statistics.impl;

import com.example.service.statistics.IPerformOrderStatistics;
import com.example.service.statistics.dto.PerformOrderDto;
import com.example.service.statistics.vo.PerformOrderVo;

import java.util.List;

public class PerformOrderStatstics implements IPerformOrderStatistics {
    @Override
    public List<PerformOrderDto> getPerformOrder(PerformOrderVo performOrderVo) {
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "\tt1.OrderNumber AS orderId,\n" +
                "\tt2.Phone AS orderUser,\n" +
                "\tt3.Name AS performNmae,\n" +
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
                "\tLEFT JOIN YUNYI_TheaterRoom t6 ON t3.TRID = t6.ID");
    }
}
