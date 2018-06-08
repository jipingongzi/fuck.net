package com.example.service.query.impl;

import com.example.service.query.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserQueryServiceImpl implements IUserQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUserList(int pageNumber,int pageSize) {
        String sql = "SELECT  \n" +
                "\t\t\t\t\t\t\tt_member.ID\n" +
                "\t\t\t\t\t\t\t,t_member.Phone    \n" +
                "\t\t\t\t\t\t\t,t_member.Balance + t_member.Subsidy AS Money\n" +
                "\t\t\t\t\t\t\t,t_member.Balance AS Balance\n" +
                "\t\t\t\t\t\t\t,t_member.Subsidy AS Subsidy\n" +
                "\t\t\t\t\t\t\t,t_order.order_total AS OrderPrice\n" +
                "\t\t\t\t\t\t\t,t_order.order_balance AS OrderRechargeTotal\n" +
                "\t\t\t\t\t\t\t,t_order.order_Subside AS OrderSubsidyTotal \n" +
                "\t\t\t\t\t\t\t,t_member.RefundBalance AS RefundBalance\n" +
                "\t\t\t\t\t\t\t,t_member.RefundSubsidy AS RefundSubsidy\n" +
                "\t\t\t\t\t\t\t,t_with_hold.with_hold_money AS WithholdTotal\n" +
                "\t\t\t\t\t\t\t,t_with_hold.with_hold_balance AS WithholdRechargePrice\n" +
                "\t\t\t\t\t\t\t,t_with_hold.with_hold_Subside AS WithholdSubsidyTotal\n" +
                "\t\t\t\t\t\t\t,t_supply.Name AS SupplyName\n" +
                "\t\t\t\t\t\t\t,t_member.InsertTime AS InsertTime\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t  FROM YUNYI_Member t_member\n" +
                "\t\t\t\t\t\t\tLEFT JOIN\n" +
                "\t\t\t\t\t\t\t( SELECT MemberID,SUM(Balance + Subsidy) AS order_total,\n" +
                "\t\t\t\t\t\t\t\tSUM(Balance) AS order_balance,\n" +
                "\t\t\t\t\t\t\t\tSUM(Subsidy) AS order_Subside\n" +
                "\t\t\t\t\t\t\t\tfrom YUNYI_Order group by MemberID) t_order ON t_member.ID = t_order.MemberID \n" +
                "\t\t\t\t\t\t LEFT JOIN\n" +
                "\t\t\t\t\t\t\t( SELECT\n" +
                "\t\t\t\t\t\t\t\tMemberID,\n" +
                "\t\t\t\t\t\t\t\tMoney AS with_hold_money,\n" +
                "\t\t\t\t\t\t\t\tBalanceTotal AS with_hold_balance,\n" +
                "\t\t\t\t\t\t\t\tSubsidyTotal AS with_hold_Subside\n" +
                "\t\t\t\t\t\t\t\tfrom YUNYI_WithholdLog) \n" +
                "\t\t\t\t\t\t\tt_with_hold ON t_member.ID = t_with_hold.MemberID \n" +
                "\t\t\t\t     LEFT JOIN YUNYI_Supply t_supply ON t_supply.id = t_member.SupplyID WHERE t_member.isDeleted <> 1";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"InsertTime",pageSize,pageNumber)
        );
    }

    @Override
    public List<Map<String, Object>> getCosytList(int pageNumber, int pageSize) {
        String sql = "SELECT \n" +
                "YUNYI_Member.Phone,\n" +
                "YUNYI_WithholdLog.OldBalanceTotal,\n" +
                "YUNYI_WithholdLog.BalanceTotal,\n" +
                "YUNYI_WithholdLog.OldSubsidyTotal,\n" +
                "YUNYI_WithholdLog.SubsidyTotal,\n" +
                "(YUNYI_WithholdLog.OldBalanceTotal - YUNYI_WithholdLog.BalanceTotal) AS NewBalanceTotal,\n" +
                "(YUNYI_WithholdLog.OldSubsidyTotal - YUNYI_WithholdLog.SubsidyTotal) AS NewSubsidyTotal,\n" +
                "CONVERT(varchar(100), YUNYI_WithholdLog.InsertTime, 120) AS InsertTime,\n" +
                "YUNYI_WithholdLog.Summary,\n" +
                "YUNYI_WithholdLog.HandleingByPerson\n" +
                "\n" +
                "FROM YUNYI_WithholdLog \n" +
                "LEFT JOIN YUNYI_Member ON YUNYI_Member.id = YUNYI_WithholdLog.MemberID";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"InsertTime",pageSize,pageNumber)
        );
    }
}
