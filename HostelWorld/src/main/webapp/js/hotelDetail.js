$(document).ready(function () {
    $(".choose-row").css("display","none");
    var typeNum = $('.room-type-row .li-header').length;
    var charList = 'ABCDEFGHIGKLMOPQRSTUVWXYZ';
    for(var i=0;i<typeNum;i++){
        $('.room-type-row .li-header').eq(i).text(charList.charAt(i));
    }
    $(".plan-row").hide();
});

function orderRoom(rId) {
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


function seePlans(i) {
    var num = $(".plan-row").length;
    $(".plan-row").slice(0,i).hide();
    $(".plan-row").slice(i+1,num).hide();
    $(".plan-row").eq(i).toggle();
}
function refresh(){
    window.location.reload();
}