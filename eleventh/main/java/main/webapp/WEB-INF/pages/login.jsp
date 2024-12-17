<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>Login form</title>
</head>
<body>
<div align="center">
    <form action="/loginSecurity" method="POST">
        Login:<br/><input type="text" name="loginUsername"><br/>
        Password:<br/><input type="password" name="loginPassword"><br/>
        <input type="submit" />

        <p><a href="/register">Register new user</a></p>

        <c:if test="${param.error ne null}">
            <p>Wrong login or password</p>
        </c:if>

        <c:if test="${param.logout ne null}">
            <p>You have been logged out</p>
        </c:if>
    </form>
</div>
</body>

</html>