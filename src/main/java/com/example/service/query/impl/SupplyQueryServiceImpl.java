package com.example.service.query.impl;

import com.example.service.query.ISupplyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SupplyQueryServiceImpl implements ISupplyQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getSupplyList(int pageNumber, int pageSize) {
        String baseInfoSql = "SELECT \n" +
                "t_supply.ID,\n" +
                "t_supply.Name,\n" +
                "t_account.AccountCount,\n" +
                "t_fram.FarmCount,\n" +
                "t_member.MemberCount,\n" +
                "t_buy_proxy.ProxyCount\n" +
                "FROM\n" +
                "YUNYI_Supply t_supply\n" +
                "LEFT JOIN(\n" +
                "\tSELECT COUNT(id) AS AccountCount,SupplyID FROM YUNYI_SupplyAccount GROUP BY SupplyID\n" +
                ") t_account ON t_supply.id = t_account.SupplyID\n" +
                "LEFT JOIN(\n" +
                "\tSELECT COUNT(DISTINCT FarmID) AS FarmCount,SupplyID FROM YUNYI_SupplyFarmRelation WHERE FarmID IN (SELECT ID FROM YUNYI_Farm WHERE FlagId = 1) GROUP BY SupplyID\n" +
                ") t_fram ON t_supply.id = t_fram.SupplyID\n" +
                "LEFT JOIN(\n" +
                "\tSELECT COUNT(id) AS MemberCount,SupplyID FROM YUNYI_Member WHERE IsDeleted = 0 GROUP BY SupplyID\n" +
                ") t_member ON t_supply.id = t_member.SupplyID\n" +
                "LEFT JOIN(\n" +
                "\tSELECT COUNT(DISTINCT FarmID) AS ProxyCount,SupplyID FROM YUNYI_SupplyFarmRelation WHERE FarmID IN (SELECT ID FROM YUNYI_Farm WHERE FlagId = 2) GROUP BY SupplyID\n" +
                ") t_buy_proxy ON t_supply.id = t_buy_proxy.SupplyID\n";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(getPageQuery(baseInfoSql,"ID",pageSize,pageNumber));
        //查询供应点代购商品数
        String goodsCountSql = "SELECT COUNT(ID) AS AgencyBuyingCount FROM YUNYI_Goods \n" +
                "WHERE  TypeID = 3 AND IsPutaway = 2 AND \n" +
                "CategoryID IN (\n" +
                "\tSELECT ID FROM YUNYI_Category WHERE TypeID = 3\n" +
                ")";
        Object AgencyBuyingCount = jdbcTemplate.queryForList(goodsCountSql).get(0).get("AgencyBuyingCount");
        result.forEach(t -> t.put("AgencyBuyingCount",AgencyBuyingCount));
        //出售价格和数量
        String numberAndMoneySql = "SELECT SUM(Price * Amount) AS AgencyBuyingSellProductTotal,COUNT(ID) AS AgencyBuyingSellProductCount\n" +
                "FROM YUNYI_OrderGoods \n" +
                "WHERE OrderID IN (SELECT ID FROM YUNYI_Order WHERE SupplyID = ? AND State = 2)\n" +
                "AND GoodsID IN (SELECT ID AS AgencyBuyingCount FROM YUNYI_Goods WHERE  TypeID = 3 AND IsPutaway = 2 AND CategoryID IN (SELECT ID FROM YUNYI_Category WHERE TypeID = 3))\n";
        result.forEach(t -> {
            Map<String,Object> numberAndMoney = jdbcTemplate.queryForList(numberAndMoneySql,t.get("ID")).get(0);
            t.put("AgencyBuyingSellProductTotal",numberAndMoney.get("AgencyBuyingSellProductTotal"));
            t.put("AgencyBuyingSellProductCount",numberAndMoney.get("AgencyBuyingSellProductCount"));
        });
        return result;
    }


}
