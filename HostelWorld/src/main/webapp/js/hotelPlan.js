$(document).ready(function () {
    $(".choose-row").css("display","none");
    var typeNum = $('.room-type-row .li-header').length;
    var planNum = $('.room-plan-row .li-header').length;
    var charList = 'ABCDEFGHIGKLMOPQRSTUVWXYZ';
    for(var i=0;i<typeNum;i++){
        $('.room-type-row .li-header').eq(i).text(charList.charAt(i));
    }
    for(var i=0;i<planNum;i++){
        $('.room-plan-row .li-header').eq(i).text(charList.charAt(i));
    }

});

function addRoom() {
    $(".choose-row").css("display","none");
    $(".add-room-row").css("display","block");
}

function addRoomSave() {
    var name = $("#type-room-name").val();
    var price = $("#type-room-price").val();
    var num = $("#type-room-num").val();
    var hId = $("#hId").val();

    var reg = /^[0-9]*$/;
    if(name=="" || price=="" || num==""){
        alert("孩子，干哈呢，都得填！");
        return false;
    }else if(!reg.test(price) || !reg.test(num)){
        alert("孩子，你家价格/数量不是数字啊！");
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/add_room",
        data: {
            "name": name,
            "price":price,
            "num":num,
            "hId":hId
        },
        success: function (result) {
            alert(result);
            refresh();
            return true;
        },
        error: function () {
            alert("未知错误");
        }
    });

}

function deleteRoom(rId) {
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/delete_room",
        data: {
            "rId":rId
        },
        success: function (result) {
            alert(result);
            refresh();
            return true;
        },
        error: function () {
            alert("未知错误");
        }
    });
}
function addPlan() {
    var typeNum = $('.room-type-row ').length;
    if(typeNum==0){
        alert("请先添加房型吧");
        return false;
    }
    $(".choose-row").css("display","none");
    $(".add-plan-row").css("display","block");
}

function addPlanSave() {
    var rId = $("#plan-room-type option:selected").val();
    var price = $("#type-plan-price").val();
    var num = $("#type-plan-num").val();
    var start = $("#type-plan-time-start").val();
    var end =$("#type-plan-time-end").val();

    var reg = /^[0-9]*$/;
    if(price=="" || num=="" || start== "" || end==""){
        alert("孩子，干哈呢，都得填！");
        return false;
    }else if(!reg.test(price) || !reg.test(num)){
        alert("孩子，你家价格/数量不是数字啊！");
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/add_plan",
        data: {
            "price":price,
            "num":num,
            "rId":rId,
            "start":start,
            "end":end
        },
        success: function (result) {
            alert(result);
            refresh();
            return true;
        },
        error: function () {
            alert("未知错误");
        }
    });
}

function deletePlan(pId) {
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/delete_plan",
        data: {
            "pId":pId
        },
        success: function (result) {
            alert(result);
            refresh();
            return true;
        },
        error: function () {
            alert("未知错误");
        }
    });
}
function refresh(){
    window.location.reload();
}