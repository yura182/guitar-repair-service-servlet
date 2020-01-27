<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top navbar-shrink" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="index.jsp">Guitar Service</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">

                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="login.jsp"><fmt:message key="menu.login"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="register.jsp"><fmt:message key="menu.register"/></a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/admin-all-users(currentPage=1, recordsPerPage=4)"><fmt:message key="menu.all.users"/></a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('CLIENT')}">
                    <li class="nav-item" >
                        <a class="nav-link js-scroll-trigger" href="/user-all-orders(currentPage=1, recordsPerPage=4)"><fmt:message key="menu.user.orders"/></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item" >
                        <a class="nav-link js-scroll-trigger" href="logout?command=logout"><fmt:message key="menu.logout"/></a>
                    </li>
                </c:if>
            </ul>

            <ul class="navbar-nav text-uppercase ml-auto">

                <li class="nav-item nav-lang">
                    <a class="nav-link js-scroll-trigger small-lang-link" href="?lang=en">Eng</a>
                </li>
                <li class="nav-item nav-lang">
                    <a class="nav-link js-scroll-trigger small-lang-link" href="?lang=ru">Ru</a>
                </li>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item nav-lang">
                        <a class="nav-link js-scroll-trigger small-name-link" href="profile.jsp">${sessionScope.user.email}</a>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>
</nav>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${pageContext.request.contextPath}vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Contact form JavaScript -->
<script src="${pageContext.request.contextPath}/js/jqBootstrapValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/contact_me.js"></script>
</body>
</html>