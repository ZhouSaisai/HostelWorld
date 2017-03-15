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
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5" style="background-color: red">
                <div class="row">
                    <div class="pic-container">
                        <img src="../img/common/user.jpg" class="user-pic">
                    </div>

                    <p>v160907<span> lv 5</span></p>
                    <p>汤旭</p>
                    <p>余额：<span>10000</span></p>
                    <button>充值</button>
                    <button>暂停账号</button>
                    <button>去激活</button>
                </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-8 col-xs-7" style="background-color: #1d62bb">
                <div class="row">
                    <p>你的账号是<span>v160907</span></p>
                    <p>该账号将用作你的唯一登录账号</p>
                    <button>去激活</button>
                </div>
                <div class="row">
                    <p>基础资料</p>
                    <p>该账号将用作你的唯一登录账号</p>
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
</body>
</html>
