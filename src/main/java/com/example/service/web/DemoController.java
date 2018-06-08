package com.example.service.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.service.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    @Autowired
    private IUserQueryService userQueryService;
    @Autowired
    private IOrderQueryService orderQueryService;
    @Autowired
    private IBasementQueryService basementQueryService;
    @Autowired
    private IBuyProxyQueryService buyProxyQueryService;
    @Autowired
    private ISupplyQueryService supplyQueryService;

    @PostMapping("/Backstage/Farm/GetDatagrid")
    public String basement(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result = basementQueryService.getBasementList(pageNumber,rows).stream().map(m -> {
            if(m.get("GoodsCount") == null){
                m.put("GoodsCount",0);
            }
            if(m.get("BuyGoodsCount") == null){
                m.put("BuyGoodsCount",0);
            }
            if(m.get("BuyPrice") == null){
                m.put("BuyPrice",0);
            }
            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/AgencyBuying/GetDatagrid")
    public String buyproxy(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        List<Map<String,Object>> result = buyProxyQueryService.getBuyProxyList(pageNumber,rows).stream().map(m -> {
            if(m.get("GoodsCount") == null){
                m.put("GoodsCount",0);
            }
            if(m.get("BuyGoodsCount") == null){
                m.put("BuyGoodsCount",0);
            }
            if(m.get("BuyPrice") == null){
                m.put("BuyPrice",0);
            }
            return m;
        }).collect(Collectors.toList());
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Supply/GetDatagrid")
    public String supply(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        JSONObject object = new JSONObject();
        object.put("rows",supplyQueryService.getSupplyList(pageNumber,rows));
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
