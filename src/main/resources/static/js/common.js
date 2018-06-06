getRow();
function getRow(){
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
                rows = [];
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