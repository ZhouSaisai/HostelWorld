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
    <link href="../css/analyse.css" rel="stylesheet">
    <link href="../css/vip_model.css" rel="stylesheet">
    <link href="../css/hotel_decision.css" rel="stylesheet">
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
                    <li class="active"><a href="/HotelWorld/hotel_decision">决策分析</a></li>
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
        <div class="row input-row">
            <div class="title">
                <span>决策分析</span>
            </div>
            <div class="row info-row">
                <ul>
                    <li>
                        <div class="info-div info-hotel-num">总用户数：<span>${ha.vipNum}</span></div>
                    </li>
                    <li>
                        <div class="info-div info-order-num">总订单数：<span>${ha.orderNum}</span></div>
                    </li>
                    <li>
                        <div class="info-div info-money-num">总订单额：<span>￥${ha.moneyNum}</span></div>
                    </li>
                </ul>
            </div>
            <div class="row img-row">

                <ul>
                    <li>
                        <div id="bar"></div>
                    </li>
                    <li>
                        <div id="line"></div>
                    </li>
                </ul>
            </div>
            <div class="row choose-wei-row">
                <div class="div_btn time_choose">
                    <span>时间选择:</span>
                    <span class="time_btn active" onclick="chooseTime(0)" id="time0">按天统计</span>
                    <span class="time_btn" onclick="chooseTime(1)" id="time1">按周统计</span>
                    <span class="time_btn" onclick="chooseTime(2)" id="time2">按月统计</span>
                    <span class="time_btn" onclick="chooseTime(3)" id="time3">按年统计</span>
                    <span class="time_btn refresh_btn" onclick="refreshTimeMap()">刷新</span>
                </div>
            </div>

            <div class="row img-row">
                <div class="pic_div" id="pic_calendar_big"></div>
            </div>
        </div>

        <div class="row input-row">
            <div class="title">
                <span>用户分析</span>

            </div>

            <div class="row img-row">
                <ul>
                    <li>
                        <%--用户分类表-数--%>
                        <div id="pic_user_1" style="height: 400px"></div>
                    </li>
                    <li>
                        <%--用户分类表-钱--%>
                        <div id="pic_user_2" style="height: 400px"></div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="row input-row">
            <div class="title">
                <span>预测分析</span>

            </div>

            <div class="row img-row">
                <div class="pic_div" id="pic_add_line"></div>
            </div>
        </div>
        <br>
        <input type="hidden" id="hId" value="${info.hId}">
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
    <script src="../js/echarts.min.js"></script>
    <script src="../js/hotelDecision.js"></script>
</body>
</html>

