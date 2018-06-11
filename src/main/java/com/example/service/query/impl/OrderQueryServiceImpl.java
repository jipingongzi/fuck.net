package com.example.service.query.impl;

import com.example.service.query.IOrderQueryService;
import com.example.service.query.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderQueryServiceImpl implements IOrderQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> getGoodsOrderList(int pageNumber, int pageSize) {
        String sql = "SELECT  \n" +
                "       t_order.OrderNo\n" +
                "    ,t_member.Phone    \n" +
                "      ,t_order.GoodsAmount\n" +
                "      ,t_order.TotalPrice\n" +
                "      ,t_order.Balance\n" +
                "      ,t_order.Subsidy\n" +
                "      ,t_order.InsertTime\n" +
                "      ,t_order.PickupDate\n" +
                "   ,t_supply.Name as SupplyName\n" +
                "\t\t\t,t_order.IsDeliver\n" +
                "   ,t_farm.SupplyOfGoods as SupplyOfGoods\n" +
                "\t ,t_order.ReceiveTime\n" +
                "   \n" +
                "  FROM YUNYI_Order t_order\n" +
                "\n" +
                "LEFT JOIN YUNYI_Member t_member ON t_order.MemberID = t_member.ID\n" +
                "\n" +
                "LEFT JOIN YUNYI_Supply t_supply ON t_order.SupplyID = t_supply.ID\n" +
                "\n" +
                "LEFT JOIN \n" +
                "(SELECT \n" +
                "DISTINCT(YUNYI_Farm.Name) AS SupplyOfGoods ,\n" +
                "YUNYI_OrderGoods.OrderID\n" +
                "FROM YUNYI_Farm \n" +
                "LEFT JOIN YUNYI_OrderGoods on\n" +
                "YUNYI_Farm.ID = YUNYI_OrderGoods.FarmID) t_farm\n" +
                "\n" +
                "ON t_order.ID = t_farm.OrderID \n";
            return jdbcTemplate.queryForList(
                getPageQuery(sql,"InsertTime",pageSize,pageNumber)
        );
    }

    @Override
    public List<Map<String, Object>> getFestivalOrderList(int pageNumber, int pageSize) {
        String sql = "SELECT\n" +
                "                t_supply.name as SupplyName\n" +
                "                ,t_order.GoodsAmount\n" +
                "\t\t\t\t\t\t\t\t,t_order.TotalPrice\n" +
                "\t\t\t\t\t\t\t\t,t_order.PickupDate\n" +
                "                ,t_order.IsDeliver\n" +
                "\t\t\t\t\t\t\t\t,t_order.OrderNo\n" +
                "                FROM YUNYI_Order t_order\n" +
                "              \n" +
                "                LEFT JOIN YUNYI_Supply t_supply ON t_order.SupplyID = t_supply.ID\n" +
                "\n" +
                "                WHERE IsFestival=1 AND  State IN(1,2) AND IsDeliver = 1 ";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"PickupDate",pageSize,pageNumber)
        );
    }

    @Override
    public List<Map<String, Object>> getRechargeOrderList(int pageNumber, int pageSize) {
        String sql = "SELECT \n" +
                "t_member.Phone \n" +
                ",t_recharge.OrderNo\n" +
                ",t_recharge.Money\n" +
                ",t_recharge.InsertTime\n" +
                "FROM YUNYI_RechargeLog t_recharge \n" +
                "\n" +
                "LEFT JOIN YUNYI_Member t_member ON t_recharge.MemberID = t_member.ID";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"InsertTime",pageSize,pageNumber)
        );
    }

    @Override
    public List<Map<String, Object>> getRefundOrderList(int pageNumber, int pageSize) {
        String sql = "SELECT \n" +
                "t_order.OrderNo\n" +
                ",t_member.Phone\n" +
                ",t_ordergoods.GoodsName\n" +
                ",t_supply.Name\n" +
                ",t_ordergoods.RefundPrice\n" +
                ",t_ordergoods.RefundPrice/6*5 AS RefundSubsidyPrice\n" +
                ",t_ordergoods.RefundPrice/6 AS RefundRechargePrice\n" +
                ",t_order.InsertTime\n" +
                ",t_order.PickupDate\n" +
                ",t_ordergoods.RefundTime\n" +
                ",t_ordergoods.RefundState\n" +
                "\n" +
                "FROM YUNYI_OrderGoods t_ordergoods\n" +
                "\n" +
                "INNER JOIN YUNYI_Order t_order ON t_order.ID = t_ordergoods.OrderID\n" +
                "INNER JOIN YUNYI_Member t_member ON t_order.MemberID = t_member.ID\n" +
                "INNER JOIN YUNYI_Supply t_supply ON t_order.SupplyID = t_supply.ID\n" +
                "\n" +
                "WHERE t_ordergoods.RefundState!=0 AND t_ordergoods.RefundState!=3";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"RefundTime",pageSize,pageNumber)
        );
    }

    @Override
    public List<Map<String, Object>> getSupplyGoodsOrderList(int pageNumber, int pageSize) {
        String sql = "SELECT\n" +
                "                DISTINCT(YUNYI_Goods.Name),\n" +
                "                YUNYI_Goods.SerialNum,\n" +
                "                t1.goodsAmount,\n" +
                "                t1.goodsPrice,\n" +
                "                t1.id\n" +
                "                from (SELECT \n" +
                "                YUNYI_OrderGoods.GoodsID as id,\n" +
                "                SUM(YUNYI_OrderGoods.Amount) AS goodsAmount,\n" +
                "                SUM (YUNYI_OrderGoods.Price*YUNYI_OrderGoods.Amount)AS goodsPrice\n" +
                "                FROM  YUNYI_OrderGoods\n" +
                "                INNER JOIN YUNYI_Order  ON YUNYI_Order.ID = YUNYI_OrderGoods.OrderID\n" +
                "                where  YUNYI_Order.State in(1,2) AND YUNYI_Order.IsFestival=0\n" +
                "                GROUP BY YUNYI_OrderGoods.GoodsID) t1\n" +
                "                INNER JOIN YUNYI_Goods ON YUNYI_Goods.ID = t1.id";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"SerialNum",pageSize,pageNumber)
        );
    }

    @Override
    public List<Map<String, Object>> getSupplyFestivalOrderList(int pageNumber, int pageSize) {
        String sql = "SELECT\n" +
                "YUNYI_Goods.Name,\n" +
                "YUNYI_Goods.SerialNum,\n" +
                "t1.goodsAmount,\n" +
                "t1.goodsPrice,\n" +
                "t1.id\n" +
                "from (SELECT \n" +
                "YUNYI_OrderGoods.GoodsID as id,\n" +
                "SUM(YUNYI_OrderGoods.Amount) AS goodsAmount,\n" +
                "SUM (YUNYI_OrderGoods.Price*YUNYI_OrderGoods.Amount)AS goodsPrice\n" +
                "FROM  YUNYI_OrderGoods\n" +
                "INNER JOIN YUNYI_Order  ON YUNYI_Order.ID = YUNYI_OrderGoods.OrderID\n" +
                "where  YUNYI_Order.State in(1,2) AND YUNYI_Order.IsFestival=1 AND YUNYI_Order.IsDeliver=1\n" +
                "GROUP BY YUNYI_OrderGoods.GoodsID) t1\n" +
                "INNER JOIN YUNYI_Goods ON YUNYI_Goods.ID = t1.id";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"Name",pageSize,pageNumber)
        );
    }
}
