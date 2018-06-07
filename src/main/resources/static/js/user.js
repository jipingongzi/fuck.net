var builderUser = {};
builderUser.url = "/Backstage/Member/GetDatagrid";
builderUser.tableId = "#tabUser";
builderUser.pageBtnId = "#btnUser";
builderUser.buildLine = function (item) {
    var line ="<tr>";
    var content = buildTd(item.Phone,item.Money,item.Balance,item.Subsidy,
        item.OrderPrice,item.OrderRechargeTotal,item.OrderSubsidyTotal,item.RefundBalance,item.RefundSubsidy,
        item.WithholdTotal,item.WithholdRechargePrice,item.WithholdSubsidyTotal,item.SupplyName,item.InsertTime,item.Status);
    line += content;
    line += "</tr>";
    return line;
}
var builderCost = {};
builderCost.url = "/Backstage/Member/GetWithholdDatagrid";
builderCost.tableId = "#tabCost";
builderCost.pageBtnId = "#btnCost";
builderCost.buildLine = function (item) {
    var line ="<tr>";
    var content = buildTd(item.Phone,item.OldSubsidyTotal,item.OldBalanceTotal,item.SubsidyTotal,
        item.BalanceTotal,item.NewSubsidyTotal,item.NewBalanceTotal,item.InsertTime,item.Summary,
        item.HandleingByPerson);
    line += content;
    line += "</tr>";
    return line;
}
var pageUser = {};
pageUser.number = 0;
pageUser.rows = 2;
var pageCost = {};
pageCost.number = 0;
pageCost.rows = 2;
//默认加载用户列表页
getRow(1);

$(function(){
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var activeTab = $(e.target).text();
        if(activeTab == "用户列表"){
           getRow(1);
        }else {
            getRow(2);
        }
    });
});


function getRow(type){
    var builder;
    var page;
    if(type == 1){
        builder = builderUser;
        page = pageUser;
    }else {
        builder = builderCost;
        page = pageCost;
    }
    $.ajax({
        type: "POST",
        url: builder.url,
        data: {page:page.number,rows:page.rows},
        dataType: "json",
        success: function(data){
            var rows = data.rows;
            if(rows == undefined || rows.length < page.rows){
                $(builder.pageBtnId).text("没有更多了");
                $(builder.pageBtnId).removeClass("btn-info");
                $(builder.pageBtnId).addClass("btn-default");
                if(rows == undefined){
                    rows = [];
                }
            }
            for(var j = 0; j < rows.length; j++) {
                var line = builder.buildLine(rows[j])
                $(builder.tableId).append(line);
            }
            page.number++;
        },
        error:function(){
            $(builder.pageBtnId).text("没有更多了");
            $(builder.pageBtnId).removeClass("btn-info");
            $(builder.pageBtnId).addClass("btn-default");
        }
    });
}
function back() {
    window.location.href = "../pages/nav.html";
}
function buildTd(){
    var content = "";
    for(var j = 0; j < arguments.length; j++) {
        var field = arguments[j];
        content += "<td>" + field + "</td>";
    }
    return content;
}