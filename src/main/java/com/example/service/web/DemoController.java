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


    private JSONArray getPageArray(JSONArray array,int page,int rows){
        int st = page * rows;
        int ed = st + rows;
        JSONArray result = new JSONArray();
        if(ed > array.size() + 1){
            return null;
        }
        if(ed > array.size() - 1){
            ed = array.size() - 1;
        }
        for (int i = st; i < ed; i++) {
            result.fluentAdd(array.getJSONObject(i));
        }
        return result;
    }
}
