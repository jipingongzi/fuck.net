var page = {};
page.number = 0;
page.rows = 10;
function buildTd(){
    var content = "";
    for(var j = 0; j < arguments.length; j++) {
        var field = arguments[j];
        content += "<td>" + field + "</td>";
    }
    return content;
}