$(document).ready(function () {
    $(".input-row").hide();
    $('.input-row').eq(0).show();
});

function tab(i){
    $(".switch-wrapper a").removeClass("active");
    $(".switch-wrapper").find("a").eq(i).addClass("active");

    $(".input-row").hide();
    $('.input-row').eq(i).show();
}


function cancel(i){
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/cancel_order",
        data: {
            "oId": i
        },
        success: function (result) {
            alert(result);
            window.location.reload();
        },
        error: function () {
            $('#error-register').text('出故障了请稍候再试');
            setTimeout("$('#error-register').text('');",1000);
        }
    });
}