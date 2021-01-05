<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Login</title>
</head>
<body>

<h3>Login Page</h3>

<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">

    <c:if test="${param.error != null}">
        <i>Sorry! You entered invalid username/password.</i>
    </c:if>

    <p>
        User name: <input type="text" name="username" />
    </p>

    <p>
        Password: <input type="password" name="password" />
    </p>

    <input type="submit" value="Login" />

</form:form>

</body>
</html>

