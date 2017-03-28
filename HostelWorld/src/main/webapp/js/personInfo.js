$(document).ready(function () {
    $(".choose-row").css("display","none");
});

function refresh() {
    window.location.reload();
}
function addMoney(i) {
    clear();
    $("#type").val(i);
    $(".choose-row").css("display","none");
    $(".add-money-row").css("display","block");
}

function addMoneySave() {
    var account = $("#add-money-account").val();
    var num = $("#add-money-num").val();
    var type = $("#type").val();
    var vId = $("#vId").val();

    var reg = /^[0-9]*$/;
    if(account=="" || !reg.test(account)){
        $("#error-add-money").text("账号输入错误！");
        setTimeout("$('#error-add-money').text('');",1000);
        return false;
    }else if(num=="" || !reg.test(num)){
        $("#error-add-money").text("金额没输入或输入错误！");
        setTimeout("$('#error-add-money').text('');",1000);
        return false;
    }else if(num<0){
        $("#error-add-money").text("金额不能小于0！");
        setTimeout("$('#error-add-money').text('');",1000);
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/add_money",
        data: {
            "account": account,
            "money": num,
            "type":type,
            "vid":vId
        },
        success: function (result) {
            $('#error-add-money').text(result);
            setTimeout("$('#error-add-money').text('');",500);
            setTimeout(refresh,1200);
            return true;
        },
        error: function () {
            $('#error-add-money').text('出故障了请稍候再试');
            setTimeout("$('#error-add-money').text('');",500);
        }
    });


}

function pointChange() {
    clear();
    $(".choose-row").css("display","none");
    $(".point-change-row").css("display","block");
}

function pointChangeSave() {
    var num = $("#point-change-num").val();
    var point = $("#point-num-text").text();
    var vId = $("#vId").val();

    var reg = /^[0-9]*$/;
    if(num=="" || !reg.test(num)){
        $("#error-point-change").text("数额输入错误！");
        setTimeout("$('#error-point-change').text('');",1000);
        return false;
    }else if(num<=0){
        $("#error-point-change").text("数额不能小于等于0！");
        setTimeout("$('#error-point-change').text('');",1000);
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/point_change",
        data: {
            "num": num,
            "vid":vId
        },
        success: function (result) {
            $('#error-point-change').text(result);
            setTimeout("$('#error-point-change').text('');",500);
            setTimeout(refresh,1200);
            return true;
        },
        error: function () {
            $('#error-point-change').text('出故障了请稍候再试');
            setTimeout("$('#error-point-change').text('');",1000);
        }
    });

}

function modifyPsw() {
    clear();
    $(".choose-row").css("display","none");
    $(".modify-psw-row").css("display","block");
}

function modifyPswSave() {
    var password = $("#modify-psw-password").val();
    var passwordN = $("#modify-psw-password-new").val();
    var passwordNA = $("#modify-psw-password-new-again").val();
    var vId = $("#vId").val();
    if(password==""){
        $("#error-modify-psw").text("原密码不能为空！");
        setTimeout("$('#error-modify-psw').text('');",1000);
        return false;
    }else if(passwordN=="" || passwordNA==""){
        $("#error-modify-psw").text("新密码不能为空！");
        setTimeout("$('#error-modify-psw').text('');",1000);
        return false;
    }else if(passwordNA!=passwordN){
        $("#error-modify-psw").text("前后密码不一致！");
        setTimeout("$('#error-modify-psw').text('');",1000);
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/modify_psw",
        data: {
            "password": password,
            "passwordN":passwordN,
            "vid":vId
        },
        success: function (result) {
            $('#error-modify-psw').text(result);
            setTimeout("$('#error-modify-psw').text('');",500);
            setTimeout(refresh,1200);
            return true;
        },
        error: function () {
            $('#error-modify-psw').text('出故障了请稍候再试');
            setTimeout("$('#error-modify-psw').text('');",1000);
        }
    });
}

function modifyInfo() {
    $(".dynamic-info-input").attr("disabled",false);
    $(".btn-second").css("display","block");
    $(".btn-first").css("display","none");
}

function modifyInfoSave() {
    var name = $("#dynamic-info-name").val();
    var age = $("#dynamic-info-age").val();
    var address = $("#dynamic-info-address").val();
    var vId = $("#vId").val();

    var reg = /^[0-9]*$/;
    if(name==""){
        $("#error-dynamic-info").text("姓名不能为空！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }else if(!reg.test(age) || age<0 || age>100){
        $("#error-dynamic-info").text("年纪输入错误！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/modify_info",
        data: {
            "name": name,
            "age":age,
            "address":address,
            "vid":vId
        },
        success: function (result) {
            $('#error-dynamic-info').text(result);
            setTimeout("$('#error-dynamic-info').text('');",500);
            setTimeout(refresh,1200);
            return true;
        },
        error: function () {
            $('#error-dynamic-info').text('出故障了请稍候再试');
            setTimeout("$('#error-dynamic-info').text('');",1000);
        }
    });

}

function stopVIP() {
    var vId = $("#vId").val();
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/stop_vip",
        data: {
            "vid":vId
        },
        success: function (result) {
            if(result==-1){
                alert("该账户已停止！");
                window.location.href="/HotelWorld/welcome";
            }else{
                alert(result);
            }
        },
        error: function () {
            alert("停止失败！");
        }
    });
}

function modifyCancel() {
    refresh();
}

function clear(){
    $('.choose-row .reg-input').val("");
}