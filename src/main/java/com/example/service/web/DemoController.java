package com.example.service.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @PostMapping("/Backstage/Farm/GetDatagrid")
    public String basement(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"IDX\":1,\"ID\":15,\"Weight\":0,\"Name\":\"紫竹院代购\",\"CagegoryIDs\":\"15,16,17\",\"FlagID\":1,\"CTime\":\"2018-05-09 22:56:28\",\"AccountCount\":1,\"GoodsCount\":0,\"BuyGoodsCount\":0,\"BuyPrice\":0.00,\"SupplyCount\":3},{\"IDX\":2,\"ID\":14,\"Weight\":0,\"Name\":\"海运仓代购\",\"CagegoryIDs\":\"15,16,17\",\"FlagID\":1,\"CTime\":\"2018-05-09 22:56:28\",\"AccountCount\":1,\"GoodsCount\":0,\"BuyGoodsCount\":0,\"BuyPrice\":0.00,\"SupplyCount\":1},{\"IDX\":3,\"ID\":13,\"Weight\":0,\"Name\":\"采购组\",\"CagegoryIDs\":\"15,16,17,19,20,22,23,24,25,26\",\"FlagID\":1,\"CTime\":\"2018-05-09 22:56:28\",\"AccountCount\":1,\"GoodsCount\":127067,\"BuyGoodsCount\":13111,\"BuyPrice\":261084.74,\"SupplyCount\":2},{\"IDX\":4,\"ID\":12,\"Weight\":0,\"Name\":\"水厂\",\"CagegoryIDs\":\"14\",\"FlagID\":1,\"CTime\":\"2018-05-09 22:56:28\",\"AccountCount\":1,\"GoodsCount\":2051,\"BuyGoodsCount\":3424,\"BuyPrice\":48294.00,\"SupplyCount\":2},{\"IDX\":5,\"ID\":11,\"Weight\":0,\"Name\":\"上辛堡基地\",\"CagegoryIDs\":\"1,2,3\",\"FlagID\":1,\"CTime\":\"2018-05-09 22:56:28\",\"AccountCount\":1,\"GoodsCount\":131594,\"BuyGoodsCount\":58391,\"BuyPrice\":165729.01,\"SupplyCount\":2},{\"IDX\":6,\"ID\":10,\"Weight\":0,\"Name\":\"顺义基地\",\"CagegoryIDs\":\"1,2,3\",\"FlagID\":1,\"CTime\":\"2018-05-09 22:56:28\",\"AccountCount\":1,\"GoodsCount\":25904,\"BuyGoodsCount\":22515,\"BuyPrice\":133083.52,\"SupplyCount\":2}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/AgencyBuying/GetDatagrid")
    public String buyproxy(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"IDX\":1,\"ID\":16,\"Weight\":0,\"Name\":\"代购\",\"CagegoryIDs\":\"15,16,19,24,28,29,30,31,32,37,38\",\"FlagID\":2,\"CTime\":\"2018-05-10 09:50:30\",\"AccountCount\":1,\"GoodsCount\":10985,\"BuyGoodsCount\":5386,\"BuyPrice\":168461.06,\"SupplyCount\":4}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Supply/GetDatagrid")
    public String supply(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"rn\":1,\"id\":10,\"Name\":\"紫竹院供应点\",\"AccountCount\":2,\"FarmCount\":5,\"MemberCount\":142,\"AgencyBuyingCount\":1,\"AgencyBuyingProductCount\":277,\"AgencyBuyingSellProductCount\":2953,\"AgencyBuyingSellProductTotal\":87492.32},{\"rn\":2,\"id\":9,\"Name\":\"海运仓供应点\",\"AccountCount\":2,\"FarmCount\":5,\"MemberCount\":78,\"AgencyBuyingCount\":1,\"AgencyBuyingProductCount\":277,\"AgencyBuyingSellProductCount\":2536,\"AgencyBuyingSellProductTotal\":83565.80}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Member/GetDatagrid")
    public String users(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"ID\":254,\"Phone\":\"Z0091Z\",\"Balance\":975.70,\"Subsidy\":0.00,\"Money\":975.70,\"InsertTime\":\"2018-06-04 09:27:54\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":1,\"OrderPrice\":24.30,\"OrderRechargeTotal\":24.30,\"OrderSubsidyTotal\":0.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-06-04 09:27:54\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":253,\"Phone\":\"Z0090Z\",\"Balance\":913.94,\"Subsidy\":0.00,\"Money\":913.94,\"InsertTime\":\"2018-05-20 19:32:50\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":1,\"OrderPrice\":86.06,\"OrderRechargeTotal\":86.06,\"OrderSubsidyTotal\":0.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-20 19:32:50\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":252,\"Phone\":\"Z0018J\",\"Balance\":137.03,\"Subsidy\":13685.15,\"Money\":13822.18,\"InsertTime\":\"2018-01-29 11:03:36\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":4,\"OrderPrice\":14777.82,\"OrderRechargeTotal\":2462.97,\"OrderSubsidyTotal\":12314.85,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":249,\"Phone\":\"Z0023Y\",\"Balance\":2946.53,\"Subsidy\":1079.02,\"Money\":4025.55,\"InsertTime\":\"2018-01-03 16:31:11\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":332,\"OrderPrice\":16574.45,\"OrderRechargeTotal\":3153.50,\"OrderSubsidyTotal\":13420.95,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":247,\"Phone\":\"H0048Z\",\"Balance\":0.00,\"Subsidy\":0.00,\"Money\":0.00,\"InsertTime\":\"2017-12-31 14:47:17\",\"SupplyName\":\"海运仓供应点\",\"OrderCount\":3,\"OrderPrice\":14200.00,\"OrderRechargeTotal\":14200.00,\"OrderSubsidyTotal\":0.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":246,\"Phone\":\"CESHI001\",\"Balance\":0.00,\"Subsidy\":0.00,\"Money\":0.00,\"InsertTime\":\"2017-12-31 12:28:07\",\"SupplyName\":\"海运仓供应点\",\"OrderCount\":0,\"OrderPrice\":0.00,\"OrderRechargeTotal\":0.00,\"OrderSubsidyTotal\":0.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":244,\"Phone\":\"Z0049Y\",\"Balance\":2078.03,\"Subsidy\":2060.72,\"Money\":4138.75,\"InsertTime\":\"2017-12-30 21:29:07\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":77,\"OrderPrice\":21961.25,\"OrderRechargeTotal\":9521.97,\"OrderSubsidyTotal\":12439.28,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":243,\"Phone\":\"Z0028Y\",\"Balance\":4468.97,\"Subsidy\":2762.75,\"Money\":7231.72,\"InsertTime\":\"2017-12-30 21:28:56\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":481,\"OrderPrice\":22728.28,\"OrderRechargeTotal\":10991.03,\"OrderSubsidyTotal\":11737.25,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":240,\"Phone\":\"Z0048Y\",\"Balance\":836.37,\"Subsidy\":4181.65,\"Money\":5018.02,\"InsertTime\":\"2017-12-30 18:35:02\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":103,\"OrderPrice\":12381.98,\"OrderRechargeTotal\":2063.62,\"OrderSubsidyTotal\":10318.36,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":239,\"Phone\":\"Z0047Y\",\"Balance\":321.69,\"Subsidy\":5108.88,\"Money\":5430.57,\"InsertTime\":\"2017-12-30 18:34:54\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":112,\"OrderPrice\":11269.43,\"OrderRechargeTotal\":1878.31,\"OrderSubsidyTotal\":9391.12,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":238,\"Phone\":\"Z0046Y\",\"Balance\":0.00,\"Subsidy\":1500.00,\"Money\":1500.00,\"InsertTime\":\"2017-12-30 18:34:44\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":1,\"OrderPrice\":14628.00,\"OrderRechargeTotal\":6628.00,\"OrderSubsidyTotal\":8000.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":237,\"Phone\":\"Z0045Y\",\"Balance\":2068.59,\"Subsidy\":3606.82,\"Money\":5675.41,\"InsertTime\":\"2017-12-30 18:34:35\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":103,\"OrderPrice\":20624.59,\"OrderRechargeTotal\":9731.41,\"OrderSubsidyTotal\":10893.18,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":236,\"Phone\":\"Z0043Y\",\"Balance\":1781.25,\"Subsidy\":9406.56,\"Money\":11187.81,\"InsertTime\":\"2017-12-30 18:34:27\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":261,\"OrderPrice\":6112.19,\"OrderRechargeTotal\":1018.75,\"OrderSubsidyTotal\":5093.44,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":235,\"Phone\":\"Z0042Y\",\"Balance\":2101.37,\"Subsidy\":4752.00,\"Money\":6853.37,\"InsertTime\":\"2017-12-30 18:34:17\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":83,\"OrderPrice\":17526.63,\"OrderRechargeTotal\":7778.63,\"OrderSubsidyTotal\":9748.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":234,\"Phone\":\"Z0041Y\",\"Balance\":412.44,\"Subsidy\":5562.56,\"Money\":5975.00,\"InsertTime\":\"2017-12-30 18:34:09\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":98,\"OrderPrice\":10725.00,\"OrderRechargeTotal\":1787.56,\"OrderSubsidyTotal\":8937.44,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":233,\"Phone\":\"Z0040Y\",\"Balance\":432.23,\"Subsidy\":0.00,\"Money\":432.23,\"InsertTime\":\"2017-12-30 18:34:00\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":141,\"OrderPrice\":24467.77,\"OrderRechargeTotal\":9967.77,\"OrderSubsidyTotal\":14500.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":232,\"Phone\":\"H0028Y\",\"Balance\":1635.95,\"Subsidy\":3262.57,\"Money\":4898.52,\"InsertTime\":\"2017-12-30 18:33:49\",\"SupplyName\":\"海运仓供应点\",\"OrderCount\":47,\"OrderPrice\":24501.48,\"OrderRechargeTotal\":13264.05,\"OrderSubsidyTotal\":11237.43,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":231,\"Phone\":\"Z0039Y\",\"Balance\":300.00,\"Subsidy\":14500.00,\"Money\":14800.00,\"InsertTime\":\"2017-12-30 18:33:42\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":0,\"OrderPrice\":0.00,\"OrderRechargeTotal\":0.00,\"OrderSubsidyTotal\":0.00,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":230,\"Phone\":\"Z0038Y\",\"Balance\":1424.45,\"Subsidy\":7122.22,\"Money\":8546.67,\"InsertTime\":\"2017-12-30 18:33:31\",\"SupplyName\":\"紫竹院供应点\",\"OrderCount\":59,\"OrderPrice\":8853.33,\"OrderRechargeTotal\":1475.55,\"OrderSubsidyTotal\":7377.78,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00},{\"ID\":229,\"Phone\":\"H0027Y\",\"Balance\":28.67,\"Subsidy\":11643.51,\"Money\":11672.18,\"InsertTime\":\"2017-12-30 18:33:23\",\"SupplyName\":\"海运仓供应点\",\"OrderCount\":58,\"OrderPrice\":3427.82,\"OrderRechargeTotal\":571.33,\"OrderSubsidyTotal\":2856.49,\"WithholdTotal\":0.00,\"WithholdRechargePrice\":0.00,\"WithholdSubsidyTotal\":0.00,\"Status\":\"正常\",\"FlagID\":1,\"CancelDateTime\":\"2018-05-09 22:56:28\",\"RefundBalance\":0.00,\"RefundSubsidy\":0.00}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("orderCount",9877);
        object.put("orderTotalMoney",2139010.93);
        object.put("withholdTotalMoney",6565.44);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Member/GetWithholdDatagrid")
    public String costs(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"ID\":6,\"Phone\":\"Z0005J\",\"OldSubsidyTotal\":9272.82,\"OldBalanceTotal\":11924.51,\"SubsidyTotal\":1007.72,\"BalanceTotal\":201.54,\"NewSubsidyTotal\":8265.10,\"NewBalanceTotal\":11722.97,\"InsertTime\":\"2018-05-24 10:01\",\"Summary\":\"付明细单\",\"HandleingByPerson\":\"韩敬\",\"InputMan\":\"admin\"},{\"ID\":7,\"Phone\":\"Z0006J\",\"OldSubsidyTotal\":9466.81,\"OldBalanceTotal\":1693.37,\"SubsidyTotal\":1696.73,\"BalanceTotal\":339.35,\"NewSubsidyTotal\":7770.08,\"NewBalanceTotal\":1354.02,\"InsertTime\":\"2018-05-24 10:01\",\"Summary\":\"付明细单\",\"HandleingByPerson\":\"韩敬\",\"InputMan\":\"admin\"},{\"ID\":8,\"Phone\":\"Z0016J\",\"OldSubsidyTotal\":6214.73,\"OldBalanceTotal\":1242.92,\"SubsidyTotal\":1341.92,\"BalanceTotal\":268.38,\"NewSubsidyTotal\":4872.81,\"NewBalanceTotal\":974.54,\"InsertTime\":\"2018-05-24 10:03\",\"Summary\":\"付明细单\",\"HandleingByPerson\":\"韩敬\",\"InputMan\":\"admin\"},{\"ID\":4,\"Phone\":\"H0017Y\",\"OldSubsidyTotal\":0.00,\"OldBalanceTotal\":0.00,\"SubsidyTotal\":271.67,\"BalanceTotal\":54.33,\"NewSubsidyTotal\":-271.67,\"NewBalanceTotal\":-54.33,\"InsertTime\":\"2018-03-14 08:15\",\"Summary\":\"代购鲜甲馅1kg36元，鲜里脊1kg46元，鲜排骨2kg144元，牛腩1kg100元。付款方式：公务卡。\",\"HandleingByPerson\":\"admin\",\"InputMan\":\"admin\"},{\"ID\":5,\"Phone\":\"H0018Y\",\"OldSubsidyTotal\":0.00,\"OldBalanceTotal\":0.00,\"SubsidyTotal\":175.00,\"BalanceTotal\":35.00,\"NewSubsidyTotal\":-175.00,\"NewBalanceTotal\":-35.00,\"InsertTime\":\"2018-03-14 08:24\",\"Summary\":\"代购鲜甲馅2.5kg90元，五花肉2.5kg120元。付款方式：公务卡。\",\"HandleingByPerson\":\"admin\",\"InputMan\":\"admin\"},{\"ID\":9,\"Phone\":\"H0022Y\",\"OldSubsidyTotal\":4513.09,\"OldBalanceTotal\":802.58,\"SubsidyTotal\":978.17,\"BalanceTotal\":195.63,\"NewSubsidyTotal\":3534.92,\"NewBalanceTotal\":606.95,\"InsertTime\":\"2018-05-24 10:04\",\"Summary\":\"付明细单\",\"HandleingByPerson\":\"韩敬\",\"InputMan\":\"admin\"}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }


    private JSONArray getPageArray(JSONArray array,int page,int rows){
        int st = page * rows;
        int ed = st + rows;
        JSONArray result = new JSONArray();
        if(ed > array.size() + 1){
            return null;
        }
        if(ed > array.size() - 1){
            ed = array.size();
        }
        for (int i = st; i < ed; i++) {
            result.fluentAdd(array.getJSONObject(i));
        }
        return result;
    }
}
