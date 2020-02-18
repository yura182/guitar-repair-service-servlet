<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Yriy Petrashenko">

    <title><fmt:message key="all.orders.title"/></title>

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

<!-- Navigation -->
<c:import url="menu.jsp"/>


<section class="page-section" id="services">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase"><fmt:message key="all.orders.title.body"/></h2>
                <h3 class="section-subheading text-muted"><fmt:message key="all.orders.body.subtitle"/></h3>

                <table class="table table-striped">
                    <thead>
                    <th><span class="profile-header"><fmt:message key="user.orders.table.id"/></span></th>
                    <th><span class="profile-header"><fmt:message key="user.orders.table.date"/></span></th>
                    <th><span class="profile-header"><fmt:message key="all.orders.table.client.name"/></span></th>
                    <th><span class="profile-header"><fmt:message key="all.orders.table.master.name"/></span></th>
                    <th><span class="profile-header"><fmt:message key="user.orders.table.instrument.brand"/></span></th>
                    <th><span class="profile-header"><fmt:message key="user.orders.table.service"/></span></th>
                    <th><span class="profile-header"><fmt:message key="user.orders.table.status"/></span></th>
                    <th><span class="profile-header" ></span></th>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${entities}">
                        <tr>
                            <td>${order.id}</td>
                            <td><tags:localDateTime date="${order.dateTime}" /></td>
                            <td>${order.client.name} ${order.client.surname}</td>
                            <td>${order.master.name} ${order.master.surname}</td>
                            <td>${order.instrument.brand}</td>
                            <td>${order.service}</td>
                            <td><fmt:message key="${order.status.localeDescription}"/></td>
                            <td><a class="details-link" href="${pageContext.request.contextPath}/admin/order-details?orderId=${order.id}"><fmt:message key="user.orders.details"/></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:import url="pagination.jsp"/>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="bg-light footer">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-4">
                <span class="copyright">Copyright &copy; Guitar Service 2020</span>
            </div>
            <div class="col-md-4">
                <img src="${pageContext.request.contextPath}/img/brands.jpg">
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


</body>

</html>