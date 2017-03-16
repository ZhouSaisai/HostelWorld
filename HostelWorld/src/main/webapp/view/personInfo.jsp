<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>个人空间</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/personInfo.css" rel="stylesheet">
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
                    <li><a href="#">预定客栈</a></li>
                    <li><a href="#">我的预定</a></li>
                    <li><a href="#">我的消费分析</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">个人空间</a></li>
                    <li><a href="/HotelWorld/ask_loginOut">注销</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
    <div class="container-fluid main-content">
        <div class="row content-row">
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 info-container">
                <div class="row basic-info-row">
                    <div class="row pic-container">
                        <img src="../img/common/user.jpg" class="user-pic">
                    </div>
                    <div class="row">
                        <div class="row info-line">
                            <p class="info-name">v160907 汤旭</p>
                            <small class="info-state">未激活</small>
                        </div>
                        <div class="row">
                            <a class="info-operate">去激活</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="row info-line">
                            <p class="info-name">v160907 汤旭</p>
                            <small class="info-state"  id="info-welcome">欢迎你</small>
                        </div>
                        <div class="row">
                            <a class="info-operate">停用账号</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="row info-line">
                            <p class="info-name">v160907 汤旭</p>
                            <small class="info-state" id="info-warning">已暂停</small>
                        </div>
                        <div class="row">
                            <a class="info-operate">充值恢复</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="row info-line" id="score-line">
                            <p>等级：lv5</p>
                            <p>积分：500</p>
                            <p>余额：5000</p>
                            <a class="info-operate">积分兑换</a>
                            <a class="info-operate">账号充值</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-8 col-xs-7">

                <div class="row notice-row">
                    <div id="success-notice">
                        <img src="../img/common/success.svg">
                        <p id="success-notice-p">注册成功！</p>
                    </div>
                    <p id="notice-code-name">您的会员卡号是: <span id="notice-code">v160907</span></p>
                    <p>该账号将用作您的唯一登录账号，充值1000以上即可激活!  <a class="active-href">去激活>></a></p>
                </div>
                <div class="row">
                    <p>基础资料</p>
                    <p>该账号将用作你的唯一登录账号</p>
                    <button>修改资料</button>
                </div>
                <div class="row">
                    <p>充值</p>
                    <p>确认充值</p>
                    <button>修改资料</button>
                </div>
                <div class="row">
                    <p>积分兑换</p>
                    <p>确认兑换</p>
                    <button>修改资料</button>
                </div>
                <div class="row">
                    <p>修改密码</p>
                    <p>输入原密码</p>
                    <p>输入新密码</p>
                    <button>修改资料</button>
                </div>
            </div>
        </div>
        <footer class="footer-fixed-bottom global-footer">
            <div class="copyright">
                <p style="text-align: center;">
                    <span>© 2017 <a href="/HotelWorld/welcome" target="_blank">HostelWorld</a></span>
                    <a class="icp" href="/HotelWorld/welcome" target="_blank">苏ICP备110120号</a>
                    <span>联系方式：110120130</span>
                    <span>HostelWorld Beta</span>
                    <a href="/HotelWorld/welcome" target="_blank" class="agreement">zhouss.wel@foxmail.com</a>
                </p>
            </div>
        </footer>
    </div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</body>
</html>
