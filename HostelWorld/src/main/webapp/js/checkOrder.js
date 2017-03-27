$(document).ready(function () {
    $(".input-row").hide();
    $('.choose-input').eq(0).show();
    $('.order-row-list').eq(0).show();

    $('#check-room-code:input').bind('input propertychange', function () {
        checkListener();
    });
    var sign = 0;
    $('.reg-check-input-nv:input').bind('input propertychange', function () {
        getPrice();
    });

    $('#check-room-type-nv').bind('change', function () {
        getPrice();
    });
});

function switchButton(i){
    $(".switch-wrapper-one a").removeClass("active");
    $(".switch-wrapper-one").find("a").eq(i).addClass("active");

    $(".choose-input").hide();
    $('.choose-input').eq(i).show();
}

function tab(i){
    $(".switch-wrapper a").removeClass("active");
    $(".switch-wrapper").find("a").eq(i).addClass("active");

    $(".order-row-list").hide();
    $('.order-row-list').eq(i).show();
}

function leaveHotel(oId) {
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/check_out",
        data: {
            "oId":oId
        },
        success: function (result) {
            alert(result);
            window.location.reload();
        }
    });
}

function comeHotel(i) {
    var code = i.toString();
    $('#check-room-code').val(code);
    getInfo(code);
}

function check() {
    var code = $('#check-room-code').val();
    var names = $('#check-costumer-names').val();
    var roomIds =$('#check-room-ids').val();
    if(sign==0){
        alert("订单号不存在！");
        return false;
    }if(names=="" || roomIds==""){
        alert("看看有没有什么漏填的！");
        return false;
    }
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/check_in",
        data: {
            "code":code,
            "names":names,
            "roomIds":roomIds
        },
        success: function (result) {
            alert(result);
            window.location.reload();
        }
    });
}

function checkNv() {
    var roomIds = $('#check-room-ids-nv').val();
    var names = $('#check-costumer-names-nv').val();
    var rId = $("#check-room-type-nv option:selected").val();
    var num = $("#check-room-num-nv").val();
    var start = $("#check-room-start-nv").val();
    var end =$("#check-room-end-nv").val();

    var reg = /^[0-9]*$/;
    if(names=="" || roomIds=="" || rId=="" || num=="" || start== "" || end==""){
        alert("孩子，干哈呢，都得填！");
        return false;
    }else if(!reg.test(rId) || !reg.test(num)){
        alert("孩子，你家数量是字母啊！");
        return false;
    }else if(rId==0){
        alert("客栈居然没房间，先去发布计划吧！");
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/order_hotel_nv",
        data: {
            "rId":rId,
            "num":num,
            "start":start,
            "end":end,
            "names":names,
            "roomIds":roomIds
        },
        success: function (result) {
            alert(result);
            window.location.reload();
        },
        error: function () {
            alert("未知错误");
        }
    });
}

function checkListener() {
    var code = $('#check-room-code').val();
    getInfo(code);
}

function getInfo(code) {
    var reg = /^[0-9]*$/;
    if(code!=""  && code.length==7 &&reg.test(code)){
        $.ajax({
            type: "post",
            async: true,
            url: "/HotelWorld/get_order",
            data: {
                "code":code
            },
            success: function (result) {
                var array = result.split(";");
                if(array[0]==1){
                    fillForm(array);
                    sign = 1;
                }else{
                    var a = new Array("0","无数据","无数据","无数据","无数据","无数据","无数据");
                    fillForm(a);
                    alert("订单码不存在！");
                    sign=0;
                }
            }
        });
    }else{
        sign=0;
        var a = new Array("0","无数据","无数据","无数据","无数据","无数据","无数据");
        fillForm(a);
    }
}
function fillForm(array) {
    $('#check-room-type').text(array[1]);
    $('#check-room-start').text(array[2]);
    $('#check-room-num').text(array[3]);
    $('#check-room-end').text(array[4]);
    $('#check-room-price').text(array[5]);
    $('#check-room-time').text(array[6]);
}

function getPrice() {
    var rId = $("#check-room-type-nv option:selected").val();
    var num = $("#check-room-num-nv").val();
    var start = $("#check-room-start-nv").val();
    var end =$("#check-room-end-nv").val();

    var reg = /^[0-9]*$/;
    if(rId!="" && num!="" && start!= "" && end!="" && reg.test(rId) && reg.test(num)){
        $.ajax({
            type: "post",
            async: true,
            url: "/HotelWorld/get_price_nv",
            data: {
                "num":num,
                "start":start,
                "end":end,
                "rId":rId
            },
            success: function (result) {
                $("#check-room-price-nv").text("￥"+result);
            }
        });
    }
}

//
// function searchCode() {
//     var code = $('#check-code').val();
//     var reg = /[0-9]*/;
//
//     if(code=="" || !reg.test(code)){
//         alert("订单码输入错误");
//     }
//     alert(code);
// }