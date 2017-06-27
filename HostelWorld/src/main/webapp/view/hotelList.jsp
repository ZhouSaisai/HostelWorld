<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>预定酒店</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/hotelList.css" rel="stylesheet">
    <link href="../css/orderHotel.css" rel="stylesheet">
    <style>
        .list-background{
            width:100%;
            height:50%;
            margin-bottom: -10px;
        }
    </style>
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

    <div class="orders-header">
        <img src="../img/welcome-bgi-sm2.jpg" class="list-background">
    </div>

    <div class="container-fluid main-content">
        <div class="row content-row">
            <div class="row input-row">
                <div class="title">
                    <span>精选推荐</span>
                </div>
                <div class="row recommend-hotel-row">
                    <div class="col-lg-4 col-md-4">
                        <div class="recommend-hotel-col">
                            <img class="order_hotel_img" src="../img/hotel_order_4.jpg">
                            <div class="recommend-hotel-content recommend-hotel-content-left">
                                <p class="order_hotel_name">仙林客栈</p>
                                <p class="order_hotel_address">南京市栖霞区仙林大道164号</p>
                            </div>
                            <div class="recommend-hotel-content recommend-hotel-content-right">
                                <span class="border-btn order-btn" onclick="order(25)">预定</span>
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="recommend-hotel-col">
                            <img class="order_hotel_img" src="../img/hotel_order_3.jpg">
                            <div class="recommend-hotel-content recommend-hotel-content-left">
                                <p class="order_hotel_name">木叶客栈</p>
                                <p class="order_hotel_address">火之国木叶村一乐外卖旁边</p>
                            </div>
                            <div class="recommend-hotel-content recommend-hotel-content-right">
                                <span class="border-btn order-btn" onclick="order(28)">预定</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="recommend-hotel-col">
                            <img class="order_hotel_img" src="../img/hotel_order_2.jpg">
                            <div class="recommend-hotel-content recommend-hotel-content-left">
                                <p class="order_hotel_name">九棵松客栈</p>
                                <p class="order_hotel_address">北京市九棵松体育馆旁</p>
                            </div>
                            <div class="recommend-hotel-content recommend-hotel-content-right">
                                <span class="border-btn order-btn" onclick="order(34)">预定</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row input-row">
                <div class="title">
                    <span>全部酒店</span>
                </div>
                <c:choose>
                    <c:when test="${hotels.size()==0}">
                        <br>
                        <p style="text-align: center">当前没有开店的客栈！</p>
                        <br>
                    </c:when>
                    <c:otherwise>
                        <% int i=1;%>
                        <c:forEach items="${hotels}" var="hotel" varStatus="vs">
                            <div class="row application-row open-hotel-row">
                            <ul class="open-hotel-ul">
                                <li>
                                    <div class="application-header">
                                        <span class="order"><%=i%></span>
                                        <img src="../img/common/hotel<%=i%5%>.jpg">
                                    </div>
                                </li>
                                <li>
                                    <div class="application-content-container">
                                        <div class="application-content-left">
                                            <div class="application-content-title application-title-name">
                                                名称：
                                                <span class="application-content application-content-name">${hotel.name}</span>
                                            </div>
                                            <div class="application-content-title application-title-tel">
                                                电话：
                                                <span class="application-content application-content-tel">${hotel.tel}</span>
                                            </div>
                                        </div>
                                        <div class="application-content-right">
                                            <div class="application-content-title application-title-level">
                                                星级：
                                                <span class="application-content application-content-level">${hotel.level}星级</span>
                                            </div>
                                            <div class="application-content-title application-title-address">
                                                地址：
                                                <span class="application-content application-content-address">${hotel.address}</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="application-operate-container">
                                        <span class="border-btn application-operate-btn" onclick="order(${hotel.hId})">预定</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <%i++;%>
                    </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
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
<script>
    function order(hId) {
        window.location.href="/HotelWorld/hotel_detail?id="+hId;
    }
</script>
</body>
</html>
