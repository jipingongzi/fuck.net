var builder = {};
//请求接口路径
builder.url = "/Backstage/Farm/GetDatagrid";
//表格id
builder.tableId = "#tab";
//分页按钮id
builder.pageBtnId = "#btn";
builder.buildLine = function (item) {
    var line ="<tr>";
    //封装列的值，需要和table中的顺序一致
    var content = buildTd(item.rownumber,item.Name,item.AccountCount,item.GoodsCount,item.BuyGoodsCount,item.BuyPrice,item.SupplyCount);
    line += content;
    line += "</tr>";
    $("#loading").hide(300)
    $("#btn").attr("disabled",false)
    return line;
}