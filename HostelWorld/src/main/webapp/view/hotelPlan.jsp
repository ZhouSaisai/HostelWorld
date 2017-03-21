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
                    <a class="room-add-button" target="_blank" onclick="addRoom()">增加房型</a>
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
                        <li class="short-li li-header">A</li>
                        <li>房型：<span>单人房</span></li>
                        <li>价格：<span>￥500</span></li>
                        <li>数量：<span>30间</span></li>
                        <li class="short-li li-footer">
                            <span class="li-btn" onclick="manage_modify_app()">删除</span>
                        </li>
                    </ul>
                </div>
                <div class="row room-row room-type-row even-row">
                    <ul>
                        <li class="short-li li-header">B</li>
                        <li>房型：<span>双人房</span></li>
                        <li>价格：<span>￥500</span></li>
                        <li>数量：<span>30间</span></li>
                        <li class="short-li li-footer">
                            <span class="li-btn" onclick="manage_modify_app()">删除</span>
                        </li>
                    </ul>
                </div>
                <div class="row room-row room-type-row even-row">
                    <ul>
                        <li class="short-li li-header">C</li>
                        <li>房型：<span>总统房</span></li>
                        <li>价格：<span>￥5000</span></li>
                        <li>数量：<span>30间</span></li>
                        <li class="short-li li-footer">
                            <span class="li-btn" onclick="manage_modify_app()">删除</span>
                        </li>
                    </ul>
                </div>
                <div class="blank_div"></div>
                <div class="blank_div"></div>
            </div>

            <div class="row input-row choose-row add-room-row">
                <div class="title">
                    <span>增加房型</span>
                </div>
                <div class="blank_div"></div>
                <div class="row add-room-info">
                    <ul>
                        <li>
                            <span class="n">房型：</span>
                            <input type="text" class="reg-input inline-reg-input" id="type-room-name" name="name">
                        </li>
                        <li>
                            <span class="n">价格：</span>
                            <input type="text" class="reg-input inline-reg-input" id="type-room-price" name="name">
                        </li>
                        <li>
                            <span class="n">数量：</span>
                            <input type="text" class="reg-input inline-reg-input" id="type-room-num" name="name">
                        </li>
                        <li class="add-last-li">
                            <span class="border-btn inline-border-btn" onclick="manage_modify_app()">增加</span>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row input-row choose-row add-plan-row">
                <div class="title">
                    <span>发布计划</span>
                </div>
                <div class="blank_div"></div>
                <div class="row add-plan-info">

                    <div class="row plan-input-row">
                        <ul>
                            <li class="plan-li plan-li-b">
                                <span class="n">房型：</span>
                                <select class="type-select">
                                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单人房</option>
                                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;双人房</option>
                                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总统房</option>
                                </select>
                            </li>
                            <li class="plan-li plan-li-b">
                                <span class="n">价格：</span>
                                <input type="text" class="reg-input reg-plan-input" id="type-plan-price" name="name">
                            </li>
                            <li class="plan-li plan-li-b">
                                <span class="n">数量：</span>
                                <input type="text" class="reg-input reg-plan-input" id="type-plan-num" name="name">
                            </li>
                        </ul>
                    </div>
                    <div class="row plan-input-row">
                        <ul>
                            <li class="plan-li plan-li-a">
                                <span class="n">开始：</span>
                                <input type="date" class="reg-input reg-plan-input" id="type-plan-time-start" name="name">
                            </li>

                            <li class="plan-li plan-li-a">
                                <span class="n">结束：</span>
                                <input type="date" class="reg-input reg-plan-input" id="type-plan-time-end" name="name">
                            </li>
                            <li class="plan-li">
                                <span class="border-btn inline-border-btn-b">增加</span>
                                <span class="border-btn inline-border-btn-b" onclick="refresh()">取消</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row input-row">
                <div class="room-button-container">
                    <a class="room-add-button" target="_blank" onclick="addPlan()">增加计划</a>
                </div>
                <div class="title">
                    <span>优惠计划</span>
                </div>
                <div class="notice room-type-notice">
                    <p>当前没有优惠计划哦!</p>
                    <p>快看看有没有添加房型哦！</p>
                </div>
                <div class="blank_div"></div>
                <div class="row room-row room-plan-row even-row">
                    <ul>
                        <li class="short-li li-header">A</li>
                        <li class="long-li">时间：<span>2017/03/01-2018/09/01</span></li>
                        <li>房型：<span>单人房</span></li>
                        <li>价格：<span>￥400</span></li>
                        <li>数量：<span>30间</span></li>
                        <li class="short-li li-footer">
                            <span class="li-btn" onclick="manage_modify_app()">删除</span>
                        </li>
                    </ul>
                </div>
                <div class="row room-row room-plan-row even-row">
                    <ul>
                        <li class="short-li li-header li-header-out">A</li>
                        <li class="long-li">时间：<span>2017/03/01-2018/09/01</span></li>
                        <li>房型：<span>双人房</span></li>
                        <li>价格：<span>￥300</span></li>
                        <li>数量：<span>30间</span></li>
                        <li class="short-li li-footer">
                            <span class="li-btn-state">失效</span>
                        </li>
                    </ul>
                </div>

                <div class="blank_div"></div>
                <div class="blank_div"></div>
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
