package com.example.service.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.service.query.IBasementQueryService;
import com.example.service.query.IBuyProxyQueryService;
import com.example.service.query.IOrderQueryService;
import com.example.service.query.IUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
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
        int pageSize = rows;
        String list = "[{\"IDX\":1,\"ID\":\"0bb7c889-c57f-4c90-a104-439d4c2c1c49\",\"SupplyID\":10,\"OrderNo\":\"20180606141525038233\",\"TotalPrice\":3,\"InsertTime\":\"2018-06-06 14:15:25\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":0.5,\"Subsidy\":2.5,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":2,\"ID\":\"692775d8-56d7-4b3c-b234-3641cca896a5\",\"SupplyID\":10,\"OrderNo\":\"20180606140757950967\",\"TotalPrice\":19,\"InsertTime\":\"2018-06-06 14:07:57\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":3.17,\"Subsidy\":15.83,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":3,\"ID\":\"7fed0572-87f9-4dd9-bd8c-8a4f31e2eea5\",\"SupplyID\":10,\"OrderNo\":\"20180606135927926208\",\"TotalPrice\":13,\"InsertTime\":\"2018-06-06 13:59:27\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":2.17,\"Subsidy\":10.83,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":4,\"ID\":\"bdfb0a9b-1e28-410e-8163-cc1f98c59336\",\"SupplyID\":10,\"OrderNo\":\"20180606135835995648\",\"TotalPrice\":33,\"InsertTime\":\"2018-06-06 13:58:35\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":20,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":5.5,\"Subsidy\":27.5,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":5,\"ID\":\"d9f98083-815a-443c-9788-5efa3f02c639\",\"SupplyID\":10,\"OrderNo\":\"20180606135713035458\",\"TotalPrice\":48.9,\"InsertTime\":\"2018-06-06 13:57:13\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":13,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":8.15,\"Subsidy\":40.75,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":6,\"ID\":\"e3e0cfe4-45af-47da-81a3-5c2663a1c8c4\",\"SupplyID\":10,\"OrderNo\":\"20180606133355340800\",\"TotalPrice\":8.5,\"InsertTime\":\"2018-06-06 13:33:55\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":3,\"State\":1,\"Phone\":\"Z0016Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":1.42,\"Subsidy\":7.08,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":7,\"ID\":\"d8c924a5-0741-4ede-a904-52ec68d10b72\",\"SupplyID\":10,\"OrderNo\":\"20180606133012459015\",\"TotalPrice\":22,\"InsertTime\":\"2018-06-06 13:30:12\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0006J\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":3.67,\"Subsidy\":18.33,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":8,\"ID\":\"15fa140c-53d4-48cf-add1-e36d43a8a8c6\",\"SupplyID\":9,\"OrderNo\":\"20180606131802390585\",\"TotalPrice\":246,\"InsertTime\":\"2018-06-06 13:18:02\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":2,\"State\":1,\"Phone\":\"H0010Y\",\"SupplyName\":\"海运仓供应点\",\"Balance\":41,\"Subsidy\":205,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":9,\"ID\":\"066fad6f-e3ae-4ee1-aaa4-b49f4def466a\",\"SupplyID\":9,\"OrderNo\":\"20180606131649706677\",\"TotalPrice\":24.2,\"InsertTime\":\"2018-06-06 13:16:49\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":7,\"State\":1,\"Phone\":\"H0010Y\",\"SupplyName\":\"海运仓供应点\",\"Balance\":4.03,\"Subsidy\":20.17,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":10,\"ID\":\"427f65bd-4f6d-4c0d-8858-dfe634eb18e9\",\"SupplyID\":9,\"OrderNo\":\"20180606123824220415\",\"TotalPrice\":44.4,\"InsertTime\":\"2018-06-06 12:38:24\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":28,\"State\":1,\"Phone\":\"H0011Z\",\"SupplyName\":\"海运仓供应点\",\"Balance\":44.4,\"Subsidy\":0,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":11,\"ID\":\"b38c9cd8-1bab-4760-b1cd-0347cff566ca\",\"SupplyID\":9,\"OrderNo\":\"20180606123330021590\",\"TotalPrice\":403.6,\"InsertTime\":\"2018-06-06 12:33:30\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":7,\"State\":1,\"Phone\":\"H0003J\",\"SupplyName\":\"海运仓供应点\",\"Balance\":67.27,\"Subsidy\":336.33,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":12,\"ID\":\"5ec486b9-4ad6-488a-a91e-1adbc8025229\",\"SupplyID\":10,\"OrderNo\":\"20180606121850732503\",\"TotalPrice\":11,\"InsertTime\":\"2018-06-06 12:18:50\",\"PickupDate\":\"2018-06-12\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0008J\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":1.83,\"Subsidy\":9.17,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":13,\"ID\":\"376b474e-3ea4-4cce-9bfd-e54756602739\",\"SupplyID\":10,\"OrderNo\":\"20180606121850692824\",\"TotalPrice\":11.9,\"InsertTime\":\"2018-06-06 12:18:50\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":4,\"State\":1,\"Phone\":\"Z0008J\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":1.98,\"Subsidy\":9.92,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":14,\"ID\":\"0eaf53e2-11e3-4d99-8071-1d2759c2cf29\",\"SupplyID\":9,\"OrderNo\":\"20180606120452971480\",\"TotalPrice\":161.52,\"InsertTime\":\"2018-06-06 12:04:52\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":34,\"State\":1,\"Phone\":\"H0010J\",\"SupplyName\":\"海运仓供应点\",\"Balance\":26.92,\"Subsidy\":134.6,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":15,\"ID\":\"16b7dfa7-6b27-4f96-91c4-912af830fd1a\",\"SupplyID\":9,\"OrderNo\":\"20180606115408047530\",\"TotalPrice\":425,\"InsertTime\":\"2018-06-06 11:54:08\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":5,\"State\":1,\"Phone\":\"H0010Y\",\"SupplyName\":\"海运仓供应点\",\"Balance\":70.83,\"Subsidy\":354.17,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":16,\"ID\":\"38d09943-cb5d-4661-9ace-97ff9e13226c\",\"SupplyID\":9,\"OrderNo\":\"20180606114807453244\",\"TotalPrice\":69.3,\"InsertTime\":\"2018-06-06 11:48:07\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":20,\"State\":1,\"Phone\":\"H0039Z\",\"SupplyName\":\"海运仓供应点\",\"Balance\":69.3,\"Subsidy\":0,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":17,\"ID\":\"f357b7ff-ff03-4184-9420-faebbcd25fad\",\"SupplyID\":10,\"OrderNo\":\"20180606113341472010\",\"TotalPrice\":56.8,\"InsertTime\":\"2018-06-06 11:33:41\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":5,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":9.47,\"Subsidy\":47.33,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":18,\"ID\":\"17fa0aeb-8927-4228-8027-2bf07aff6c21\",\"SupplyID\":10,\"OrderNo\":\"20180606113140808407\",\"TotalPrice\":27,\"InsertTime\":\"2018-06-06 11:31:40\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":4.5,\"Subsidy\":22.5,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":19,\"ID\":\"ddf2fb98-1077-43dd-863a-5691424018a1\",\"SupplyID\":10,\"OrderNo\":\"20180606113122152249\",\"TotalPrice\":18,\"InsertTime\":\"2018-06-06 11:31:22\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":10,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":3,\"Subsidy\":15,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":20,\"ID\":\"0f2e5976-793d-4e27-a170-13873ae3edf1\",\"SupplyID\":10,\"OrderNo\":\"20180606113027549598\",\"TotalPrice\":81.9,\"InsertTime\":\"2018-06-06 11:30:27\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":20,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":13.65,\"Subsidy\":68.25,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetFestivalDatagrid")
    public String FestivalOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list ="[{\"rn\":1,\"SupplyID\":9,\"SupplyName\":\"海运仓供应点\",\"PickupDate\":\"2018-05-01\",\"FestivalGoodsCount\":1,\"FestivalGoodsPrice\":100,\"IsDeliver\":1},{\"rn\":2,\"SupplyID\":10,\"SupplyName\":\"紫竹院供应点\",\"PickupDate\":\"2018-05-01\",\"FestivalGoodsCount\":1,\"FestivalGoodsPrice\":200,\"IsDeliver\":1},{\"rn\":3,\"SupplyID\":9,\"SupplyName\":\"海运仓供应点\",\"PickupDate\":\"2018-04-28\",\"FestivalGoodsCount\":466,\"FestivalGoodsPrice\":8673.9,\"IsDeliver\":1},{\"rn\":4,\"SupplyID\":10,\"SupplyName\":\"紫竹院供应点\",\"PickupDate\":\"2018-04-28\",\"FestivalGoodsCount\":474,\"FestivalGoodsPrice\":12775.2,\"IsDeliver\":1},{\"rn\":5,\"SupplyID\":9,\"SupplyName\":\"海运仓供应点\",\"PickupDate\":\"2018-04-03\",\"FestivalGoodsCount\":16,\"FestivalGoodsPrice\":37500,\"IsDeliver\":1},{\"rn\":6,\"SupplyID\":10,\"SupplyName\":\"紫竹院供应点\",\"PickupDate\":\"2018-04-03\",\"FestivalGoodsCount\":6,\"FestivalGoodsPrice\":6318,\"IsDeliver\":1},{\"rn\":7,\"SupplyID\":9,\"SupplyName\":\"海运仓供应点\",\"PickupDate\":\"2018-03-02\",\"FestivalGoodsCount\":134,\"FestivalGoodsPrice\":1850,\"IsDeliver\":1},{\"rn\":8,\"SupplyID\":10,\"SupplyName\":\"紫竹院供应点\",\"PickupDate\":\"2018-03-02\",\"FestivalGoodsCount\":169,\"FestivalGoodsPrice\":2206,\"IsDeliver\":1},{\"rn\":9,\"SupplyID\":9,\"SupplyName\":\"海运仓供应点\",\"PickupDate\":\"2018-02-22\",\"FestivalGoodsCount\":2,\"FestivalGoodsPrice\":1960,\"IsDeliver\":1},{\"rn\":10,\"SupplyID\":10,\"SupplyName\":\"紫竹院供应点\",\"PickupDate\":\"2018-02-22\",\"FestivalGoodsCount\":12,\"FestivalGoodsPrice\":14160,\"IsDeliver\":1},{\"rn\":11,\"SupplyID\":9,\"SupplyName\":\"海运仓供应点\",\"PickupDate\":\"2018-02-12\",\"FestivalGoodsCount\":424,\"FestivalGoodsPrice\":353271,\"IsDeliver\":1},{\"rn\":12,\"SupplyID\":10,\"SupplyName\":\"紫竹院供应点\",\"PickupDate\":\"2018-02-12\",\"FestivalGoodsCount\":1054,\"FestivalGoodsPrice\":917031,\"IsDeliver\":1}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetRechargeDatagrid")
    public String RechargeOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"ID\":558,\"MemberID\":254,\"OrderNo\":\"20180606104835848\",\"Phone\":\"Z0091Z\",\"Money\":1000,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528253315847)/\"},{\"ID\":557,\"MemberID\":182,\"OrderNo\":\"20180605155933690\",\"Phone\":\"H0005Y\",\"Money\":1900,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528185573690)/\"},{\"ID\":556,\"MemberID\":191,\"OrderNo\":\"20180605110943550\",\"Phone\":\"H0010Y\",\"Money\":400,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528168183550)/\"},{\"ID\":555,\"MemberID\":106,\"OrderNo\":\"20180605110900529\",\"Phone\":\"H0028Z\",\"Money\":1000,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528168140530)/\"},{\"ID\":554,\"MemberID\":217,\"OrderNo\":\"20180605100545604\",\"Phone\":\"H0025Y\",\"Money\":500,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528164345603)/\"},{\"ID\":553,\"MemberID\":183,\"OrderNo\":\"20180605084526642\",\"Phone\":\"H0006Y\",\"Money\":300,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528159526643)/\"},{\"ID\":552,\"MemberID\":50,\"OrderNo\":\"20180605084235590\",\"Phone\":\"H0010J\",\"Money\":800,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528159355590)/\"},{\"ID\":551,\"MemberID\":204,\"OrderNo\":\"20180605083150383\",\"Phone\":\"H0018Y\",\"Money\":300,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528158710383)/\"},{\"ID\":550,\"MemberID\":240,\"OrderNo\":\"20180605080820484\",\"Phone\":\"Z0048Y\",\"Money\":300,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528157300483)/\"},{\"ID\":549,\"MemberID\":231,\"OrderNo\":\"20180605080753580\",\"Phone\":\"Z0039Y\",\"Money\":300,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528157273580)/\"},{\"ID\":548,\"MemberID\":34,\"OrderNo\":\"20180604111509191\",\"Phone\":\"H0005J\",\"Money\":600,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528082109190)/\"},{\"ID\":547,\"MemberID\":40,\"OrderNo\":\"20180604105553391\",\"Phone\":\"Z0009J\",\"Money\":600,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1528080953390)/\"},{\"ID\":546,\"MemberID\":199,\"OrderNo\":\"20180603090412052\",\"Phone\":\"Z0013Y\",\"Money\":300,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527987852053)/\"},{\"ID\":545,\"MemberID\":33,\"OrderNo\":\"20180603090356533\",\"Phone\":\"Z0005J\",\"Money\":850,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527987836533)/\"},{\"ID\":544,\"MemberID\":209,\"OrderNo\":\"20180603090341266\",\"Phone\":\"H0022Y\",\"Money\":300,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527987821267)/\"},{\"ID\":543,\"MemberID\":35,\"OrderNo\":\"20180603090326074\",\"Phone\":\"Z0006J\",\"Money\":600,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527987806077)/\"},{\"ID\":542,\"MemberID\":67,\"OrderNo\":\"20180603090311661\",\"Phone\":\"Z0013Z\",\"Money\":2000,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527987791663)/\"},{\"ID\":541,\"MemberID\":90,\"OrderNo\":\"20180602140851951\",\"Phone\":\"Z0030Z\",\"Money\":500,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527919731953)/\"},{\"ID\":540,\"MemberID\":217,\"OrderNo\":\"20180602122158218\",\"Phone\":\"H0025Y\",\"Money\":500,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527913318217)/\"},{\"ID\":539,\"MemberID\":104,\"OrderNo\":\"20180531083856264\",\"Phone\":\"Z0035Z\",\"Money\":3000,\"SupplyID\":0,\"SupplyName\":\"mixed\",\"InsertTime\":\"/Date(1527727136263)/\"}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetRefundDatagrid")
    public String RefundOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"rn\":1,\"ID\":43787,\"OrderNo\":\"20180529183504455095\",\"InsertTime\":\"2018-05-29 18:35:04\",\"PickupDate\":\"2018-06-01\",\"GoodsName\":\"樱桃\",\"RefundState\":2,\"RefundPrice\":340,\"RefundPrice1\":56.663670015864625,\"RefundPrice2\":283.3363299841354,\"RefundTime\":\"2018-06-04 07:49:39\",\"Phone\":\"H0023Y\",\"Total\":340,\"SupplyName\":\"海运仓供应点\"},{\"rn\":2,\"ID\":38462,\"OrderNo\":\"20180515233450418743\",\"InsertTime\":\"2018-05-15 23:34:50\",\"PickupDate\":\"2018-05-18\",\"GoodsName\":\"京西宾馆馒头\",\"RefundState\":2,\"RefundPrice\":10,\"RefundPrice1\":1.6666666666666667,\"RefundPrice2\":8.333333333333334,\"RefundTime\":\"2018-05-17 19:01:09\",\"Phone\":\"Z0008Y\",\"Total\":10,\"SupplyName\":\"紫竹院供应点\"},{\"rn\":3,\"ID\":38463,\"OrderNo\":\"20180515233450418743\",\"InsertTime\":\"2018-05-15 23:34:50\",\"PickupDate\":\"2018-05-18\",\"GoodsName\":\"西直门宾馆虎皮蛋糕\",\"RefundState\":2,\"RefundPrice\":6,\"RefundPrice1\":1,\"RefundPrice2\":5,\"RefundTime\":\"2018-05-17 19:01:20\",\"Phone\":\"Z0008Y\",\"Total\":6,\"SupplyName\":\"紫竹院供应点\"},{\"rn\":4,\"ID\":38464,\"OrderNo\":\"20180515233450418743\",\"InsertTime\":\"2018-05-15 23:34:50\",\"PickupDate\":\"2018-05-18\",\"GoodsName\":\"西直门宾馆卤水腐竹\",\"RefundState\":2,\"RefundPrice\":10,\"RefundPrice1\":1.6666666666666667,\"RefundPrice2\":8.333333333333334,\"RefundTime\":\"2018-05-17 19:01:25\",\"Phone\":\"Z0008Y\",\"Total\":10,\"SupplyName\":\"紫竹院供应点\"},{\"rn\":5,\"ID\":38465,\"OrderNo\":\"20180515233450418743\",\"InsertTime\":\"2018-05-15 23:34:50\",\"PickupDate\":\"2018-05-18\",\"GoodsName\":\"远望楼肉皮冻\",\"RefundState\":2,\"RefundPrice\":10,\"RefundPrice1\":1.6666666666666667,\"RefundPrice2\":8.333333333333334,\"RefundTime\":\"2018-05-17 19:01:32\",\"Phone\":\"Z0008Y\",\"Total\":10,\"SupplyName\":\"紫竹院供应点\"},{\"rn\":6,\"ID\":38466,\"OrderNo\":\"20180515233450418743\",\"InsertTime\":\"2018-05-15 23:34:50\",\"PickupDate\":\"2018-05-18\",\"GoodsName\":\"太平路香酥鸭\",\"RefundState\":2,\"RefundPrice\":16,\"RefundPrice1\":2.6666666666666665,\"RefundPrice2\":13.333333333333334,\"RefundTime\":\"2018-05-17 19:01:38\",\"Phone\":\"Z0008Y\",\"Total\":16,\"SupplyName\":\"紫竹院供应点\"},{\"rn\":7,\"ID\":38467,\"OrderNo\":\"20180515233450418743\",\"InsertTime\":\"2018-05-15 23:34:50\",\"PickupDate\":\"2018-05-18\",\"GoodsName\":\"翔云楼宾馆酥鱼\",\"RefundState\":2,\"RefundPrice\":11,\"RefundPrice1\":1.8333333333333333,\"RefundPrice2\":9.166666666666666,\"RefundTime\":\"2018-05-17 19:01:49\",\"Phone\":\"Z0008Y\",\"Total\":11,\"SupplyName\":\"紫竹院供应点\"},{\"rn\":8,\"ID\":36897,\"OrderNo\":\"20180512104356908182\",\"InsertTime\":\"2018-05-12 10:43:56\",\"PickupDate\":\"2018-05-15\",\"GoodsName\":\"蒿子杆\",\"RefundState\":2,\"RefundPrice\":5,\"RefundPrice1\":0.8330404217926186,\"RefundPrice2\":4.166959578207381,\"RefundTime\":\"2018-05-15 11:08:49\",\"Phone\":\"H0023Y\",\"Total\":5,\"SupplyName\":\"海运仓供应点\"},{\"rn\":9,\"ID\":36881,\"OrderNo\":\"20180512103650910855\",\"InsertTime\":\"2018-05-12 10:36:50\",\"PickupDate\":\"2018-05-15\",\"GoodsName\":\"蒿子杆\",\"RefundState\":2,\"RefundPrice\":5,\"RefundPrice1\":0.8333333333333334,\"RefundPrice2\":4.166666666666667,\"RefundTime\":\"2018-05-15 10:34:27\",\"Phone\":\"H0012Y\",\"Total\":5,\"SupplyName\":\"海运仓供应点\"},{\"rn\":10,\"ID\":36796,\"OrderNo\":\"20180512091859386478\",\"InsertTime\":\"2018-05-12 09:18:59\",\"PickupDate\":\"2018-05-15\",\"GoodsName\":\"蒿子杆\",\"RefundState\":2,\"RefundPrice\":5,\"RefundPrice1\":0.8333754038772213,\"RefundPrice2\":4.166624596122778,\"RefundTime\":\"2018-05-15 10:34:22\",\"Phone\":\"H0026Y\",\"Total\":5,\"SupplyName\":\"海运仓供应点\"},{\"rn\":11,\"ID\":36756,\"OrderNo\":\"20180512082147140587\",\"InsertTime\":\"2018-05-12 08:21:47\",\"PickupDate\":\"2018-05-15\",\"GoodsName\":\"蒿子杆\",\"RefundState\":2,\"RefundPrice\":5,\"RefundPrice1\":0.8333333333333334,\"RefundPrice2\":4.166666666666667,\"RefundTime\":\"2018-05-15 10:34:18\",\"Phone\":\"H0021Y\",\"Total\":5,\"SupplyName\":\"海运仓供应点\"},{\"rn\":12,\"ID\":36598,\"OrderNo\":\"20180511190114619127\",\"InsertTime\":\"2018-05-11 19:01:14\",\"PickupDate\":\"2018-05-15\",\"GoodsName\":\"蒿子杆\",\"RefundState\":2,\"RefundPrice\":5,\"RefundPrice1\":0.8328840970350404,\"RefundPrice2\":4.16711590296496,\"RefundTime\":\"2018-05-15 10:34:04\",\"Phone\":\"H0007J\",\"Total\":5,\"SupplyName\":\"海运仓供应点\"},{\"rn\":13,\"ID\":30894,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"芦笋\",\"RefundState\":2,\"RefundPrice\":4,\"RefundPrice1\":4,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:53:26\",\"Phone\":\"H0001Z\",\"Total\":4,\"SupplyName\":\"海运仓供应点\"},{\"rn\":14,\"ID\":30895,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"小香葱\",\"RefundState\":2,\"RefundPrice\":2,\"RefundPrice1\":2,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:53:20\",\"Phone\":\"H0001Z\",\"Total\":2,\"SupplyName\":\"海运仓供应点\"},{\"rn\":15,\"ID\":30896,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"绿豆芽\",\"RefundState\":2,\"RefundPrice\":2,\"RefundPrice1\":2,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:53:04\",\"Phone\":\"H0001Z\",\"Total\":2,\"SupplyName\":\"海运仓供应点\"},{\"rn\":16,\"ID\":30897,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"香菇\",\"RefundState\":2,\"RefundPrice\":4.4,\"RefundPrice1\":4.4,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:52:56\",\"Phone\":\"H0001Z\",\"Total\":4.4,\"SupplyName\":\"海运仓供应点\"},{\"rn\":17,\"ID\":30898,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"小黄瓜\",\"RefundState\":2,\"RefundPrice\":4,\"RefundPrice1\":4,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:52:37\",\"Phone\":\"H0001Z\",\"Total\":4,\"SupplyName\":\"海运仓供应点\"},{\"rn\":18,\"ID\":30899,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"长茄子\",\"RefundState\":2,\"RefundPrice\":3.6,\"RefundPrice1\":3.6,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:52:45\",\"Phone\":\"H0001Z\",\"Total\":3.6,\"SupplyName\":\"海运仓供应点\"},{\"rn\":19,\"ID\":30900,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"纯牛奶\",\"RefundState\":2,\"RefundPrice\":1.5,\"RefundPrice1\":1.5,\"RefundPrice2\":0,\"RefundTime\":\"2018-05-15 10:34:34\",\"Phone\":\"H0001Z\",\"Total\":6,\"SupplyName\":\"海运仓供应点\"},{\"rn\":20,\"ID\":30901,\"OrderNo\":\"20180423172155424554\",\"InsertTime\":\"2018-04-23 17:21:55\",\"PickupDate\":\"2018-04-27\",\"GoodsName\":\"原味酸牛奶\",\"RefundState\":2,\"RefundPrice\":3.6,\"RefundPrice1\":3.6,\"RefundPrice2\":0,\"RefundTime\":\"2018-04-27 07:52:30\",\"Phone\":\"H0001Z\",\"Total\":3.6,\"SupplyName\":\"海运仓供应点\"}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetGoodsDatagrid")
    public String SupplyGoodsOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"rn\":1,\"GoodsName\":\"生菜\",\"SerialNum\":\"1-84\",\"Amount\":1304,\"Price\":9521.28},{\"rn\":2,\"GoodsName\":\"2kg金鱼酵素柔软洗衣液\",\"SerialNum\":\"mock\",\"Amount\":2,\"Price\":60.6},{\"rn\":3,\"GoodsName\":\"300—400平鱼\",\"SerialNum\":\"mock\",\"Amount\":48,\"Price\":2400},{\"rn\":4,\"GoodsName\":\"400—600黄鱼\",\"SerialNum\":\"mock\",\"Amount\":40,\"Price\":1200},{\"rn\":5,\"GoodsName\":\"6头草虾\",\"SerialNum\":\"mock\",\"Amount\":32,\"Price\":2560},{\"rn\":6,\"GoodsName\":\"澳洲水蜜桃\",\"SerialNum\":\"mock\",\"Amount\":2,\"Price\":880},{\"rn\":7,\"GoodsName\":\"白不老豆角\",\"SerialNum\":\"1-53\",\"Amount\":854,\"Price\":3676},{\"rn\":8,\"GoodsName\":\"白菜\",\"SerialNum\":\"1-6\",\"Amount\":522,\"Price\":783},{\"rn\":9,\"GoodsName\":\"白菜花\",\"SerialNum\":\"1-3\",\"Amount\":722,\"Price\":2308.6},{\"rn\":10,\"GoodsName\":\"白萝卜\",\"SerialNum\":\"1-16\",\"Amount\":524,\"Price\":2515.8},{\"rn\":11,\"GoodsName\":\"白玉萝卜\",\"SerialNum\":\"1-65\",\"Amount\":264,\"Price\":660},{\"rn\":12,\"GoodsName\":\"白玉樱桃萝卜\",\"SerialNum\":\"1-64\",\"Amount\":66,\"Price\":198},{\"rn\":13,\"GoodsName\":\"百合\",\"SerialNum\":\"ET41\",\"Amount\":10,\"Price\":180},{\"rn\":14,\"GoodsName\":\"宝塔菜花\",\"SerialNum\":\"1-90\",\"Amount\":116,\"Price\":464},{\"rn\":15,\"GoodsName\":\"北冰洋橙汁汽水330ml24听\",\"SerialNum\":\"ZT11\",\"Amount\":28,\"Price\":2268},{\"rn\":16,\"GoodsName\":\"北冰洋橙汁汽水330ml24听\",\"SerialNum\":\"mock\",\"Amount\":32,\"Price\":2592},{\"rn\":17,\"GoodsName\":\"北冰洋桔汁汽水330ml24听\",\"SerialNum\":\"mock\",\"Amount\":22,\"Price\":1782},{\"rn\":18,\"GoodsName\":\"北冰洋桔汁汽水330ml24听\",\"SerialNum\":\"ZT10\",\"Amount\":22,\"Price\":1782},{\"rn\":19,\"GoodsName\":\"北大荒绿野有机秋木耳100g\",\"SerialNum\":\"ZT2\",\"Amount\":20,\"Price\":396},{\"rn\":20,\"GoodsName\":\"北大荒绿野有机秋木耳100g\",\"SerialNum\":\"mock\",\"Amount\":186,\"Price\":3682.8}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }

    @PostMapping("/Backstage/Order/GetGoodsDatagrid1")
    public String SupplyFestivalOrder(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"rn\":1,\"GoodsName\":\"34号-冻品礼盒1号\",\"SerialNum\":\"\",\"Amount\":42,\"Price\":22470},{\"rn\":2,\"GoodsName\":\"34号-冻品礼盒2号\",\"SerialNum\":\"\",\"Amount\":9,\"Price\":10710},{\"rn\":3,\"GoodsName\":\"34号-冻品礼盒3号\",\"SerialNum\":\"\",\"Amount\":11,\"Price\":8470},{\"rn\":4,\"GoodsName\":\"34号-冻品礼盒5号\",\"SerialNum\":\"\",\"Amount\":5,\"Price\":6650},{\"rn\":5,\"GoodsName\":\"陈酿茅台酒53度\",\"SerialNum\":\"\",\"Amount\":503,\"Price\":694140},{\"rn\":6,\"GoodsName\":\"春菠菜\",\"SerialNum\":\"\",\"Amount\":17,\"Price\":27.2},{\"rn\":7,\"GoodsName\":\"春韭菜\",\"SerialNum\":\"\",\"Amount\":14,\"Price\":30.8},{\"rn\":8,\"GoodsName\":\"纯奶\",\"SerialNum\":\"\",\"Amount\":302,\"Price\":211.4},{\"rn\":9,\"GoodsName\":\"大红门君健-A款\",\"SerialNum\":\"\",\"Amount\":2,\"Price\":576},{\"rn\":10,\"GoodsName\":\"大红门君健-B款\",\"SerialNum\":\"\",\"Amount\":12,\"Price\":4056},{\"rn\":11,\"GoodsName\":\"大红门生鲜礼盒\",\"SerialNum\":\"\",\"Amount\":22,\"Price\":6996},{\"rn\":12,\"GoodsName\":\"稻香村桂花山楂汤圆\",\"SerialNum\":\"\",\"Amount\":33,\"Price\":396},{\"rn\":13,\"GoodsName\":\"稻香村黄米钙奶汤圆\",\"SerialNum\":\"\",\"Amount\":7,\"Price\":84},{\"rn\":14,\"GoodsName\":\"稻香村黄米核桃汤圆\",\"SerialNum\":\"\",\"Amount\":11,\"Price\":132},{\"rn\":15,\"GoodsName\":\"稻香村黄米红枣汤圆\",\"SerialNum\":\"\",\"Amount\":16,\"Price\":192},{\"rn\":16,\"GoodsName\":\"稻香村精制黑麻汤圆\",\"SerialNum\":\"\",\"Amount\":108,\"Price\":1296},{\"rn\":17,\"GoodsName\":\"稻香村玫瑰豆沙汤圆\",\"SerialNum\":\"\",\"Amount\":55,\"Price\":660},{\"rn\":18,\"GoodsName\":\"稻香村奶油可可汤圆\",\"SerialNum\":\"\",\"Amount\":17,\"Price\":204},{\"rn\":19,\"GoodsName\":\"稻香村浓香花生汤圆\",\"SerialNum\":\"\",\"Amount\":33,\"Price\":396},{\"rn\":20,\"GoodsName\":\"稻香村糖醇汤圆\",\"SerialNum\":\"\",\"Amount\":17,\"Price\":306}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }
}
