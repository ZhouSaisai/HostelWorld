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
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="../img/common/logo.png"></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">股票 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">策略</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">板块 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
    <div class="container-fluid main-content">
        <div class="row" id="logReg">
            <div class="row">
                <div class="top-wrapper">
                    <img src="../img/common/login-lg.png">
                    <p>您私人的云端量化研究与交易平台</p>
                </div>
            </div>
            <div class="row">
                <div class="switch-wrapper">
                    <a onclick="tab(0)" id="login" class="switch-button active">登录</a>
                    <a onclick="tab(1)" id="register"  class="switch-button">注册</a>
                </div>
            </div>

            <div class="row" id="switch-content">
                <div class="login">
                    <div class="login-wrapper">
                        <form class="form-login">
                            <div class="input-wrapper">
                                <input type="text" name="username" id="username-login" placeholder="用户名">
                            </div>

                            <div class="input-wrapper">
                                <input type="password" name="password" id="password-login" placeholder="密码">
                                <a onclick="forgetPwd()" data-href="/forgetPwd" class="tip">忘记密码</a>
                            </div>
                            <input type="button" class="button btn-block submit-login" value="登录" onclick="login()">

                            <div class="clearfix-wrapper">
                                <div class="errorField" id="error-login"></div>
                                <div class="input-wrapper checkbox-wrapper">
                                    <p>还没账户？<a onclick="tab(1)" >立即注册</a></p>
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
                                    <input type="text" name="tel" id="username-register" placeholder="用户名">
                                </div>
                                <!--防止记住用户名密码自动填充到页面-->
                                <input type="text" class="none">
                                <input type="password" class="none">

                                <div class="input-wrapper">
                                    <input type="password" name="passwordTel" id="password-register" placeholder="密码（6-20位，建议字母数字和符号的组合）">
                                </div>
                                <div class="input-wrapper">
                                    <input type="text" name="passwordAgain" id="password-register-a" placeholder="请再次输入密码">
                                </div>
                                <input type="button" class="button btn-block submit-register" value="注册" onclick="register()">
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
                    <span>© 2017 <a href="" target="_blank">AnyQuant</a></span>
                    <a class="icp" href="" target="_blank">苏ICP备110120130号</a>
                    <span>联系方式：110120130</span>
                    <span>AnyQuant Beta</span>
                    <a href="" target="_blank" class="agreement">Find Password Team</a>
                </p>
            </div>
        </footer>
    </div>

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/login.js"></script>
</body>
</html>
