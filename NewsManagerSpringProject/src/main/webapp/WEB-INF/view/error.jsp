<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Error</title>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.link.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list"/>
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news"/>
    <fmt:message bundle="${loc}" key="local.message.error1" var="error1" />
    <fmt:message bundle="${loc}" key="local.message.error2" var="error2" />
    <fmt:message bundle="${loc}" key="local.message.error3" var="error3" />
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
</head>
<body>

<h1>${news_management}</h1> <br />
<a href="/news/changelocale?lang=en">${en} </a>
<a href="/news/changelocale?lang=ru">${ru}</a>
<hr />

<table>
    <tr>
        <td valign="top">
            <table>
                <tr >
                    <td>
                        <form action="/logout" method="POST">
                            <input type="submit" value="Logout" />
                        </form>
                    </td>
                </tr>
                <tr>
                    <td><a href="/news/main">${news_list}</a></td>
                </tr>
                <tr>
                    <security:authorize access="hasRole('ADMIN')">
                        <td><a href="/news/createnews">${add_news}</a></td>
                    </security:authorize>
                </tr>
            </table>
        </td>
        <td>
            <c:set var="errorId" scope="session" value="${sessionScope.errorId}" />
            <c:choose>
                <c:when test="${errorId == 1}">
                    <c:out value="${error1}"/>
                </c:when>
                <c:when test="${errorId == 2}">
                    <c:out value="${error2}"/>
                </c:when>
                <c:when test="${errorId == 3}">
                    <c:out value="${error3}"/>
                </c:when>
            </c:choose>
        </td>
    </tr>
</table>

</body>
</html>

