<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zs
  Date: 2017/3/2
  Time: 下午11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是一个测试页面</title>
    <table>
        <th>
            <tr>姓名</tr>
            <tr>年纪</tr>
        </th>
        <c:forEach var="user" items="${list}" >
            <td>
                <tr>${user.name}</tr>
                <tr>${user.age}</tr>
            </td>
        </c:forEach>
    </table>
</head>
<body>

</body>
</html>
