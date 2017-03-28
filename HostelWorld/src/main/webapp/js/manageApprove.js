$(function(){

    //根据总页数判断，如果小于5页，则显示所有页数，如果大于5页，则显示5页。根据当前点击的页数生成

    //每页显示的数目
    var show_per_page_num = 3;

    //获取content对象里面，数据的数量
    var numb_of_items = $(".open-hotel-row").length;

    //计算页面显示的总页数
    var pageCounter = Math.ceil(numb_of_items/show_per_page_num);

    //隐藏该对象下面的所有子元素
    $(".open-hotel-row").css('display', 'none');

    //显示第n（show_per_page_num）元素
    $(".open-hotel-row").slice(0, show_per_page_num).css('display', 'block');

    //隐藏域默认值
    $('#start_page').val(0);
    $('#current_page').val(0);
    $('#show_per_page_num').val(show_per_page_num);
    $('#end_page').val(pageCounter);

    //生成分页按钮
    if(pageCounter>5){
        page_icon_num(1,5,0);
    }else{
        page_icon_num(1,pageCounter,0);
    }

    //点击分页按钮触发
    $("body").on("click","#pageGro li",function(){
        var pageNum = parseInt($(this).html())-1;//获取当前页数

        var page = pageNum +1;//跳转页码数
        var show_per_page = parseInt($('#show_per_page_num').val());

        //开始
        start_from_t = pageNum * show_per_page;
        //结束
        end_on_t = start_from_t + show_per_page;
        //隐藏内容ul的所有子元素，获取特定项目并显示它们
        $(".open-hotel-row").css('display', 'none').slice(start_from_t, end_on_t).css('display', 'block');
        if(pageCounter > 5){
            //显示页面
            pageGroup(page,pageCounter);
        }else{
            $(this).addClass("on");
            $(this).siblings("li").removeClass("on");
        }
    });

    //点击上一页触发
    $("#pageGro .pageUp").click(function(){
        var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
        if (pageNum <= 1) {
            var page = pageNum;
        }else{
            var page = pageNum-1;
        }
        var show_per_page = parseInt($('#show_per_page_num').val());

        //开始
        start_from_t = page * show_per_page - show_per_page;
        //结束
        end_on_t = start_from_t + show_per_page;

        //隐藏内容ul的所有子元素，获取特定项目并显示它们
        $(".open-hotel-row").css('display', 'none').slice(start_from_t, end_on_t).css('display', 'block');

        if(pageCounter > 5){
            pageUpC(pageNum,pageCounter);
        }else{
            var index = $("#pageGro ul li.on").index();//获取当前页
            if(index > 0){
                $("#pageGro li").removeClass("on");//清除所有选中
                $("#pageGro ul li").eq(index-1).addClass("on");//选中上一页
            }
        }
    });

    //点击下一页触发
    $("#pageGro .pageDown").click(function(){
        var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
        var page = pageNum;
        if (pageNum===pageCounter) {
            page = pageNum-1;
        }
        var show_per_page = parseInt($('#show_per_page_num').val());
        //开始
        start_from_t = page * show_per_page;

        //结束
        end_on_t = start_from_t + show_per_page;

        //隐藏内容ul的所有子元素，获取特定项目并显示它们
        $(".open-hotel-row").css('display', 'none').slice(start_from_t, end_on_t).css('display', 'block');
        if(pageCounter > 5){
            pageDownC(pageNum,pageCounter);
        }else{
            var index = $("#pageGro ul li.on").index();//获取当前页
            if(index+1 < pageCounter){
                $("#pageGro li").removeClass("on");//清除所有选中
                $("#pageGro ul li").eq(index+1).addClass("on");//选中上一页
            }
        }
    });

    //点击首页
    $("body").on("click","#pageGro .pagestart",function(){
        var pageNum = $('#start_page').val();
        //开始
        start_from_t = pageNum * show_per_page_num;
        //结束
        end_on_t = start_from_t + show_per_page_num;

        //隐藏内容ul的所有子元素，获取特定项目并显示它们
        $(".open-hotel-row").css('display', 'none').slice(start_from_t, end_on_t).css('display', 'block');
        if (pageCounter > 5) {
            //显示页码
            pageGroup(1,pageCounter);
        }else{
            var index = $("#pageGro ul li.on").index();//获取当前页
            if(index < pageCounter){
                $("#pageGro li").removeClass("on");//清除所有选中
                $("#pageGro ul li:first").addClass("on");
            }
        }
    });

    //点击尾页
    $("body").on("click","#pageGro .pageend",function(){
        var pageNum1 = $('#end_page').val();
        var pagenum = pageNum1-2
        var page = pageNum1-1;

        //开始
        start_from_t = page * show_per_page_num;
        //结束
        end_on_t = start_from_t + show_per_page_num;

        //隐藏内容ul的所有子元素，获取特定项目并显示它们
        $(".open-hotel-row").css('display', 'none').slice(start_from_t, end_on_t).css('display', 'block');

        if (pageCounter > 5) {
            //显示页码
            pageGroup(pagenum,pageNum1);
            $("#pageGro ul li:last-child").addClass("on").siblings().removeClass("on");
        }else{
            var index = $("#pageGro ul li.on").index();//获取当前页
            if(index < pageCounter){
                $("#pageGro li").removeClass("on");//清除所有选中
                $("#pageGro ul li:last-child").addClass("on");
            }
        }

    });
});

//点击跳转页面
function pageGroup(pageNum,pageCount){
    switch(pageNum){
        case 1:
            page_icon_num(1,5,0);
            break;
        case 2:
            page_icon_num(1,5,1);
            break;
        case pageCount-1:
            page_icon_num(pageCount-4,pageCount,3);
            break;
        case pageCount:
            page_icon_num(pageCount-4,pageCount,4);
            break;
        default:
            page_icon_num(pageNum-2,pageNum+2,2);
            break;
    }
}

//根据当前选中页生成页面点击按钮
function page_icon_num(page, count, eq){
    var ul_html = "";
    for(var i=page; i<=count; i++){
        ul_html += "<li>"+i+"</li>";
    }
    $("#pageGro ul").html(ul_html);
    $("#pageGro ul li").eq(eq).addClass("on");
}

//上一页
function pageUpC(pageNum, pageCount){
    switch(pageNum){
        case 1:
            break;
        case 2:
            page_icon_num(1,5,0);
            break;
        case pageCount-1:
            page_icon_num(pageCount-4,pageCount,2);
            break;
        case pageCount:
            page_icon_num(pageCount-4,pageCount,3);
            break;
        default:
            page_icon_num(pageNum-2,pageNum+2,1);
            break;
    }
}

//下一页
function pageDownC(pageNum, pageCount){
    switch(pageNum){
        case 1:
            page_icon_num(1,5,1);
            break;
        case 2:
            page_icon_num(1,5,2);
            break;
        case pageCount-1:
            page_icon_num(pageCount-4,pageCount,4);
            break;
        case pageCount:
            break;
        default:
            page_icon_num(pageNum-2,pageNum+2,3);
            break;
    }
}

function manage_open_app(hId,type) {
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/manage_open_app",
        data: {
            "hId":hId,
            "type":type
        },
        success: function (result) {
            alert(result);
            setTimeout(function () {
                window.location.reload();
            },1000);
        },
        error: function () {
            alert("操作失败！");
        }
    });
}

function manage_modify_app(hId,type) {
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/manage_modify_app",
        data: {
            "hId":hId,
            "type":type
        },
        success: function (result) {
            alert(result);
            setTimeout(function () {
                window.location.reload();
            },1000);
        },
        error: function () {
            alert("操作失败！");
        }
    });
}