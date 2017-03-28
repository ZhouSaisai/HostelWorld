function settleMoney(hId) {
    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/manage_settle_money",
        data: {
            "hId":hId
        },
        success: function (result) {
            alert(result);
            setTimeout(function () {
                window.location.reload();
            },1000);
        },
        error: function () {
            alert("操作失败！");
        }
    });
}