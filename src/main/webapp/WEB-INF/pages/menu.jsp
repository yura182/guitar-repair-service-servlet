<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%@ taglib prefix="loc" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top navbar-shrink" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="/">Guitar Service</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">

                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/login"><fmt:message key="menu.login"/></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/register"><fmt:message key="menu.register"/></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/reviews?currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role.name() eq 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin/users?currentPage=1&recordsPerPage=5"><fmt:message key="menu.all.users"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin/orders?currentPage=1&recordsPerPage=5"><fmt:message key="menu.admin.orders"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin/reviews?currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
                    </li>

                </c:if>

                <c:if test="${sessionScope.user.role.name() eq 'CLIENT'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/client/add-order"><fmt:message key="menu.order"/></a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/client/all-orders?currentPage=1&recordsPerPage=5"><fmt:message key="menu.user.orders"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/reviews?currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.user.role.name() eq 'MASTER'}">
                    <li class="nav-item" >
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/master?command=masterAllAvailableOrders&currentPage=1&recordsPerPage=5"><fmt:message key="menu.master.available.orders"/></a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/master?command=masterProcessingOrders&currentPage=1&recordsPerPage=5"><fmt:message key="menu.master.processing.orders"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/reviews?command=allReviews&currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
                    </li>
                </c:if>

            </ul>

            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item nav-lang">
                    <a class="nav-link js-scroll-trigger small-lang-link" href="<loc:replaceParam name="lang" value="en"/>">Eng</a>
                </li>
                <li class="nav-item nav-lang">
                    <a class="nav-link js-scroll-trigger small-lang-link" href="<loc:replaceParam name="lang" value="ru"/>">Ru</a>
                </li>

                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item nav-lang">
                        <a class="nav-link js-scroll-trigger small-name-link" href="${pageContext.request.contextPath}/profile">${sessionScope.user.email}</a>
                    </li>

                    <li class="nav-item nav-lang" >
                        <a class="nav-link js-scroll-trigger small-name-link" href="${pageContext.request.contextPath}/logout"><fmt:message key="menu.logout"/></a>
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