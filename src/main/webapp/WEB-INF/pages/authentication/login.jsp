<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<div class="container-fluid">
    <div class="mt-4">
        <c:if test="${not empty errorMessage}">
            <p style="color: red">${errorMessage}</p>
        </c:if>
    </div>
    <div class="mt-4">
        <form action="${pageContext.servletContext.contextPath}/login" method="post">
            <h2>Login</h2><br>
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <div class="mt-3">
            <a href="${pageContext.servletContext.contextPath}/register" class="btn btn-link">Registration</a>
        </div>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
