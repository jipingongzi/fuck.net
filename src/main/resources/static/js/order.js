var builderGoodsOrder = {};
builderGoodsOrder.url = "/Backstage/Order/GetDatagrid";
builderGoodsOrder.tableId = "#tabGoodsOrder";
builderGoodsOrder.pageBtnId = "#btnGoodsOrder";
builderGoodsOrder.buildLine = function (item) {
  var line ="<tr>";
  var content = buildTd(item.OrderNo,item.Phone,item.GoodsAmount,item.TotalPrice,item.Balance,item.Subsidy,item.InsertTime,item.PickupDate,item.SupplyName,item.IsDeliver,item.SupplyOfGoods,item.ReceiveTime);
  line += content;
  line += "</tr>";
  return line;
}

var builderFestivalOrder = {};
builderFestivalOrder.url = "/Backstage/Order/GetFestivalDatagrid";
builderFestivalOrder.tableId = "#tabFestivalOrder";
builderFestivalOrder.pageBtnId = "#btnFestivalOrder";
builderFestivalOrder.buildLine = function (item) {
  var line ="<tr>";
  var content = buildTd(item.SupplyName,item.GoodsAmount,item.TotalPrice,item.PickupDate,item.IsDeliver);
  line += content;
  line += "</tr>";
  return line;
}

var builderRechargeOrder = {};
builderRechargeOrder.url = "/Backstage/Order/GetRechargeDatagrid";
builderRechargeOrder.tableId = "#tabRechargeOrder";
builderRechargeOrder.pageBtnId = "#btnRechargeOrder";
builderRechargeOrder.buildLine = function (item) {
  var line ="<tr>";
  var content = buildTd(item.Phone,item.OrderNo,item.Money,item.InsertTime);
  line += content;
  line += "</tr>";
  return line;
}

var builderRefundOrder = {};
builderRefundOrder.url = "/Backstage/Order/GetRefundDatagrid";
builderRefundOrder.tableId = "#tabRefundOrder";
builderRefundOrder.pageBtnId = "#btnRefundOrder";
builderRefundOrder.buildLine = function (item) {
  var line ="<tr>";
  var content = buildTd(item.OrderNo,item.Phone,item.GoodsName,item.Name,item.RefundPrice,item.RefundSubsidyPrice,item.RefundRechargePrice,item.InsertTime,item.PickupDate,item.RefundTime,item.RefundState);
  line += content;
  line += "</tr>";
  return line;
}

var builderSupplyGoodsOrder = {};
builderSupplyGoodsOrder.url = "/Backstage/Order/GetGoodsDatagrid";
builderSupplyGoodsOrder.tableId = "#tabSupplyGoodsOrder";
builderSupplyGoodsOrder.pageBtnId = "#btnSupplyGoodsOrder";
builderSupplyGoodsOrder.buildLine = function (item) {
  var line ="<tr>";
  var content = buildTd(item.GoodsName,item.SerialNum,item.Amount,item.Price);
  line += content;
  line += "</tr>";
  return line;
}

var builderSupplyFestivalOrder = {};
builderSupplyFestivalOrder.url = "/Backstage/Order/GetGoodsDatagrid1";
builderSupplyFestivalOrder.tableId = "#tabSupplyFestivalOrder";
builderSupplyFestivalOrder.pageBtnId = "#btnSupplyFestivalOrder";
builderSupplyFestivalOrder.buildLine = function (item) {
  var line ="<tr>";
  var content = buildTd(item.GoodsName,item.SerialNum,item.Amount,item.Price);
  line += content;
  line += "</tr>";
  return line;
}


var pageGoodsOrder = {};
pageGoodsOrder.number = 0;
pageGoodsOrder.rows = 10;
var pageFestivalOrder = {};
pageFestivalOrder.number = 0;
pageFestivalOrder.rows = 10;
var pageRechargeOrder = {};
pageRechargeOrder.number = 0;
pageRechargeOrder.rows = 10;
var pageRefundOrder = {};
pageRefundOrder.number = 0;
pageRefundOrder.rows = 10;
var pageSupplyGoodsOrder = {};
pageSupplyGoodsOrder.number = 0;
pageSupplyGoodsOrder.rows = 10;
var pageSupplyFestivalOrder = {};
pageSupplyFestivalOrder.number = 0;
pageSupplyFestivalOrder.rows = 10;
//默认加载商品订单列表页
getRow(1);


$(function(){
  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    var activeTab = $(e.target).text();
    if(activeTab == "商品订单"){
      if(pageGoodsOrder.number == 0){
        getRow(1);
      }
    }else if (activeTab == "节日订单"){
      if(pageFestivalOrder.number == 0){
        getRow(2);
      }
    }else if (activeTab == "充值订单"){
      if(pageRechargeOrder.number == 0){
        getRow(3);
      }
    }else if (activeTab == "退款列表"){
      if(pageRefundOrder.number == 0){
        getRow(4);
      }
    }else if (activeTab == "供应点商品订单"){
      if(pageSupplyGoodsOrder.number == 0){
        getRow(5);
      }
    }else if (activeTab == "供应点节日订单"){
      if(pageSupplyFestivalOrder.number == 0){
        getRow(6);
      }
    }
  });
});





function getRow(type){
  var builder;
  var page;
  if(type == 1){
    builder = builderGoodsOrder;
    page = pageGoodsOrder;
  }else if(type == 2){
    builder = builderFestivalOrder;
    page = pageFestivalOrder;
  }else if(type == 3){
    builder = builderRechargeOrder;
    page = pageRechargeOrder;
  }else if(type == 4){
    builder = builderRefundOrder;
    page = pageRefundOrder;
  }else if(type == 5){
    builder = builderSupplyGoodsOrder;
    page = pageSupplyGoodsOrder;
  }else if(type == 6){
    builder = builderSupplyFestivalOrder;
    page = pageSupplyFestivalOrder;
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