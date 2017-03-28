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
    <link href="../css/analyse.css" rel="stylesheet">
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
                    <li class="active"><a href="/HotelWorld/hotel_analyse">客栈统计</a></li>
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
                <span>收益情况</span>
            </div>
            <div class="row info-row">
                <ul>
                    <li>
                        <div class="info-div info-hotel-num">被 <span>${ha.vipNum}</span> 个会员光顾了</div>
                    </li>
                    <li>
                        <div class="info-div info-order-num">产生了 <span>${ha.orderNum}</span> 次订单</div>
                    </li>
                    <li>
                        <div class="info-div info-money-num">收益了 <span>￥${ha.moneyNum}</span> 元</div>
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
        </div>
        <div class="row input-row">
            <div class="title">
                <span>财务流水</span>
            </div>
            <c:choose>
                <c:when test="${cashs.size()==0}">
                    <br>
                    <p style="text-align: center">当前没有财务流水，看看有没有发布计划！</p>
                    <br>
                </c:when>
                <c:otherwise>
                    <table class="table table-striped table-hover" id="cash-table">
                        <tr>
                            <th style="text-align:center;">序号</th>
                            <th style="text-align:center;">金额</th>
                            <th style="text-align:center;">类别</th>
                            <th style="text-align:center;">时间</th>

                        </tr>
                        <% int i=1;%>
                        <c:forEach items="${cashs}" var="cash" varStatus="vs">
                            <tr>
                                <td style="text-align:center;"><%=i%></td>
                                <td style="text-align:center;">${cash.content}</td>
                                <c:choose>
                                    <c:when test="${cash.type==1}">
                                        <td style="text-align:center;">会员卡</td>
                                    </c:when>
                                    <c:when test="${cash.type==0}">
                                        <td style="text-align:center;">现金支付</td>
                                    </c:when>
                                </c:choose>
                                <td style="text-align:center;">${cash.time}</td>
                            </tr>
                            <% i++;%>
                        </c:forEach>
                    </table>

                    <input type="hidden" id="start_page">
                    <input type="hidden" id="current_page" />
                    <input type="hidden" id="show_per_page" />
                    <input type="hidden" id="end_page">
                    <!-------------------------------------------分页----------------------------------------------------------------->
                    <div id="pageGro" class="cb">
                        <div class="pagestart">首页</div>
                        <div class="pageUp">上一页</div>
                        <div class="pageList">
                            <ul>
                                <li>1</li>
                                <li>2</li>
                                <li>3</li>
                                <li>4</li>
                                <li>5</li>
                            </ul>
                        </div>
                        <div class="pageDown">下一页</div>
                        <div class="pageend">尾页</div>
                    </div>
                    <!-------------------------------------------END 分页----------------------------------------------------------------->

                </c:otherwise>
            </c:choose>

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
    <script src="../js/pageGroup.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/hotelAnalyse.js"></script>
</body>
</html>

