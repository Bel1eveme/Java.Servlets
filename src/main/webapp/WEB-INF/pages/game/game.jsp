<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movie</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../navbar/navbar.jsp" />

    <div class="mt-3 text-center">
        <h1 class="mt-3">${game.getTitle()}</h1>
        <img src="${game.getImageUrl()}" alt="Image not found" class="img-fluid rounded mt-3">
    </div>

    <div class="mt-3">
        <p class="mt-3">Description : ${game.getDescription()}</p>
        <p class="mt-2">Release Date: ${game.getReleaseDate()}</p>
    </div>

    <form action="${pageContext.servletContext.contextPath}/review" method="post" class="mt-3">
        <div class="form-row">
            <div class="col-md-8">
                <div class="form-group">
                    <label for="message">Review:</label>
                    <input type="text" name="message" id="message" class="form-control">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <input type="hidden" name="gameId" value="${game.getId()}">
                </div>
            </div>
            <div class="col-md-4 mt-2">
                <div class="form-group">
                    <input type="submit" value="Create Review" class="btn btn-primary btn-block">
                </div>
            </div>
        </div>
    </form>

    <c:forEach var="review" items="${reviews}">
        <div class="mt-4">
            <h3>${review.getPublisherUsername()}</h3>
            <c:if test="${review.isUserThePublisher()}">
                <form action="${pageContext.servletContext.contextPath}/review/edit/${review.getId()}"
                      method="post" class="form-inline mt-2">
                    <div class="form-group">
                        <input type="text" name="message" value="${review.getMessage()}" class="form-control">
                    </div>
                    <input type="submit" value="Edit" class="btn btn-primary ml-2">
                </form>
                <form action="${pageContext.servletContext.contextPath}/review/delete/${review.getId()}"
                      method="post" class="form-inline mt-2">
                    <input type="submit" value="Delete" class="btn btn-danger ml-2">
                </form>
            </c:if>
            <c:if test="${!review.isUserThePublisher()}">
                <p class="mt-2">${review.getMessage()}</p>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>
</html>

