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
<nav  class="navbar navbar-expand-lg navbar-dark fixed-top navbar-shrink" id="mainNav">
    <div class="container menu-container" >
        <a class="navbar-brand js-scroll-trigger" href="/">Guitar Service</a>

        <div class="navbar-menu" id="navbarResponsive">
<%--            <ul class="navbar-nav text-uppercase ml-auto">--%>
<%--                <li class="nav-item" sec:authorize="!isAuthenticated()">--%>
<%--                    <a class="nav-link js-scroll-trigger" th:href="@{/login}" th:text="#{menu.login}"></a>--%>
<%--                </li>--%>
<%--                <li class="nav-item" sec:authorize="!isAuthenticated()">--%>
<%--                    <a class="nav-link js-scroll-trigger" th:href="@{/register}" th:text="#{menu.register}"></a>--%>
<%--                </li>--%>
<%--                <li class="nav-item" sec:authorize="hasAuthority('CLIENT')">--%>
<%--                    <a class="nav-link js-scroll-trigger" th:href="@{/order}" th:text="#{menu.order}"></a>--%>
<%--                </li>--%>
<%--                <li class="nav-item" sec:authorize="hasAuthority('ADMIN')">--%>
<%--                    <a class="nav-link js-scroll-trigger" th:href="@{/all-users(currentPage=1, recordsPerPage=4)}" th:text="#{menu.all.users}"></a>--%>
<%--                </li>--%>
<%--                <li class="nav-item" sec:authorize="hasAuthority('CLIENT')">--%>
<%--                    <a class="nav-link js-scroll-trigger" th:href="@{/user-orders(currentPage=1, recordsPerPage=4)}" th:text="#{menu.user.orders}"></a>--%>
<%--                </li>--%>
<%--                <li class="nav-item" sec:authorize="isAuthenticated()">--%>
<%--                    <a class="nav-link js-scroll-trigger" th:href="@{/logout}" th:text="#{menu.logout}"></a>--%>
<%--                </li>--%>
<%--            </ul>--%>

            <ul class="navbar-nav text-uppercase ml-auto">

                <li class="nav-item nav-lang">
                    <a class="nav-link js-scroll-trigger small-lang-link" href="?lang=en">Eng</a>
                </li>
                <li class="nav-item nav-lang">
                    <a class="nav-link js-scroll-trigger small-lang-link" href="?lang=ru">Ru</a>
                </li>
<%--                <li class="nav-item nav-lang" sec:authorize="isAuthenticated()">--%>
<%--                    <a class="nav-link js-scroll-trigger small-name-link" th:href="@{/profile}" th:text="${#authentication.name}"></a>--%>
<%--                </li>--%>
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