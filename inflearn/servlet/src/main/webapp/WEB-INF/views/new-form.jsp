<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
<%--        상대 경로 사용 --%>
        <form action="save" method="post">
            username: <input type="text" name="username"/>
            age: <input type="text" name="age"/>
            <button type="submit">전송</button>
        </form>
    </body>
</html>