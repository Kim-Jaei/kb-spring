<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
    <h1>/security/admin page</h1>

    <form action="/security/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf_token}"/>
        <input type="submit" value="로그아웃"/>
    </form>
</body>
</html>