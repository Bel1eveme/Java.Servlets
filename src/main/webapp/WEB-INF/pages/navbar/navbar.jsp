<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="gamelibrary.configuration.SessionAttributeNames" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String attributeName = SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME;
    boolean isAdmin = (boolean) request.getSession().getAttribute(attributeName);
    String language = (String)request.getSession().getAttribute(SessionAttributeNames.LANGUAGE_ATTRIBUTE_NAME);
    language = language==null?"en":language;
    pageContext.setAttribute("language",language);
    pageContext.setAttribute("isAdmin", isAdmin);
%>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/profile"><fmt:message key="profile" /></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/games/list"><fmt:message key="games"/></a>
            </li>
            <c:if test="${isAdmin}">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/game/create"><fmt:message key="create"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/user/list"><fmt:message key="userList"/></a>
                </li>
            </c:if>
            <li class="nav-item">
                <form action="${contextPath}/language" method="post">
                    <div class="form-group">
                        <select class="form-control" id="languageSelect" name="language">
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </li>
            <li class="nav-item">
                <fmt:message var="myMessage" key="logout" />
                <form action="${pageContext.servletContext.contextPath}/logout" method="post">
                    <input type="submit" class="btn btn-link nav-link" value="${myMessage}">
                </form>
            </li>
        </ul>
    </div>
</nav>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
