<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>发布计划</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/hotelPlan.css" rel="stylesheet">
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
                        <li><a href="#">发布计划</a></li>
                        <li><a href="#">订单登记</a></li>
                        <li><a href="#">客栈统计</a></li>
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
                <div class="room-button-container">
                    <a class="room-add-button" target="_blank">增加房型</a>
                </div>
                <div class="title">
                    <span>房型一览</span>
                </div>

                <div class="notice room-type-notice">
                    <p>还没有录入房型哦!</p>
                    <p>快来添加,不然是发布不了计划的！</p>
                </div>
                <div class="blank_div"></div>
                <div class="row room-row room-type-row even-row">
                    <ul>
                        <li class="short-li">房型1</li>
                        <li>房型：单人房</li>
                        <li>价格：￥500</li>
                        <li>数量：30间</li>
                        <li class="short-li">
                            <span class="li-btn" onclick="manage_modify_app()">删除</span>
                        </li>
                    </ul>
                </div>
                <div class="row room-row room-type-row even-row">
                    <ul>
                        <li class="short-li">房型1</li>
                        <li>单人房</li>
                        <li>￥500</li>
                        <li>30间</li>
                        <li class="short-li">删除</li>
                    </ul>
                </div>
                <div class="row room-row room-type-row even-row">
                    <ul>
                        <li>房型1</li>
                        <li>单人房</li>
                        <li>￥500</li>
                        <li>30间</li>
                    </ul>
                </div>
                <div class="blank_div"></div>
            </div>

            <div class="row input-row">
                <div class="title">
                    <span>已有计划</span>
                </div>

                <div class="row application-row modify-hotel-row">
                    <ul class="open-hotel-ul">

                        <li>
                            <div class="application-content-container">
                                <div class="application-content-left">
                                    <div class="application-content-title application-title-name">
                                        名称：

                                        <span class="application-content application-content-name">{info.name}</span>


                                    </div>
                                    <div class="application-content-title application-title-level">
                                        星级：
                                        <span class="application-content application-content-level">{app.hotel.level}星级</span>
                                    </div>
                                    <div class="application-content-title application-title-tel">
                                        电话：

                                        <span class="application-content application-content-name">{app.hotel.tel}</span>

                                    </div>
                                </div>
                                <div class="application-content-right">
                                    <div class="application-content-title application-title-id">
                                        编号：
                                        <span class="application-content application-content-id">{app.hotel.code}</span>
                                    </div>
                                    <div class="application-content-title application-title-level">
                                        时间：
                                        <span class="application-content application-content-level">{app.time}</span>
                                    </div>
                                    <div class="application-content-title application-title-address">
                                        地址：

                                        <span class="application-content application-content-name">{app.hotel.address}</span>

                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="application-operate-container">
                                <span class="border-btn application-operate-btn" onclick="manage_modify_app()">删除</span>

                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row input-row">
                <div class="title">
                    <span>已有计划</span>
                </div>
               
                            <div class="row application-row modify-hotel-row">
                                <ul class="open-hotel-ul">
                                    
                                    <li>
                                        <div class="application-content-container">
                                            <div class="application-content-left">
                                                <div class="application-content-title application-title-name">
                                                    名称：
                                                    
                                                            <span class="application-content application-content-name">{info.name}</span>
                                                        

                                                </div>
                                                <div class="application-content-title application-title-level">
                                                    星级：
                                                    <span class="application-content application-content-level">{app.hotel.level}星级</span>
                                                </div>
                                                <div class="application-content-title application-title-tel">
                                                    电话：
                                                    
                                                            <span class="application-content application-content-name">{app.hotel.tel}</span>
                                                        
                                                </div>
                                            </div>
                                            <div class="application-content-right">
                                                <div class="application-content-title application-title-id">
                                                    编号：
                                                    <span class="application-content application-content-id">{app.hotel.code}</span>
                                                </div>
                                                <div class="application-content-title application-title-level">
                                                    时间：
                                                    <span class="application-content application-content-level">{app.time}</span>
                                                </div>
                                                <div class="application-content-title application-title-address">
                                                    地址：
                                                  
                                                            <span class="application-content application-content-name">{app.hotel.address}</span>
                                                       
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="application-operate-container">
                                            <span class="border-btn application-operate-btn" onclick="manage_modify_app()">删除</span>
                                           
                                        </div>
                                    </li>
                                </ul>
                            </div>
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
<script src="../js/manageApprove.js"></script>
<script src="../js/hotelPlan.js"></script>
</body>
</html>
