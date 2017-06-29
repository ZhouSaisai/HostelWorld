<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的消费模型</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/analyse.css" rel="stylesheet">
    <link href="../css/vip_model.css" rel="stylesheet">
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
                <li><a href="/HotelWorld/welcome">首页</a></li>
                <li><a href="/HotelWorld/hotel_list">预定客栈</a></li>
                <li><a href="/HotelWorld/my_order">我的预定</a></li>
                <li><a href="/HotelWorld/vip_analyse">我的消费分析</a></li>
                <li class="active"><a href="/HotelWorld/vip_model">我的消费模型</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/HotelWorld/person_zone?id=${vId}">个人空间</a></li>
                <li><a href="/HotelWorld/ask_loginOut">注销</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>
<div class="container-fluid main-content">
    <div class="row content-row">
        <div class="row input-row">
            <div class="title">
                <span>消费情况</span>
            </div>
            <div class="row info-row">
                <ul>
                    <li>
                        <div class="info-div info-hotel-num">总客栈数：<span>${va.hotelNum}</span></div>
                    </li>
                    <li>
                        <div class="info-div info-order-num">总订单数：<span>${va.orderNum}</span></div>
                    </li>
                    <li>
                        <div class="info-div info-money-num">完成订单率：<span>${rates.get(4)}%</span></div>
                    </li>
                </ul>
            </div>

            <div class="row img-row">
                <ul>

                    <li>
                        <div id="scores">
                            <p class="p_right"><span>模型分析</span></p>
                            <p class="p_title">总体得分：<span class="small_score"><fmt:formatNumber type="number" value="${rates.get(5)}" pattern="0.00" maxFractionDigits="2"/></span> 分</p>
                            <p>分析：综合各方面考虑：您是一个具有较高价值的用户</p>
                            <p>消费能力：<span class="small_score">${rates.get(0)}</span> 分</p>
                            <p>分析：您的消费能力在全部用户中处于领先地位，具有较强经济实力</p>
                            <p>消费频次：<span class="small_score">${rates.get(1)}</span> 分</p>
                            <p>分析：您的消费频次在全部用户中处于较好地位，看来长期奔波劳累</p>
                            <p>消费跨度：<span class="small_score"><fmt:formatNumber type="number" value="${rates.get(2)}" pattern="0.00" maxFractionDigits="2"/></span> 分</p>
                            <p>分析：您的消费跨度在全部用户中处于一般地位，较为喜欢外出人士</p>
                            <p>消费广度：<span class="small_score"><fmt:formatNumber type="number" value="${rates.get(3)}" pattern="0.00" maxFractionDigits="2"/></span> 分</p>
                            <p>分析：您的消费广度在全部用户中处于很好地位，时间周期间隔较小</p>
                            <p>消费信率：<span class="small_score">${rates.get(4)}</span> 分</p>
                            <p>分析：您的消费信率在全部用户中处于领先地位，具有较高诚信度</p>
                        </div>
                    </li>

                    <li>
                        <div id="model"></div>
                    </li>
                </ul>
            </div>
        </div>


        <div class="row input-row">
            <div class="title">
                <span>多维分析</span>

            </div>
            <div class="row choose-wei-row">
                <div class="div_btn area_choose">
                    <span>地域选择：</span>
                    <select id="selProvince"><option value="">---请选择--</option></select>
                    <select id="selCity"><option value="">---请选择--</option></select>
                    <select id="selArea"><option value="">---请选择--</option></select>
                </div>

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
                <div class="pic_div" id="pic_time"></div>
            </div>
        </div>

        <div class="row input-row">
            <div class="title">
                <span>地域维分析</span>
            </div>
            <div class="row img-row">
                <div class="pic_div" id="pic_map"></div>
            </div>
            <div class="row img-row">
                <div class="pic_div" id="pic_small_map">
                    <p class="pic_remind">未选择省份</p>
                </div>

            </div>
        </div>

        <input type="hidden" id="vId" value="${vId}">
        <footer class="global-footer footer-fixed-bottom">
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
    <script src="../json/china.js"></script>
    <script src="../json/beijing.js"></script>
    <script src="../json/12.js"></script>
    <script src="../json/13.js"></script>
    <script src="../json/14.js"></script>
    <script src="../json/15.js"></script>
    <script src="../json/21.js"></script>
    <script src="../json/22.js"></script>
    <script src="../json/23.js"></script>
    <script src="../json/31.js"></script>
    <script src="../json/32.js"></script>
    <script src="../json/33.js"></script>
    <script src="../json/34.js"></script>
    <script src="../json/35.js"></script>
    <script src="../json/36.js"></script>
    <script src="../json/37.js"></script>
    <script src="../json/41.js"></script>
    <script src="../json/42.js"></script>
    <script src="../json/43.js"></script>
    <script src="../json/44.js"></script>
    <script src="../json/45.js"></script>
    <script src="../json/46.js"></script>
    <script src="../json/50.js"></script>
    <script src="../json/51.js"></script>
    <script src="../json/52.js"></script>
    <script src="../json/53.js"></script>
    <script src="../json/54.js"></script>
    <script src="../json/61.js"></script>
    <script src="../json/62.js"></script>
    <script src="../json/63.js"></script>
    <script src="../json/64.js"></script>
    <script src="../json/65.js"></script>
    <script src="../js/vipModel.js"></script>
    <script src="../js/district.js" type="text/javascript"></script>
</body>
</html>

