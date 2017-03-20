<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>客栈空间</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/pageGroup.css" rel="stylesheet">
    <link href="../css/personInfo.css" rel="stylesheet">
    <link href="../css/common/hotelList.css" rel="stylesheet">
    <link href="../css/hotelInfo.css" rel="stylesheet">
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
                    <li class="active"><a onclick="refresh()">客栈空间</a></li>
                    <li><a href="/HotelWorld/ask_loginOut">注销</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
    <div class="container-fluid main-content">
        <div class="row content-row">
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-5 info-container">
                <div class="row basic-info-row">
                    <div class="row pic-container">
                        <img src="../img/common/hotel.jpg" class="user-pic">
                    </div>
                    <c:choose>
                        <c:when test="${info.state==0}">
                            <div class="row">
                                <div class="row info-line">
                                    <p class="info-name">${info.code}</p>
                                    <p class="info-name">${info.name}</p>
                                    <small class="info-state">审批中</small>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${info.state==1}">
                            <div class="row">
                                <div class="row info-line">
                                    <p class="info-name">${info.code}</p>
                                    <p class="info-name">${info.name}</p>
                                    <small class="info-state"  id="info-welcome">营业中</small>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="row">
                        <div class="row info-line" id="score-line">
                            <p>星级：${info.level}星级</p>
                            <p>收入：${info.money}</p>
                            <p>未结算：${info.outMoney}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-8 col-xs-7">
                <c:if test="${sign=='new'}">
                    <div class="row notice-row">
                        <div id="success-notice">
                            <img src="../img/common/success.svg">
                            <p id="success-notice-p">注册成功！</p>
                        </div>
                        <p id="notice-code-name">您的客栈账号是: <span id="notice-code">${info.code}</span></p>
                        <p>该账号将用作您的唯一登录账号，审批通过后即可开始发布计划!</p>
                    </div>
                </c:if>
                <div class="row input-row"> 
                    <div class="title">
                        <span>基础资料</span>
                    </div>
                    <div class="row static-info">
                        <div class="col-lg-5 col-md-5">
                            <div class="item">
                                <span class="n">账户：</span>
                                <span class="content-span">${info.code}</span>
                            </div>
                            <div class="item">
                                <span class="n">星级：</span>
                                <span class="content-span">${info.level}星级</span>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="item">
                                <span class="n">收入：</span>
                                <span class="content-span">${info.money}</span>
                            </div>
                            <div class="item">
                                <span class="n">未结算：</span>
                                <span class="content-span" id="point-num-text">${info.outMoney}</span>
                            </div>
                        </div>
                    </div>
                    <div class="row dynamic-info">
                        <div class="item">
                            <span class="n">名称：</span>
                            <input type="text" class="reg-input dynamic-info-input" id="dynamic-info-name" name="name" value="${info.name}" disabled="disabled">
                        </div>
                        <div class="item">
                            <span class="n">电话：</span>
                            <input type="text" class="reg-input dynamic-info-input" id="dynamic-info-tel" name="age" value="${info.tel}" disabled="disabled">
                        </div>
                        <div class="item">
                            <span class="n">地址：</span>
                            <input type="text" class="reg-input dynamic-info-input" id="dynamic-info-address" name="address" value="${info.address}" disabled="disabled">
                        </div>
                    </div>
                    <%--审批通过才能操作--%>
                    <c:choose>
                        <c:when test="${info.state==1}">
                            <div class="row operate-info">
                                <div class="errorMsg nicknameError" id="error-dynamic-info">
                                </div>
                                <span class="border-btn btn-first" onclick="modifyInfo()">修改资料</span>
                                <span class="border-btn btn-first" onclick="modifyPsw()">修改密码</span>
                                <span class="border-btn btn-second" onclick="modifyInfoSave()">申请修改</span>
                                <span class="border-btn btn-second" onclick="modifyCancel()">取消修改</span>
                            </div>
                        </c:when>
                    </c:choose>

                </div>

                <div class="row input-row choose-row modify-psw-row">
                    <div class="title">
                        <span>修改密码</span>
                    </div>
                    <div class="row dynamic-info">
                        <div class="item">
                            <span class="n long-n">请输入原密码：</span>
                            <input type="password" class="reg-input long" id="modify-psw-password" name="nickname">
                        </div>
                        <div class="item">
                            <span class="n long-n">请输入新密码：</span>
                            <input type="password" class="reg-input long" id="modify-psw-password-new" name="nickname">
                        </div>
                        <div class="item">
                            <span class="n long-n">请再次输入新密码：</span>
                            <input type="password" class="reg-input long" id="modify-psw-password-new-again" name="nickname">
                        </div>
                        <div class="row operate-info">
                            <div class="errorMsg nicknameError" id="error-modify-psw">
                            </div>
                            <span class="border-btn" onclick="modifyPswSave()">修改密码</span>
                            <span class="border-btn" onclick="modifyCancel()">取消修改</span>
                        </div>
                    </div>
                </div>

                <div class="row input-row">
                    <div class="title">
                        <span>修改申请</span>
                    </div>
                    <c:choose>
                        <c:when test="${apps.size()==0}">
                            <br>
                            <p style="text-align: center">当前没有申请修改的客栈！</p>
                            <br>
                        </c:when>
                        <c:otherwise>
                            <% int i=1;%>
                            <c:forEach items="${apps}" var="app" varStatus="vs">
                                <div class="row application-row modify-hotel-row">
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
                                                        <c:choose>
                                                            <c:when test="${app.hotel.name == app.name}">
                                                                <span class="application-content application-content-name">${app.hotel.name}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="application-content application-content-name">${app.name} <sapn class="new">(new)</sapn></span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                    <div class="application-content-title application-title-level">
                                                        星级：
                                                        <span class="application-content application-content-level">${app.hotel.level}星级</span>
                                                    </div>
                                                    <div class="application-content-title application-title-tel">
                                                        电话：
                                                        <c:choose>
                                                            <c:when test="${app.hotel.tel == app.tel}">
                                                                <span class="application-content application-content-name">${app.hotel.tel}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="application-content application-content-name">${app.tel} <sapn class="new">(new)</sapn></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                                <div class="application-content-right">
                                                    <div class="application-content-title application-title-id">
                                                        编号：
                                                        <span class="application-content application-content-id">${app.hotel.code}</span>
                                                    </div>
                                                    <div class="application-content-title application-title-level">
                                                        时间：
                                                        <span class="application-content application-content-level">${app.time}</span>
                                                    </div>
                                                    <div class="application-content-title application-title-address">
                                                        地址：
                                                        <c:choose>
                                                            <c:when test="${app.hotel.address == app.address}">
                                                                <span class="application-content application-content-name">${app.hotel.address}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="application-content application-content-name">${app.address} <sapn class="new">(new)</sapn></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="application-operate-container">
                                                <c:choose>
                                                    <c:when test="${app.state==0}">
                                                        <span class="border-btn application-operate-btn">申请中</span>
                                                    </c:when>
                                                    <c:when test="${app.state==1}">
                                                        <span class="border-btn application-operate-btn">已通过</span>
                                                    </c:when>
                                                    <c:when test="${app.state==2}">
                                                        <span class="border-btn application-operate-btn">被拒绝</span>
                                                    </c:when>
                                                    <c:when test="${app.state==3}">
                                                        <span class="border-btn application-operate-btn">已覆盖</span>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <%i++;%>
                            </c:forEach>
                            <input type="hidden" id="qa_start_page">
                            <input type="hidden" id="qa_current_page" />
                            <input type="hidden" id="qa_show_per_page" />
                            <input type="hidden" id="qa_end_page">
                            <!-------------------------------------------分页----------------------------------------------------------------->
                            <div id="pageQA" class="cb">
                                <div class="QAPagestart">首页</div>
                                <div class="QAPageUp">上一页</div>
                                <div class="QAPageList">
                                    <ul>
                                        <li>1</li>
                                        <li>2</li>
                                        <li>3</li>
                                    </ul>
                                </div>
                                <div class="QAPageDown">下一页</div>
                                <div class="QAPageend">尾页</div>
                            </div>
                            <!-------------------------------------------END 分页----------------------------------------------------------------->
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <input type="hidden" id="hId" value="${info.hId}">
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
<script src="../js/pageGroup.js"></script>
<script src="../js/hotelInfo.js"></script>
</body>
</html>
