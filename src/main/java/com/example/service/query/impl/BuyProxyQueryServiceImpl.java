package com.example.service.query.impl;

import com.example.service.query.IBuyProxyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyProxyQueryServiceImpl implements IBuyProxyQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getBuyProxyList(int pageNumber, int pageSize) {
        String sql = "SELECT \n" +
                "t_farm.ID,\n" +
                "t_farm.Name,\n" +
                "CONVERT(varchar(100), t_farm.CTime, 120) AS CTime,\n" +
                "t_account.AccountCount,\n" +
                "t_good.GoodsCount,\n" +
                "t_supply.SupplyCount,\n" +
                "t_info.BuyPrice,\n" +
                "t_info.BuyGoodsCount\n" +
                "FROM \n" +
                "YUNYI_Farm t_farm\n" +
                "LEFT JOIN(\n" +
                "\tSELECT COUNT(ID) AS AccountCount,FarmID FROM YUNYI_FarmAccount GROUP BY FarmID\n" +
                ")t_account ON t_account.FarmID = t_farm.ID\n" +
                "LEFT JOIN(\n" +
                "\tSELECT SUM(Store) AS GoodsCount,FarmID FROM YUNYI_FarmGoods GROUP BY FarmID\n" +
                ")t_good ON t_good.FarmID = t_farm.ID\n" +
                "LEFT JOIN(\n" +
                "\tSELECT COUNT(id) AS SupplyCount,FarmID FROM YUNYI_SupplyFarmRelation GROUP BY FarmID\n" +
                ")t_supply ON t_supply.FarmID = t_farm.id \n" +
                "LEFT JOIN(\n" +
                "\tSELECT ID,SUM(Price * Amount) AS BuyPrice,SUM(Amount) AS BuyGoodsCount\n" +
                "\tFROM\n" +
                "\t(\n" +
                "\tselect c.Id,a.TotalPrice,a.Balance, b.IsFestival,b.GoodsName,b.Price,b.Amount \n" +
                "\t\t\t\t\t\t\t\t\tfrom YUNYI_Order a, YUNYI_OrderGoods b, YUNYI_Farm c \n" +
                "\t\t\t\t\t\t\t\t\twhere a.ID = b.OrderID and b.FarmID = c.ID and a.State in('1','2') \n" +
                "\t)t GROUP BY t.Id\n" +
                ")t_info ON t_info.id = t_farm.id\n" +
                "WHERE t_farm.FlagID = 2\n";
        return jdbcTemplate.queryForList(
                getPageQuery(sql,"CTime",pageSize,pageNumber)
        );
    }
}
