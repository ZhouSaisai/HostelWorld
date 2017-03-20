$(document).ready(function () {
    $(".choose-row").css("display","none");
});

function refresh() {
    window.location.reload();
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
    var hId = $("#hId").val();
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
        url: "/HotelWorld/modify_psw_hotel",
        data: {
            "password": password,
            "passwordN":passwordN,
            "hid":hId
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
    var tel = $("#dynamic-info-tel").val();
    var address = $("#dynamic-info-address").val();
    var hId = $("#hId").val();

    var reg = /^[0-9]*$/;
    if(name==""){
        $("#error-dynamic-info").text("姓名不能为空！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }else if(tel=="" || !reg.test(tel)){
        $("#error-dynamic-info").text("电话为空或电话输入错误！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }else if(address==""){
        $("#error-dynamic-info").text("客栈的地址不能为空！");
        setTimeout("$('#error-dynamic-info').text('');",1000);
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/modify_info_hotel",
        data: {
            "name": name,
            "tel":tel,
            "address":address,
            "hid":hId
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

function modifyCancel() {
    refresh();
}

function clear(){
    $('.choose-row .reg-input').val("");
}