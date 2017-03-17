<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>个人空间</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/common/navbar.css" rel="stylesheet">
    <link href="../css/common/common.css" rel="stylesheet">
    <link href="../css/common/pageGroup.css" rel="stylesheet">
    <link href="../css/personInfo.css" rel="stylesheet">
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
                    <li><a href="#">预定客栈</a></li>
                    <li><a href="#">我的预定</a></li>
                    <li><a href="#">我的消费分析</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">个人空间</a></li>
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
                        <img src="../img/common/user.jpg" class="user-pic">
                    </div>
                    <c:choose>
                        <c:when test="${info.isActive==0}">
                            <div class="row">
                                <div class="row info-line">
                                    <p class="info-name">${info.code} ${info.name}</p>
                                    <small class="info-state">未激活</small>
                                </div>
                                <div class="row">
                                    <a class="info-operate" onclick="addMoney(0)">去激活</a>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${info.state==0}">
                            <div class="row">
                                <div class="row info-line">
                                    <p class="info-name">${info.code} ${info.name}</p>
                                    <small class="info-state"  id="info-welcome">欢迎你</small>
                                </div>
                                <div class="row">
                                    <a class="info-operate">停用账号</a>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${info.state==1}">
                            <div class="row">
                                <div class="row info-line">
                                    <p class="info-name">${info.code} ${info.name}</p>
                                    <small class="info-state" id="info-warning">已暂停</small>
                                </div>
                                <div class="row">
                                    <a class="info-operate" onclick="addMoney(0)">充值恢复</a>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="row">
                        <div class="row info-line" id="score-line">
                            <p>等级：lv${info.level}</p>
                            <p>积分：${info.point}</p>
                            <p>余额：${info.money}</p>
                            <a class="info-operate" onclick="pointChange()">积分兑换</a>
                            <a class="info-operate" onclick="addMoney(1)">账号充值</a>
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
                        <p id="notice-code-name">您的会员卡号是: <span id="notice-code">${info.code}</span></p>
                        <p>该账号将用作您的唯一登录账号，充值1000以上即可激活!  <a class="active-href" onclick="addMoney(0)">去激活>></a></p>
                    </div>
                </c:if>
                <div class="row input-row"> 
                    <div class="title">
                        <span>基础资料</span>
                    </div>
                    <div class="row static-info">
                        <div class="col-lg-4 col-md-4">
                            <div class="item">
                                <span class="n">账户：</span>
                                <span class="content-span">${info.code}</span>
                            </div>
                            <div class="item">
                                <span class="n">等级：</span>
                                <span class="content-span">lv.${info.level}</span>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4">
                            <div class="item">
                                <span class="n">余额：</span>
                                <span class="content-span">${info.money}</span>
                            </div>
                            <div class="item">
                                <span class="n">积分：</span>
                                <span class="content-span">${info.point}</span>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3">
                            <div class="item buttons">
                                <a class="info-operate-inline" onclick="addMoney(1)">账号充值</a>
                            </div>
                            <div class="item buttons">
                                <a class="info-operate-inline" onclick="pointChange()">积分兑换</a>
                            </div>
                        </div>
                    </div>
                    <div class="row dynamic-info">
                        <div class="item">
                            <span class="n">昵称：</span>
                            <input type="text" class="reg-input dynamic-info-input" name="name" value="${info.name}" disabled="disabled">
                        </div>
                        <div class="item">
                            <span class="n">年龄：</span>
                            <input type="text" class="reg-input dynamic-info-input" name="age" value="${info.age}" disabled="disabled">
                        </div>
                        <div class="item">
                            <span class="n">地址：</span>
                            <input type="text" class="reg-input dynamic-info-input" name="address" value="${info.address}" disabled="disabled">
                        </div>
                    </div>
                    <div class="row operate-info">
                        <div class="errorMsg nicknameError">
                            昵称格式要求：1-10个字符
                        </div>
                        <span class="border-btn btn-first" onclick="modifyInfo()">修改资料</span>
                        <span class="border-btn btn-first" onclick="modifyPsw()">修改密码</span>
                        <span class="border-btn btn-second" onclick="modifyInfoSave()">确认修改</span>
                        <span class="border-btn btn-second" onclick="modifyCancel()">取消修改</span>
                    </div>

                </div>

                <div class="row input-row choose-row add-money-row">
                    <div class="title">
                        <span>账号充值</span>
                    </div>
                    <div class="row dynamic-info">
                        <div class="item">
                            <span class="n">卡号：</span>
                            <input type="text" class="reg-input" name="nickname" value="">
                        </div>
                        <div class="item">
                            <span class="n">金额：</span>
                            <input type="text" class="reg-input" name="nickname" value="0">
                        </div>
                        <div class="row operate-info">
                            <div class="errorMsg nicknameError">
                                金额需要大于1000
                            </div>
                            <span class="border-btn" onclick="addMoneySave()">充值</span>
                            <span class="border-btn" onclick="modifyCancel()">取消</span>

                        </div>
                    </div>
                </div>

                <div class="row input-row choose-row point-change-row">
                    <div class="title">
                        <span>积分兑换</span>
                    </div>
                    <div class="row dynamic-info">
                        <div class="item">
                            <span class="n">数额：</span>
                            <input type="text" class="reg-input" name="nickname" value="0">
                        </div>
                        <div class="row operate-info">
                            <div class="errorMsg nicknameError">
                                积分不够
                            </div>
                            <span class="border-btn" onclick="pointChangeSave()">兑换</span>
                            <span class="border-btn" onclick="modifyCancel()">取消</span>

                        </div>
                    </div>
                </div>

                <div class="row input-row choose-row modify-psw-row">
                    <div class="title">
                        <span>修改密码</span>
                    </div>
                    <div class="row dynamic-info">
                        <div class="item">
                            <span class="n long-n">请输入原密码：</span>
                            <input type="text" class="reg-input long" name="nickname">
                        </div>
                        <div class="item">
                            <span class="n long-n">请输入新密码：</span>
                            <input type="text" class="reg-input long" name="nickname">
                        </div>
                        <div class="item">
                            <span class="n long-n">请再次输入新密码：</span>
                            <input type="text" class="reg-input long" name="nickname">
                        </div>
                        <div class="row operate-info">
                            <div class="errorMsg nicknameError">
                                密码错误
                            </div>
                            <span class="border-btn" onclick="modifyPswSave()">修改密码</span>
                            <span class="border-btn" onclick="modifyCancel()">取消修改</span>
                        </div>
                    </div>
                </div>

                <div class="row input-row">
                    <div class="title">
                        <span>财务流水</span>
                    </div>
                    <c:choose>
                        <c:when test="${0==0}">
                            <br>
                            <p style="text-align: center">当前没有财务流水，一看就没激活！</p>
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
                                <% int i=1;
                                    for(;i<10;i++){
                                %>
                                <%--<c:forEach items="${answererInfos}" var="answererInfo" varStatus="vs">--%>
                                    <tr>
                                        <td style="text-align:center;"><%=i%></td>
                                        <td style="text-align:center;">+1000</td>
                                        <td style="text-align:center;">充值</td>
                                        <td style="text-align:center;">2017/03/01/22:07</td>

                                    </tr>
                                    <% }%>
                                <%--</c:forEach>--%>
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
<script src="../js/pageGroup.js"></script>
<script src="../js/personInfo.js"></script>
</body>
</html>
