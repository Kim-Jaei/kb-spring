<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>login</title>
</head>
<body>
<h1>login</h1>
<%-- config의 url과 같아서 action 생략 가능 --%>
<form name="f" action="/security/login" method="POST">
    <%--CSRF 토큰 없거나 내 토큰이 아닐 경우 403 에러--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td>User: </td>
            <td><input type="text" name="username" value=""></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input name="submit" type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>