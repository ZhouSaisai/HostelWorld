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
                    <li class="active"><a href="/HotelWorld/my_order">我的预定</a></li>
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
            <div class="row">
                <div class="switch-wrapper">
                    <a onclick="tab(0)" id="ordering" class="switch-button active">预约订单</a>
                    <a onclick="tab(1)" id="ordered"  class="switch-button">正在入住</a>
                    <a onclick="tab(2)" id="checked"  class="switch-button">完成订单</a>
                    <a onclick="tab(3)" id="cancel"  class="switch-button">取消订单</a>
                </div>
            </div>
            <div class="row input-row">
                <div class="title">
                    <span>预约订单</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${orderingOrders.size()==0}">
                        <p class="notice">当前没有预约的订单，赶快预定一家吧！</p>
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
                                    <li class="last-li"><a class="cancel-button" onclick="cancel(${order.oId})">取消</a></li>
                                </ul>
                            </div>
                            <% i++; %>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
            </div>
            <div class="row input-row">
                <div class="title">
                    <span>正在入住</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${orderedOrders.size()==0}">
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
                                    <li class="last-li">已入住</li>
                                </ul>
                            </div>
                            <% j++; %>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
            </div>
            <div class="row input-row">
                <div class="title">
                    <span>完成订单</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${checkedOrders.size()==0}">
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
            <div class="row input-row">
                <div class="title">
                    <span>取消订单</span>
                </div>
                <div class="blank_div"></div>
                <c:choose>
                    <c:when test="${cancelOrders.size()==0}">
                        <p class="notice">当前没有取消的订单，说明您很鸡汁哦！</p>
                    </c:when>
                    <c:otherwise>
                        <% int k =1;%>
                        <c:forEach var="order" items="${cancelOrders}">
                            <div class="row order-row">
                                <ul>
                                    <li class="short-li"><%=k%></li>
                                    <li>${order.code}</li>
                                    <li class="mid-li"><c:out value="${fn:substring(order.time, 0, 19)}" /></li>
                                    <li>${order.room.hotel.name}</li>
                                    <li>${order.room.name}</li>
                                    <li>${order.num}间房</li>
                                    <li>￥${order.nowPrice}</li>
                                    <li class="long-li">${order.start}~${order.end}</li>
                                    <li class="last-li">已取消</li>
                                </ul>
                            </div>
                            <% k++; %>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <div class="blank_div"></div>
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
<script src="../js/personOrder.js"></script>
</body>
</html>
