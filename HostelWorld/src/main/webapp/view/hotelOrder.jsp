<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>${hotel.name}</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/hotelList.css" rel="stylesheet">
    <link href="../css/hotelPlan.css" rel="stylesheet">
    <link href="../css/hotelOrder.css" rel="stylesheet">
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
                    <li class="active"><a href="/HotelWorld/hotel_list">预定客栈</a></li>
                    <li><a href="/HotelWorld/my_order">我的预定</a></li>
                    <li><a href="/HotelWorld/vip_analyse">我的消费分析</a></li>
                    <li><a href="/HotelWorld/vip_model">我的消费模型</a></li>
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
                    <span>房间介绍</span>
                </div>
                <div class="blank_div"></div>
                <div class="row room-row room-type-row">
                    <ul>
                        <li>房型：<span>${room.name}</span></li>
                        <li>价格：<span>￥${room.price}</span></li>
                        <li>还剩：<span>${room.num-room.orderNum}间</span></li>
                    </ul>
                </div>
                <c:choose>
                    <c:when test="${plans.size()==0}">
                        <p class="plans-title">无优惠计划！</p>
                    </c:when>
                    <c:otherwise>
                        <p class="plans-title">共有${plans.size()}个优惠计划！</p>
                        <div class="row plan-row">
                            <% int j=1;%>
                            <c:forEach items="${plans}" var="plan">
                                <div class="row room-row room-plan-row">
                                    <ul>
                                        <li class="short-li li-header plan-li-header"><%=j%></li>
                                        <li class="long-li">时间：
                                            <span>${plan.start}~${plan.end}</span>
                                        </li>
                                        <li>价格：<span>￥${plan.price}</span></li>
                                        <c:choose>
                                            <c:when test="${plan.num-plan.orderNum==0}">
                                                <li><span class="no-plan">已售完</span></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li>还剩：<span>${plan.num-plan.orderNum}间</span></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </div>
                                <%j++;%>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
                <div class="blank_div"></div>

            </div>
            <div class="row input-row">
                <div class="level-info-container">
                    <p>您是尊贵的${info.level}会员，可享受 ${info.level.charAt(3)}*2 %的优惠！</p>
                </div>
                <div class="title">
                    <span>填写订单</span>
                </div>
                <div class="blank_div"></div>
                <div class="row order-input-row">
                    <ul class="order-first-ul">
                        <li class="input-li">
                            <span class="n">入住：</span>
                            <input type="date" class="reg-input reg-order-input" id="order-time-start" name="name">
                        </li>

                        <li class="input-li">
                            <span class="n">离店：</span>
                            <input type="date" class="reg-input reg-order-input" id="order-time-end" name="name">
                        </li>
                        <li class="msg-li">
                            <span class="n">价格：</span>
                            <span class="cal_price">￥${room.price}</span>
                            <span class="origin_price">￥${room.price}</span>
                        </li>
                    </ul>
                    <ul class="order-second-ul">
                        <li class="input-li">
                            <span class="n">计划：</span>
                            <select class="type-select" id="plan-room-type">
                                <% int i=1;%>
                                <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不参加</option>
                                <c:forEach var="plan" items="${plans}">
                                    <option value="${plan.pId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计划<%=i%></option>
                                    <%i++;%>
                                </c:forEach>
                            </select>
                        </li>
                        <li class="input-li">
                            <span class="n">房数：</span>
                            <input type="text" class="reg-input reg-order-input" id="order-num" name="name">
                        </li>
                        <li class="msg-li">
                            <span class="border-btn order-btn" onclick="order()">预定</span>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <input type="hidden" id="vId" value="${vId}">
        <input type="hidden" id="rId" value="${room.rId}">
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
<script src="../js/hotelOrder.js"></script>
</body>
</html>
