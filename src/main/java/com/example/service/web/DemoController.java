package com.example.service.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.service.common.DateUtil;
import com.example.service.query.IOrderQueryService;
import com.example.service.query.IUserQueryService;
import com.sun.jdi.IntegerValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    @Autowired
    private IUserQueryService userQueryService;
    @Autowired
    private IOrderQueryService orderQueryService;

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
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  userQueryService.getUserList(pageNumber,rows);
        result = result.stream().map(m ->{
            if(m.get("WithholdTotal") == null){
                m.put("WithholdTotal",0);
            }
            if(m.get("WithholdRechargePrice") == null){
                m.put("WithholdRechargePrice",0);
            }
            if(m.get("WithholdSubsidyTotal") == null){
                m.put("WithholdSubsidyTotal",0);
            }
            if(m.get("OrderPrice") == null){
                m.put("OrderPrice","0");
            }
            if(m.get("OrderRechargeTotal") == null){
                m.put("OrderRechargeTotal","0");
            }
            if(m.get("OrderSubsidyTotal") == null){
                m.put("OrderSubsidyTotal","0");
            }
            if(m.get("InsertTime") != null){
                String insertTimeStr = m.get("InsertTime").toString().split(" ")[0];
                m.put("InsertTime",insertTimeStr);
            }
            if(m.get("Status") == null){
                m.put("Status","正常");
            }
            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Member/GetWithholdDatagrid")
    public String costs(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  userQueryService.getCosytList(pageNumber,rows);

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

    @PostMapping("/Backstage/Order/GetDatagrid")
    public String goodsOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  orderQueryService.getGoodsOrderList(pageNumber,rows);
        result = result.stream().map(m ->{
            if(m.get("SupplyOfGoods") == null){
                m.put("SupplyOfGoods","");
            }
            if(m.get("InsertTime") != null){
                String insertTimeStr = m.get("InsertTime").toString().split(" ")[0];
                m.put("InsertTime",insertTimeStr);
            }
            if(!StringUtils.isEmpty(m.get("ReceiveTime"))){
                String insertTimeStr = m.get("ReceiveTime").toString().split(" ")[0];
                m.put("ReceiveTime",insertTimeStr);
            }
            if(!StringUtils.isEmpty(m.get("ReceiveTime"))){
                m.put("IsDeliver","已领取");
            }else {
                m.put("IsDeliver","未领取");
            }
            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetFestivalDatagrid")
    public String FestivalOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  orderQueryService.getFestivalOrderList(pageNumber,rows);
        result = result.stream().map(m ->{
            if(Boolean.valueOf(m.get("IsDeliver").toString())){
                m.put("IsDeliver","已发货");
            }else {
                m.put("IsDeliver","未发货");
            }

            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetRechargeDatagrid")
    public String RechargeOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  orderQueryService.getRechargeOrderList(pageNumber,rows);
        result = result.stream().map(m ->{
            if(m.get("InsertTime") != null){
                String insertTimeStr = m.get("InsertTime").toString().split(" ")[0];
                m.put("InsertTime",insertTimeStr);
            }
            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetRefundDatagrid")
    public String RefundOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  orderQueryService.getRefundOrderList(pageNumber,rows);

        result = result.stream().map(m ->{
            if(m.get("InsertTime") != null){
                String insertTimeStr = m.get("InsertTime").toString().split(" ")[0];
                m.put("InsertTime",insertTimeStr);
            }
            if ((int)m.get("RefundState")  == 1){
                m.put("RefundState","退款中");
            }
            if ((int)m.get("RefundState")  == 2){
                m.put("RefundState","已退款");
            }
            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetGoodsDatagrid")
    public String SupplyGoodsOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  orderQueryService.getSupplyGoodsOrderList(pageNumber,rows);

        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetGoodsDatagrid1")
    public String SupplyFestivalOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result =  orderQueryService.getSupplyFestivalOrderList(pageNumber,rows);

        object.put("rows",result);
        return object.toJSONString();
    }
}
