<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的预定</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/order.css" rel="stylesheet">
    <link href="../css/personOrder.css" rel="stylesheet">
    <link href="../css/checkOrder.css" rel="stylesheet">
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
            <div class="row">
                <div class="switch-wrapper-one">
                    <a onclick="switchButton(0)" id="vipCheck" class="switch-button active">会员入住</a>
                    <a onclick="switchButton(1)" id="publicCheck"  class="switch-button">非会员入住</a>
                </div>
            </div>
            <div class="row input-row choose-input">
                <%--<div class="search-code-div">--%>
                    <%--<input type="text" class="reg-input reg-search-input" id="check-code">--%>
                    <%--<span class="border-btn search-btn" onclick="searchCode()">搜索</span>--%>
                <%--</div>--%>
                <div class="title">
                    <span>会员入住</span>
                </div>
                <div class="blank_div"></div>
                <div class="row check-input-row">
                    <ul class="check-first-ul">
                        <li class="first-li">
                            <span class="n">订单号：</span>
                            <input type="text" class="reg-input reg-check-input-short" id="check-room-code">
                            <span class="border-btn check-btn" onclick="check()">入住</span>
                        </li>
                        <li>
                            <span class="n">房型&nbsp;&nbsp;&nbsp;：</span>
                            <span id="check-room-type">无数据</span>
                        </li>
                        <li>
                            <span class="n">入住时间：</span>
                            <span id="check-room-start">无数据</span>
                        </li>
                    </ul>
                    <ul class="check-second-ul">
                        <li class="first-li">
                            <span class="n">房间号：</span>
                            <input type="text" class="reg-input reg-check-input" id="check-room-ids">
                            <small>(用分号隔开)</small>
                        </li>
                        <li>
                            <span class="n">房间数：</span>
                            <span id="check-room-num">无数据</span>
                        </li>
                        <li>
                            <span class="n">离店时间：</span>
                            <span id="check-room-end">无数据</span>
                        </li>
                    </ul>
                    <ul class="check-third-ul">
                        <li class="first-li">
                            <span class="n">房客名：</span>
                            <input type="text" class="reg-input reg-check-input" id="check-costumer-names">
                            <small>(用分号隔开)</small>
                        </li>
                        <li>
                            <span class="n">总价格：</span>
                            <span id="check-room-price">无数据</span>
                        </li>
                        <li>
                            <span class="n">预定时间：</span>
                            <span id="check-room-time">无数据</span>
                        </li>
                    </ul>
                </div>
                <div class="blank_div"></div>
            </div>


            <div class="row input-row choose-input">
                <div class="title">
                    <span>非会员入住</span>
                </div>
                <div class="blank_div"></div>
                <div class="row check-input-row">
                    <ul class="check-fourth-ul">
                        <li>
                            <span class="n">房间号：</span>
                            <input type="text" class="reg-input reg-check-input-nv" id="check-room-ids-nv">
                            <small>(用分号隔开)</small>
                        </li>
                        <li>
                            <span class="n">房客姓名：</span>
                            <input type="text" class="reg-input reg-check-input-nv" id="check-costumer-names-nv">
                            <small>(用分号隔开)</small>
                        </li>
                        <li class="short-li">
                            <span>价格</span>
                        </li>
                    </ul>
                    <ul class="check-fifth-ul">
                        <li>
                            <span class="n">选房型：</span>
                            <select class="type-select" id="check-room-type-nv">
                                <c:forEach var="room" items="${rooms}">
                                    <option value="${room.rId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${room.name}&nbsp;&nbsp;${room.price}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li>
                            <span class="n">入住时间：</span>
                            <input type="date" class="reg-input reg-check-input-nv" id="check-room-start-nv">
                        </li>
                        <li class="short-li">
                            <span id="check-room-price-nv">￥0.0</span>
                        </li>
                    </ul>
                    <ul class="check-sixth-ul">
                        <li>
                            <span class="n">房间数：</span>
                            <input type="text" class="reg-input reg-check-input-nv" id="check-room-num-nv">
                        </li>
                        <li>
                            <span class="n">离店时间：</span>
                            <input type="date" class="reg-input reg-check-input-nv" id="check-room-end-nv">
                        </li>
                        <li class="short-li">
                            <span class="border-btn check-btn-nv" onclick="checkNv()">入住</span>
                        </li>
                    </ul>
                </div>
                <div class="blank_div"></div>
            </div>
            <div class="blank_div"></div>
            <div class="blank_div"></div>
            <div class="blank_div"></div>
            <div class="row">
                <div class="switch-wrapper">
                    <a onclick="tab(0)" id="ordering" class="switch-button active">预约订单</a>
                    <a onclick="tab(1)" id="ordered"  class="switch-button">正在入住</a>
                    <a onclick="tab(2)" id="checked"  class="switch-button">完成订单</a>
                </div>
            </div>
            <div class="row input-row order-row-list">
                <div class="title">
                    <span>预约订单</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${orderingOrders.size()==0}">
                        <div class="blank_div"></div>
                        <p class="notice">当前没有预约的订单，赶快多宣传一下吧！</p>
                    </c:when>
                    <c:otherwise>
                        <% int i =1;%>
                        <c:forEach var="order" items="${orderingOrders}">
                            <div class="row order-row">
                                <ul>
                                    <li class="short-li"><%=i%></li>
                                    <li>${order.code}</li>
                                    <li class="mid-li"><c:out value="${fn:substring(order.time, 0, 19)}" /></li>
                                    <li>${order.room.hotel.name}</li>
                                    <li>${order.room.name}</li>
                                    <li>${order.num}间房</li>
                                    <li>￥${order.nowPrice}</li>
                                    <li class="long-li">${order.start}~${order.end}</li>
                                    <li class="last-li"><a class="cancel-button" onclick="comeHotel(<%=i%>)">入住</a></li>
                                </ul>
                            </div>
                            <% i++; %>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
            </div>
            <div class="row input-row order-row-list">
                <div class="title">
                    <span>正在入住</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${orderedOrders.size()==0}">
                        <div class="blank_div"></div>
                        <p class="notice">当前没有入住的订单，看看有没有预定的先！</p>
                    </c:when>
                    <c:otherwise>
                        <% int j =1;%>
                        <c:forEach var="order" items="${orderedOrders}">
                            <div class="row order-row">
                                <ul>
                                    <li class="short-li"><%=j%></li>
                                    <li>${order.code}</li>
                                    <li class="mid-li"><c:out value="${fn:substring(order.time, 0, 19)}" /></li>
                                    <li>${order.room.hotel.name}</li>
                                    <li>${order.room.name}</li>
                                    <li>${order.num}间房</li>
                                    <li>￥${order.nowPrice}</li>
                                    <li class="long-li">${order.start}~${order.end}</li>
                                    <li class="last-li"><a class="cancel-button" onclick="leaveHotel(${order.oId})">退房</a></li>
                                </ul>
                            </div>
                            <% j++; %>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
            </div>
            <div class="row input-row order-row-list">
                <div class="title">
                    <span>完成订单</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${checkedOrders.size()==0}">
                        <div class="blank_div"></div>
                        <p class="notice">当前没有完成的订单，看看有没有预定的先！</p>
                    </c:when>
                    <c:otherwise>
                        <% int z =1;%>
                        <c:forEach var="order" items="${checkedOrders}">
                            <div class="row order-row">
                                <ul>
                                    <li class="short-li"><%=z%></li>
                                    <li>${order.code}</li>
                                    <li class="mid-li"><c:out value="${fn:substring(order.time, 0, 19)}" /></li>
                                    <li>${order.room.hotel.name}</li>
                                    <li>${order.room.name}</li>
                                    <li>${order.num}间房</li>
                                    <li>￥${order.nowPrice}</li>
                                    <li class="long-li">${order.start}~${order.end}</li>
                                    <li class="last-li">已退房</li>
                                </ul>
                            </div>
                            <% z++; %>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
            </div>
        </div>
        <input type="hidden" id="hId" value="${hId}">
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
<script src="../js/checkOrder.js"></script>

</body>
</html>
