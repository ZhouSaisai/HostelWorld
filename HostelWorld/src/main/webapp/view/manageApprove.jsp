<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>查看申请</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/pageGroup.css" rel="stylesheet">
    <link href="../css/personInfo.css" rel="stylesheet">
    <link href="../css/common/hotelList.css" rel="stylesheet">
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
                    <li><a href="#">查看申请</a></li>
                    <li><a href="#">会员结算</a></li>
                    <li><a href="#">网站统计</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a>管理员，你好</a></li>
                    <li><a href="/HotelWorld/ask_loginOut">注销</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
    <div class="container-fluid main-content">
        <div class="row content-row">
            <div class="row input-row">
                <div class="title">
                    <span>开店申请</span>
                </div>
            <c:choose>
                <c:when test="${hotels.size()==0}">
                    <br>
                    <p style="text-align: center">当前没有申请的客栈！</p>
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
                                            <div class="application-content-title application-title-level">
                                                星级：
                                                <span class="application-content application-content-level">${hotel.level}星级</span>
                                            </div>
                                            <div class="application-content-title application-title-tel">
                                                电话：
                                                <span class="application-content application-content-tel">${hotel.tel}</span>
                                            </div>
                                        </div>
                                        <div class="application-content-right">
                                            <div class="application-content-title application-title-id">
                                                编号：
                                                <span class="application-content application-content-id">${hotel.code}</span>
                                            </div>
                                            <div class="application-content-title application-title-level">
                                                时间：
                                                <span class="application-content application-content-level">${hotel.time}</span>
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
                                        <span class="border-btn application-operate-btn" onclick="manage_open_app(${hotel.hId},1)">通过</span>
                                        <span class="border-btn application-operate-btn" onclick="manage_open_app(${hotel.hId},2)">拒绝</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <%i++;%>
                    </c:forEach>
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
            <div class="row input-row"> 
                <div class="title">
                    <span>修改资料申请</span>
                </div>
                <div class="row static-info">
                    <div class="col-lg-4 col-md-4">
                        <div class="item">
                            <span class="n">账户：</span>
                            <span class="content-span">${info.code}</span>
                        </div>
                        <div class="item">
                            <span class="n">等级：</span>
                            <span class="content-span">${info.level}</span>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                        <div class="item">
                            <span class="n">余额：</span>
                            <span class="content-span">${info.money}</span>
                        </div>
                        <div class="item">
                            <span class="n">积分：</span>
                            <span class="content-span" id="point-num-text">${info.point}</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3">
                        <div class="item buttons">
                            <c:choose>
                            <c:when test="${info.isActive==0}">
                                <a class="info-operate-inline" onclick="addMoney(0)">去激活</a>
                            </c:when>
                                <c:when test="${info.state==0}">
                                <a class="info-operate-inline" onclick="addMoney(2)">账号充值</a>
                        </div>
                        <div class="item buttons">
                            <a class="info-operate-inline" onclick="pointChange()">积分兑换</a>
                            </c:when><c:when test="${info.state==1}">
                            <a class="info-operate-inline" onclick="addMoney(1)">充值恢复</a>
                            </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="row dynamic-info">
                    <div class="item">
                        <span class="n">昵称：</span>
                        <input type="text" class="reg-input dynamic-info-input" id="dynamic-info-name" name="name" value="${info.name}" disabled="disabled">
                    </div>
                    <div class="item">
                        <span class="n">年龄：</span>
                        <input type="text" class="reg-input dynamic-info-input" id="dynamic-info-age" name="age" value="${info.age}" disabled="disabled">
                    </div>
                    <div class="item">
                        <span class="n">地址：</span>
                        <input type="text" class="reg-input dynamic-info-input" id="dynamic-info-address" name="address" value="${info.address}" disabled="disabled">
                    </div>
                </div>
                <%--已激活且正常状态才能操作--%>
                <c:choose>
                    <c:when test="${info.isActive==0}">
                    </c:when>
                    <c:when test="${info.state==0}">
                        <div class="row operate-info">
                            <div class="errorMsg nicknameError" id="error-dynamic-info">
                            </div>
                            <span class="border-btn btn-first" onclick="modifyInfo()">修改资料</span>
                            <span class="border-btn btn-first" onclick="modifyPsw()">修改密码</span>
                            <span class="border-btn btn-second" onclick="modifyInfoSave()">确认修改</span>
                            <span class="border-btn btn-second" onclick="modifyCancel()">取消修改</span>
                        </div>
                    </c:when>
                </c:choose>
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
</body>
</html>
