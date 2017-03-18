$(document).ready(function () {
    $(".choose-row").css("display","none");

    // //关闭编辑区
    // $(document).click(function(){
    //     $(".choose-row").css("display","none");
    // });
    // $(".choose-row").click(function(){
    //     $(".choose-row").css("display","block");
    //     event.stopPropagation();
    // });
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
            setTimeout("$('#error-add-money').text('');",1000);
        },
        error: function () {
            $('#error-add-money').text('出故障了请稍候再试');
            setTimeout("$('#error-add-money').text('');",1000);
        }
    });
    setTimeout(refresh,2000);
    return true;

}

function pointChange() {
    clear();
    $(".choose-row").css("display","none");
    $(".point-change-row").css("display","block");
}

function pointChangeSave() {
    $(".choose-row").css("display","none");
}

function modifyPsw() {
    clear();
    $(".choose-row").css("display","none");
    $(".modify-psw-row").css("display","block");
}

function modifyPswSave() {
    $(".choose-row").css("display","none");
}

function modifyInfo() {
    $(".dynamic-info-input").attr("disabled",false);
    $(".btn-second").css("display","block");
    $(".btn-first").css("display","none");
}

function modifyInfoSave() {
    $(".dynamic-info-input").attr("disabled",true);
    $(".btn-first").css("display","block");
    $(".btn-second").css("display","none");
}

function modifyCancel() {
    refresh();
}

function clear(){
    $('.choose-row .reg-input').val("");
}