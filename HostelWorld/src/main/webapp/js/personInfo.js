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
    if(i==0){
        alert("必须大于1000");
    }
    clear();
    $(".choose-row").css("display","none");
    $(".add-money-row").css("display","block");
}

function addMoneySave() {
    $(".choose-row").css("display","none");
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