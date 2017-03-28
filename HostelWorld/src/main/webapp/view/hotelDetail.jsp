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
    <link href="../css/hotelDetail.css" rel="stylesheet">
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
                    <span>酒店介绍</span>
                </div>
                <ul class="show-hotel-ul">
                    <li>
                        <div class="show-header">
                            <img src="../img/hotel_order_4.jpg">
                        </div>
                    </li>
                    <li>
                        <div class="show-content-container">
                            <div class="show-content-title show-title-num">
                                共有 <span>${rooms.size()}</span> 种房型
                            </div>
                            <div class="show-content-left">
                                <div class="show-content-title show-title-name">
                                    名称：
                                    <span class="show-content show-content-name">${hotel.name}</span>
                                </div>
                                <div class="show-content-title show-title-tel">
                                    电话：
                                    <span class="show-content show-content-tel">${hotel.tel}</span>
                                </div>
                            </div>
                            <div class="show-content-right">
                                <div class="show-content-title show-title-level">
                                    星级：
                                    <span class="show-content show-content-level">${hotel.level}星级</span>
                                </div>
                                <div class="show-content-title show-title-address">
                                    地址：
                                    <span class="show-content application-content-address">${hotel.address}</span>
                                </div>
                            </div>

                            <div class="show-content-title show-title-description">
                                介绍："
                                <span>这是一段关于酒店的介绍因为实在没东西放了但不放点什么东西就太空了超尴尬虽然现在还是很别扭天啊我是谁我在哪我在干什么我为什么要写界面！</span>
                                "
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="row input-row">
                <div class="title">
                    <span>房型一览</span>
                </div>
                <c:choose>
                    <c:when test="${rooms.isEmpty()}">
                        <div class="notice room-type-notice">
                            <p>这个酒店太差了，没有房型咋住！</p>
                            <p>难道让我睡大街啊，我有一句...！</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="blank_div"></div>
                        <% int i = 0;%>
                        <c:forEach items="${rooms}" var="room">
                            <div class="row room-row room-type-row even-row">
                                <ul>
                                    <li class="short-li li-header">A</li>
                                    <li>房型：<span>${room.name}</span></li>
                                    <li>价格：<span>￥${room.price}</span></li>
                                    <c:choose>
                                        <c:when test="${room.num-room.orderNum==0}">
                                            <li><span class="no-plan">已售完</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>还剩：<span>${room.num-room.orderNum}间</span></li>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${room.plans.size()==0}">
                                            <li><span class="no-plan">无优惠计划</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><span class="has-plan" onclick="seePlans(<%=i%>)">${room.plans.size()}个优惠计划</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="short-li li-footer">
                                        <c:choose>
                                            <c:when test="${room.num-room.orderNum==0}">
                                                <span class="no-plan">售罄</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="li-btn" onclick="orderRoom(${room.rId})">预定</span>
                                            </c:otherwise>
                                    </c:choose>
                                    </li>
                                </ul>
                            </div>
                            <c:if test="${room.plans.size()!=0}">
                                <div class="row plan-row">
                                    <% int j =1;%>
                                    <c:forEach items="${room.plans}" var="plan">
                                        <div class="row room-row room-plan-row even-row">
                                            <ul>
                                                <li class="short-li li-header plan-li-header"><%=j%></li>
                                                <c:choose>
                                                    <c:when test="${plan.num-plan.orderNum==0}">
                                                        <li><span class="no-plan">已售完</span></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li>还剩：<span>${plan.num-plan.orderNum}间</span></li>
                                                    </c:otherwise>
                                                </c:choose>

                                                <li>价格：<span>￥${plan.price}</span></li>
                                                <li class="long-li">时间：<span>${plan.start}~${plan.end}</span></li>
                                            </ul>
                                        </div>
                                        <%j++;%>
                                    </c:forEach>
                                </div>
                                <%i++;%>
                            </c:if>
                        </c:forEach>
                        <div class="blank_div"></div>
                        <div class="blank_div"></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <input type="hidden" id="vId" value="${vId}">
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
<script src="../js/hotelDetail.js"></script>
</body>
</html>
