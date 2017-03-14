<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/login.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-color navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/HotelWorld/welcome"><img src="../img/common/logo.png"></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/HotelWorld/welcome">首页</a></li>
                    <li><a href="#">订单登记</a></li>
                    <li><a href="#">客栈统计</a></li>


                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">登录</a></li>
                    <li><a href="#">注册</a></li>
                    <li><a href="#">客栈空间</a></li>
                    <li><a href="#">注销</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
    <div class="container-fluid main-content">
        <div class="row" id="logReg">
            <div class="row">
                <div class="top-wrapper">
                    <img src="../img/common/login-md.png">
                    <p>您私人的便捷客栈管理平台</p>
                </div>
            </div>
            <div class="row">
                <div class="switch-wrapper">
                    <a onclick="tab(0)" id="login" class="switch-button active">登录</a>
                    <a onclick="tab(1)" id="register"  class="switch-button">开店</a>
                </div>
            </div>

            <div class="row" id="switch-content">
                <div class="login">
                    <div class="login-wrapper">
                        <form class="form-login">
                            <div class="input-wrapper">
                                <input type="text" name="usercode" id="usercode-login" placeholder="客栈识别码">
                            </div>

                            <div class="input-wrapper">
                                <input type="password" name="password" id="password-login" placeholder="密码">
                                <a onclick="forgetPwd()" data-href="/forgetPwd" class="tip">忘记密码</a>
                            </div>
                            <input type="button" class="button btn-block submit-login" value="登录" onclick="login('hotel')">

                            <div class="clearfix-wrapper">
                                <div class="errorField" id="error-login"></div>
                                <div class="input-wrapper checkbox-wrapper">
                                    <p>还没账户？<a onclick="tab(1)" >立即申请</a></p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="register">
                    <div class="register-wrapper">
                        <form>
                            <div class="register-tel">
                                <div class="input-wrapper">
                                    <input type="text" name="tel" id="username-register" placeholder="客栈名">
                                </div>
                                <!--防止记住用户名密码自动填充到页面-->
                                <input type="text" class="none">
                                <input type="password" class="none">

                                <div class="input-wrapper">
                                    <input type="password" name="passwordTel" id="password-register" placeholder="密码（6-20位，建议字母数字和符号的组合）">
                                </div>
                                <div class="input-wrapper">
                                    <input type="password" name="passwordAgain" id="password-register-a" placeholder="请再次输入密码">
                                </div>
                                <input type="button" class="button btn-block submit-register" value="申请开店" onclick="register('hotel')">
                                <div class="clearfix-wrapper">
                                    <div class="errorField" id="error-register"></div>
                                    <div class="input-wrapper checkbox-wrapper">
                                        <p>已有账户？<a onclick="tab(0)" >立即登录</a></p>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer-fixed-bottom global-footer">
            <div class="copyright">
                <p style="text-align: center;">
                    <span>© 2017 <a href="" target="_blank">HostelWorld</a></span>
                    <a class="icp" href="" target="_blank">苏ICP备110120130号</a>
                    <span>联系方式：110120130</span>
                    <span>HostelWorld Beta</span>
                    <a href="" target="_blank" class="agreement">zhouss.wel@foxmail.com</a>
                </p>
            </div>
        </footer>
    </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/login.js"></script>
</body>
</html>
