<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>客栈统计</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/pageGroup.css" rel="stylesheet">
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
                <c:if test="${info.state==1}">
                    <li><a href="/HotelWorld/hotel_plan">发布计划</a></li>
                    <li><a href="/HotelWorld/hotel_check">订单登记</a></li>
                    <li><a href="/HotelWorld/hotel_analyse">客栈统计</a></li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/HotelWorld/hotel_zone?id=${info.hId}">客栈空间</a></li>
                <li><a href="/HotelWorld/ask_loginOut">注销</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>
<div class="container-fluid main-content">
    <div class="row content-row">
        <p>这是一个分析界面</p>
        <br>
        <br>
        <br>
        <br>
        <footer class="global-footer">
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
    <script src="../js/pageGroup.js"></script>
    <script src="../js/personInfo.js"></script>
</body>
</html>

