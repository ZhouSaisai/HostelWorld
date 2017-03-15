$(document).ready(function(){

});

function tab(i){
    $("#logReg .switch-wrapper a").removeClass("active");
    $("#logReg .switch-wrapper").find("a").eq(i).addClass("active");
    if(i==0){
        $('#switch-content .login').css("display","block");
        $("#switch-content .register").css("display","none");

    }else if(i==1){
        $("#switch-content .register").css("display","block");
        $("#switch-content .login").css("display","none");
    }
}
function login(type){
    var usercode = $("#usercode-login").val();
    var password = $("#password-login").val();

    if(usercode==""){
        $("#error-login").text("账号不能为空！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }else if(password==""){
        $("#error-login").text("密码不能为空！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }
    if(password.length>20||password.length<6){
        $("#error-login").text("密码长度为6-20！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }
    if(type!="person"&&type!="hotel"){
        $("#error-login").text("参数错误！");
        setTimeout("$('#error-login').text('');",1000);
        return false;
    }

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/ask_login",
        data: {
            "usercode": usercode,
            "password": password,
            "type":type
        },
        success: function (result) {
            var array=result.split(";");
            if (array[0]!= "-1") {
                switch (array[1]){
                    case "person":
                        window.location.href = "/HotelWorld/person_zone?id="+array[0];
                        break;
                    case "hotel":
                        window.location.href = "/HotelWorld/hotel_zone?id="+array[0];
                        break;
                    case "manager":
                        alert("manage");
                        break;
                    default:
                        $("#error-login").text("系统故障！");
                        setTimeout(function() {
                            $('#error-login').text('');
                        },1000);
                }
            } else {
                $('#error-login').text(array[1]);
                setTimeout(function() {
                    $('#error-login').text('');
                },1000);
            }
        },
        error: function () {
            $("#error-login").text("出故障了请稍候再试");
            setTimeout(function() {
                $('#error-login').text('');
            },1000);
        }
    });

    return true;

}

function register(type) {
    var username = $("#username-register").val();
    var password = $("#password-register").val();
    var passwordA = $("#password-register-a").val();

    if(username==""){
        $("#error-register").text("账号不能为空！");
        setTimeout("$('#error-register').text('');",1000);
        return false;
    }else if(password==""||password==""){
        $("#error-register").text("密码不能为空！");
        setTimeout("$('#error-register').text('');",1000);
        return false;
    }else if(password.length>20||password.length<6){
        $("#error-register").text("密码长度为6-20！");
        setTimeout("$('#error-register').text('');",1000);
        return false;
    }else if(password!==passwordA){
        $("#error-register").text("前后密码不一致！");
        setTimeout("$('#error-register').text('');",1000);
        return false;
    }
    if(type!="person"&&type!="hotel"){
        $("#error-login").text("参数错误！");
        setTimeout("$('#error-register').text('');",1000);
        return false;
    }
    var reg = /^[0-9]*$/;

    $.ajax({
        type: "post",
        async: true,
        url: "/HotelWorld/ask_register",
        data: {
            "username": username,
            "password": password,
            "type":type
        },
        success: function (result) {
            var array=result.split(";");
            if (array[0]!= "-1") {
                switch (array[1]){
                    case "person":
                        window.location.href = "/HotelWorld/person_zone?id="+array[0];
                        break;
                    case "hotel":
                        window.location.href = "/HotelWorld/hotel_zone?id="+array[0];
                        break;
                    default:
                        $('#error-register').text('系统故障');
                        setTimeout("$('#error-register').text('');",1000);
                }

            } else {
                $('#error-register').text(array[1]);
                setTimeout("$('#error-register').text('');",1000);
            }
        },
        error: function () {
            $('#error-register').text('出故障了请稍候再试');
            setTimeout("$('#error-register').text('');",1000);
        }
    });
    return true;
}

function forgetPwd() {
    $("#error-login").text("暂未实现，敬请期待！");
    setTimeout("$('#error-login').text('');",1000);
    return false;
}
