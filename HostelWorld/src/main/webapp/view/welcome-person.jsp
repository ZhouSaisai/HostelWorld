<%@ page import="com.edu.nju.wel.info.PersonInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>欢迎来到HostelWorld的世界</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/welcome.css" rel="stylesheet">
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
            <a class="navbar-brand" href="#"><img src="../img/common/logo.png"></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/HotelWorld/welcome">首页</a></li>
                <li><a href="/HotelWorld/hotel_list">预定客栈</a></li>
                <li><a href="/HotelWorld/my_order">我的预定</a></li>
                <li><a href="/HotelWorld/vip_analyse">我的消费分析</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/HotelWorld/person_zone?id=<%=((PersonInfo)session.getAttribute("info")).getId()%>">个人空间</a></li>
                <li><a href="ask_loginOut">注销</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>
<div class="container-fluid main-content">
    <div class="row" id="logReg">
        <div class="row">
            <div class="top-wrapper">
                <img src="../img/common/login-lg.png">
                <p>您私人的在外居家客栈平台</p>
            </div>
        </div>
        <div class="row">
            <div class="switch-buttons">
                <a id="single" href="/HotelWorld/hotel_list">立即预定</a>
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