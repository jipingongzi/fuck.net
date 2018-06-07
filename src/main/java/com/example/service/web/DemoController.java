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

    @PostMapping("/Backstage/Order/GetDatagrid")
    public String order(@RequestParam("page")int pageNumber,@RequestParam("rows")int rows){
        int pageSize = rows;
        String list = "[{\"IDX\":1,\"ID\":\"0bb7c889-c57f-4c90-a104-439d4c2c1c49\",\"SupplyID\":10,\"OrderNo\":\"20180606141525038233\",\"TotalPrice\":3,\"InsertTime\":\"2018-06-06 14:15:25\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":0.5,\"Subsidy\":2.5,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":2,\"ID\":\"692775d8-56d7-4b3c-b234-3641cca896a5\",\"SupplyID\":10,\"OrderNo\":\"20180606140757950967\",\"TotalPrice\":19,\"InsertTime\":\"2018-06-06 14:07:57\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":3.17,\"Subsidy\":15.83,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":3,\"ID\":\"7fed0572-87f9-4dd9-bd8c-8a4f31e2eea5\",\"SupplyID\":10,\"OrderNo\":\"20180606135927926208\",\"TotalPrice\":13,\"InsertTime\":\"2018-06-06 13:59:27\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":2.17,\"Subsidy\":10.83,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":4,\"ID\":\"bdfb0a9b-1e28-410e-8163-cc1f98c59336\",\"SupplyID\":10,\"OrderNo\":\"20180606135835995648\",\"TotalPrice\":33,\"InsertTime\":\"2018-06-06 13:58:35\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":20,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":5.5,\"Subsidy\":27.5,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":5,\"ID\":\"d9f98083-815a-443c-9788-5efa3f02c639\",\"SupplyID\":10,\"OrderNo\":\"20180606135713035458\",\"TotalPrice\":48.9,\"InsertTime\":\"2018-06-06 13:57:13\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":13,\"State\":1,\"Phone\":\"Z0047Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":8.15,\"Subsidy\":40.75,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":6,\"ID\":\"e3e0cfe4-45af-47da-81a3-5c2663a1c8c4\",\"SupplyID\":10,\"OrderNo\":\"20180606133355340800\",\"TotalPrice\":8.5,\"InsertTime\":\"2018-06-06 13:33:55\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":3,\"State\":1,\"Phone\":\"Z0016Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":1.42,\"Subsidy\":7.08,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":7,\"ID\":\"d8c924a5-0741-4ede-a904-52ec68d10b72\",\"SupplyID\":10,\"OrderNo\":\"20180606133012459015\",\"TotalPrice\":22,\"InsertTime\":\"2018-06-06 13:30:12\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0006J\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":3.67,\"Subsidy\":18.33,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":8,\"ID\":\"15fa140c-53d4-48cf-add1-e36d43a8a8c6\",\"SupplyID\":9,\"OrderNo\":\"20180606131802390585\",\"TotalPrice\":246,\"InsertTime\":\"2018-06-06 13:18:02\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":2,\"State\":1,\"Phone\":\"H0010Y\",\"SupplyName\":\"海运仓供应点\",\"Balance\":41,\"Subsidy\":205,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":9,\"ID\":\"066fad6f-e3ae-4ee1-aaa4-b49f4def466a\",\"SupplyID\":9,\"OrderNo\":\"20180606131649706677\",\"TotalPrice\":24.2,\"InsertTime\":\"2018-06-06 13:16:49\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":7,\"State\":1,\"Phone\":\"H0010Y\",\"SupplyName\":\"海运仓供应点\",\"Balance\":4.03,\"Subsidy\":20.17,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":10,\"ID\":\"427f65bd-4f6d-4c0d-8858-dfe634eb18e9\",\"SupplyID\":9,\"OrderNo\":\"20180606123824220415\",\"TotalPrice\":44.4,\"InsertTime\":\"2018-06-06 12:38:24\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":28,\"State\":1,\"Phone\":\"H0011Z\",\"SupplyName\":\"海运仓供应点\",\"Balance\":44.4,\"Subsidy\":0,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":11,\"ID\":\"b38c9cd8-1bab-4760-b1cd-0347cff566ca\",\"SupplyID\":9,\"OrderNo\":\"20180606123330021590\",\"TotalPrice\":403.6,\"InsertTime\":\"2018-06-06 12:33:30\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":7,\"State\":1,\"Phone\":\"H0003J\",\"SupplyName\":\"海运仓供应点\",\"Balance\":67.27,\"Subsidy\":336.33,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":12,\"ID\":\"5ec486b9-4ad6-488a-a91e-1adbc8025229\",\"SupplyID\":10,\"OrderNo\":\"20180606121850732503\",\"TotalPrice\":11,\"InsertTime\":\"2018-06-06 12:18:50\",\"PickupDate\":\"2018-06-12\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0008J\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":1.83,\"Subsidy\":9.17,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":13,\"ID\":\"376b474e-3ea4-4cce-9bfd-e54756602739\",\"SupplyID\":10,\"OrderNo\":\"20180606121850692824\",\"TotalPrice\":11.9,\"InsertTime\":\"2018-06-06 12:18:50\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":4,\"State\":1,\"Phone\":\"Z0008J\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":1.98,\"Subsidy\":9.92,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":14,\"ID\":\"0eaf53e2-11e3-4d99-8071-1d2759c2cf29\",\"SupplyID\":9,\"OrderNo\":\"20180606120452971480\",\"TotalPrice\":161.52,\"InsertTime\":\"2018-06-06 12:04:52\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":34,\"State\":1,\"Phone\":\"H0010J\",\"SupplyName\":\"海运仓供应点\",\"Balance\":26.92,\"Subsidy\":134.6,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":15,\"ID\":\"16b7dfa7-6b27-4f96-91c4-912af830fd1a\",\"SupplyID\":9,\"OrderNo\":\"20180606115408047530\",\"TotalPrice\":425,\"InsertTime\":\"2018-06-06 11:54:08\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":5,\"State\":1,\"Phone\":\"H0010Y\",\"SupplyName\":\"海运仓供应点\",\"Balance\":70.83,\"Subsidy\":354.17,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":16,\"ID\":\"38d09943-cb5d-4661-9ace-97ff9e13226c\",\"SupplyID\":9,\"OrderNo\":\"20180606114807453244\",\"TotalPrice\":69.3,\"InsertTime\":\"2018-06-06 11:48:07\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":20,\"State\":1,\"Phone\":\"H0039Z\",\"SupplyName\":\"海运仓供应点\",\"Balance\":69.3,\"Subsidy\":0,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":17,\"ID\":\"f357b7ff-ff03-4184-9420-faebbcd25fad\",\"SupplyID\":10,\"OrderNo\":\"20180606113341472010\",\"TotalPrice\":56.8,\"InsertTime\":\"2018-06-06 11:33:41\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":5,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":9.47,\"Subsidy\":47.33,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":18,\"ID\":\"17fa0aeb-8927-4228-8027-2bf07aff6c21\",\"SupplyID\":10,\"OrderNo\":\"20180606113140808407\",\"TotalPrice\":27,\"InsertTime\":\"2018-06-06 11:31:40\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":1,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":4.5,\"Subsidy\":22.5,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":19,\"ID\":\"ddf2fb98-1077-43dd-863a-5691424018a1\",\"SupplyID\":10,\"OrderNo\":\"20180606113122152249\",\"TotalPrice\":18,\"InsertTime\":\"2018-06-06 11:31:22\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":10,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":3,\"Subsidy\":15,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"},{\"IDX\":20,\"ID\":\"0f2e5976-793d-4e27-a170-13873ae3edf1\",\"SupplyID\":10,\"OrderNo\":\"20180606113027549598\",\"TotalPrice\":81.9,\"InsertTime\":\"2018-06-06 11:30:27\",\"PickupDate\":\"2018-06-08\",\"ReceiveTime\":\"mock\",\"IsDeliver\":false,\"IsFestival\":false,\"GoodsAmount\":20,\"State\":1,\"Phone\":\"Z0033Y\",\"SupplyName\":\"紫竹院供应点\",\"Balance\":13.65,\"Subsidy\":68.25,\"FestivalGoodsCount\":0,\"FestivalGoodsPrice\":0,\"SupplyOfGoods\":\"顺义基地\"}]";
        JSONArray array = JSON.parseArray(list);
        JSONArray result = getPageArray(array,pageNumber,pageSize);
        JSONObject object = new JSONObject();
        object.put("total",100);
        object.put("rows",result);
        return object.toJSONString();
    }
}
