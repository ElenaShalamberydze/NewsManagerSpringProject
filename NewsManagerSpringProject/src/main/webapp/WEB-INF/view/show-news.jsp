<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ShowNews</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.link.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list"/>
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news"/>
    <fmt:message bundle="${loc}" key="local.button.edit" var="edit_button"/>
    <fmt:message bundle="${loc}" key="local.button.delete" var="delete_button"/>
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
    <fmt:message bundle="${loc}" key="local.text.newsTitle" var="news_title"/>
    <fmt:message bundle="${loc}" key="local.text.newsDate" var="news_date"/>
    <fmt:message bundle="${loc}" key="local.text.brief" var="news_brief"/>
    <fmt:message bundle="${loc}" key="local.text.content" var="news_content"/>
</head>
<body>

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
                    <security:authorize access="hasRole('ADMIN')">
                        <td><a href="/news/createnews">${add_news}</a></td>
                    </security:authorize>
                </tr>
            </table>
        </td>
        <td>
            <table>
                <tr>
                    <td>${news_title}</td>
                    <td colspan="2">${requestScope.news.title}</td>
                </tr>
                <tr>
                    <td>${news_date}</td>
                    <td colspan="2">${requestScope.news.date}</td>
                </tr>
                <tr>
                    <td>${news_brief}</td>
                    <td colspan="2">${requestScope.news.brief}</td>
                </tr>
                <tr>
                    <td>${news_content}</td>
                    <td colspan="2">${requestScope.news.content}</td>
                </tr>
                <tr>
                    <td><br><br></td>
                </tr>
                <tr>
                    <td></td>
                    <security:authorize access="hasRole('ADMIN')">
                        <td align="right">
                            <form action="/news/editnews" method="post">
                                <input type="hidden" name="id" value="${requestScope.news.id}">
                                <input type="submit" value="${edit_button}">
                            </form>
                        </td>
                        <td>
                            <form action="/news/delete" method="post">
                                <input type="hidden" name="deleteId" value="${requestScope.news.id}">
                                <input type="submit" value="${delete_button}">
                            </form>
                        </td>
                    </security:authorize>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
