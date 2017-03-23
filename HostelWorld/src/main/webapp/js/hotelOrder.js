$(document).ready(function () {
    $('.reg-input:input').bind('input propertychange', function() {
        var plan = $("#plan-room-type option:selected").val();
        var num = $("#order-num").val();
        var start = $("#order-time-start").val();
        var end =$("#order-time-end").val();
        var vId = $("#vId").val();
        var rId = $("#rId").val();
        var reg = /^[0-9]*$/;
        if(start!=""&&end!=""&&num!=""&&reg.test(num)){
            $.ajax({
                type: "post",
                async: true,
                url: "/HotelWorld/order_hotel",
                data: {
                    "plan":plan,
                    "num":num,
                    "start":start,
                    "end":end,
                    "vId":vId,
                    "rId":rId
                },
                success: function (result) {

                }
            });
        }
    })
});

function order(){
    var plan = $("#plan-room-type option:selected").val();
    var num = $("#order-num").val();
    var start = $("#order-time-start").val();
    var end =$("#order-time-end").val();
    var vId = $("#vId").val();
    var rId = $("#rId").val();

    var reg = /^[0-9]*$/;
    if(plan=="" || num=="" || start== "" || end==""){
        alert("孩子，干哈呢，都得填！");
        return false;
    }else if(!reg.test(plan) || !reg.test(num)){
        alert("孩子，你家数量是字母啊！");
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/order_hotel",
        data: {
            "plan":plan,
            "num":num,
            "start":start,
            "end":end,
            "vId":vId,
            "rId":rId
        },
        success: function (result) {
            if(result=="success"){
                window.location.href="/HotelWorld/my_order";
                return true;
            }
            alert(result);
            return false;
        },
        error: function () {
            alert("未知错误");
        }
    });
}

