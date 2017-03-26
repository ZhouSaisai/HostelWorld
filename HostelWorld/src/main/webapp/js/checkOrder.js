$(document).ready(function () {
    $(".input-row").hide();
    $('.choose-input').eq(0).show();
    $('.order-row-list').eq(0).show();
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

}

function comeHotel(i) {

}

function check() {
    
}

function checkNv() {

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