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
