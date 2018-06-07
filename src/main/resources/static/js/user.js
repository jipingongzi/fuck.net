var builder = {};
builder.url = "/Backstage/Member/GetDatagrid";
builder.tableId = "#tabUser";
builder.pageBtnId = "#btnUser";
builder.buildLine = function (item) {
    var line ="<tr>";
    var content = buildTd(item.Phone,item.Money,item.Balance,item.Subsidy,
        item.OrderPrice,item.OrderRechargeTotal,item.OrderSubsidyTotal,item.RefundBalance,item.RefundSubsidy,
        item.WithholdTotal,item.WithholdRechargePrice,item.WithholdSubsidyTotal,item.SupplyName,item.InsertTime,item.Status);
    line += content;
    line += "</tr>";
    return line;
}
$(function(){
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var activeTab = $(e.target).text();
        if(activeTab == "用户列表"){
            builder.url = "/Backstage/Member/GetDatagrid";
            builder.tableId = "#tabUser";
            builder.pageBtnId = "#btnUser";
            builder.buildLine = function (item) {
                var line ="<tr>";
                var content = buildTd(item.OrderPrice,item.OrderRechargeTotal,item.OrderSubsidyTotal,item.RefundBalance,item.RefundSubsidy,
                    item.WithholdTotal,item.WithholdRechargePrice,item.WithholdSubsidyTotal,item.SupplyName,item.InsertTime,item.Status);
                line += content;
                line += "</tr>";
                return line;
            }
        }else {
            builder.url = "/Backstage/Supply/GetDatagrid";
            builder.tableId = "#tab";
            builder.pageBtnId = "#btn";
            builder.buildLine = function (item) {
                var line ="<tr>";
                var content = buildTd(item.rn,item.Name,item.AccountCount,item.FarmCount,item.MemberCount,item.AgencyBuyingCount,item.AgencyBuyingProductCount,item.AgencyBuyingSellProductCount,item.AgencyBuyingSellProductTotal);
                line += content;
                line += "</tr>";
                return line;
            }
        }
    });
});