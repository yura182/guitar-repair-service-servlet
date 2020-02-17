<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%@ taglib prefix="loc" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Yriy Petrashenko">

    <title><fmt:message key="main.title"/></title>


    <link href="${pageContext.request.contextPath}/css/agency.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">

</head>

<body id="page-top">

<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Guitar Service</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="#services"><fmt:message key="menu.services"/></a>
                </li>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/login"><fmt:message key="menu.login"/></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/register"><fmt:message key="menu.register"/></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/reviews?command=allReviews&currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role.name() eq 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin?command=allUsers&currentPage=1&recordsPerPage=5"><fmt:message key="menu.all.users"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin?command=adminAllOrders&currentPage=1&recordsPerPage=5"><fmt:message key="menu.admin.orders"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin?command=adminAllReviews&currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
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
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/reviews?command=allReviews&currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
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
                        <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/reviews?currentPage=1&recordsPerPage=3"><fmt:message key="menu.reviews"/></a>
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

<!-- Header -->
<header class="masthead">
    <div class="container">
        <div class="intro-text">
            <div class="intro-lead-in"><fmt:message key="main.body.welcome"/></div>
            <div class="intro-heading text-uppercase"><fmt:message key="main.body.since"/></div>
            <a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="${pageContext.request.contextPath}/client-add-order.jsp"><fmt:message key="main.body.make"/></a>
        </div>
    </div>
</header>

<!-- Services -->
<section class="page-section" id="services">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase"><fmt:message key="main.body.services"/></h2>
                <h3 class="section-subheading text-muted"><fmt:message key="main.body.service.description"/></h3>
            </div>
        </div>
        <div class="row text-center">
            <div class="col-md-4">
          <div>
            <img src="${pageContext.request.contextPath}/img/repairs.jpg" class="rounded-circle" alt="repairs" width="200" height="200">
          </div>
                <h4 class="service-heading"><fmt:message key="main.services.repairs"/></h4>
                <p class="text-muted"><fmt:message key="main.services.repairs.description"/></p>
            </div>
            <div class="col-md-4">
                <div>
                    <img src="${pageContext.request.contextPath}/img/finishing.jpg" class="rounded-circle" alt="repairs" width="200" height="200">
                </div>
                <h4 class="service-heading"><fmt:message key="main.services.finishing"/></h4>
                <p class="text-muted"><fmt:message key="main.services.finishing.description"/></p>
            </div>
            <div class="col-md-4">
                <div>
                    <img src="${pageContext.request.contextPath}/img/setup.jpg" class="rounded-circle" alt="repairs" width="200" height="200">
                </div>
                <h4 class="service-heading"><fmt:message key="main.services.setup"/></h4>
                <p class="text-muted"><fmt:message key="main.services.setup.description"/></p>
            </div>
        </div>
    </div>
</section>



<!-- Footer -->
<footer class="bg-light footer">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-4">

            </div>
            <div class="col-md-4">
                <span class="copyright">Copyright &copy; Guitar Service 2020</span>
            </div>
            <div class="col-md-4">
                <ul class="list-inline quicklinks">

                </ul>
            </div>
        </div>
    </div>
</footer>



<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Contact form JavaScript -->
<script src="${pageContext.request.contextPath}/js/jqBootstrapValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/contact_me.js"></script>

<script src="${pageContext.request.contextPath}/js/agency.js"></script>


</body>

</html>
