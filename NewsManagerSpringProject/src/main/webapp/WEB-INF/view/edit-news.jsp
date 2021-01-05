<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>EditNews</title>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.link.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list"/>
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news"/>
    <fmt:message bundle="${loc}" key="local.button.save" var="save_button"/>
    <fmt:message bundle="${loc}" key="local.button.cancel" var="cancel_button"/>
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
    <fmt:message bundle="${loc}" key="local.text.newsTitle" var="news_title"/>
    <fmt:message bundle="${loc}" key="local.text.newsDate" var="news_date"/>
    <fmt:message bundle="${loc}" key="local.text.brief" var="news_brief"/>
    <fmt:message bundle="${loc}" key="local.text.content" var="news_content"/>
    <fmt:message bundle="${loc}" key="local.text.datePlaceholder" var="date_placeholder"/>
</head>
<body>

<security:authorize access="hasRole('ADMIN')">

<h1>${news_management}</h1>
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
                    <td><a href="/news/createnews">${add_news}</a></td>
                </tr>
            </table>
        </td>
        <td>
            <form:form method="post" modelAttribute="news" action="/news/savenews">
                <form:input path="id" type="hidden" value="${requestScope.news.id}" />
                ${news_title}
                <form:input path="title" type="text" placeholder="${news_title}" value="${requestScope.news.title}" /> <br />
                ${news_brief}
                <form:input path="brief" type="text" placeholder="${news_brief}" value="${requestScope.news.brief}" /> <br />
                ${news_content}
                <form:input path="content" type="text" placeholder="${news_content}" value="${requestScope.news.content}" /> <br />
                <input type="submit" value="${save_button}">
                <input type="button" value="${cancel_button}" onclick='location.href="/news/main"'>
            </form:form>
        </td>
    </tr>
</table>

</security:authorize>

</body>
</html>
