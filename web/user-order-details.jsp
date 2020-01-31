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

    <title><fmt:message key="order.details.title"/></title>

    <link href="${pageContext.request.contextPath}/css/agency.css"  rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"  rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css"  rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

</head>

<body id="page-top">

<!-- Navigation -->
<c:import url="menu.jsp"/>


<section class="page-section" id="services">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase"><fmt:message key="order.details.title.body"/></h2>
                <h3 class="section-subheading text-muted"><fmt:message key="order.details.body.subtitle"/></h3>

                <table class="table table-striped profile">
                    <tbody>
                    <tr>
                        <td ><span class="profile-header"><fmt:message key="user.orders.table.id"/></span></td>
                        <td>${order.id}</td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.date"/></span></td>
                        <td><tags:localDateTime date="${order.dateTime}" /></td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.instrument.brand"/></span></td>
                        <td>${order.instrument.brand}</td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.instrument.model"/></span></td>
                        <td>${order.instrument.model}</td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.instrument.year"/></span></td>
                        <td>${order.instrument.manufactureYear}</td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.service"/></span></td>
                        <td>${order.service}</td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.price"/></span></td>
                        <c:choose>
                            <c:when test="${order.price eq 0.0}">
                                <td> </td>
                            </c:when>
                            <c:otherwise>
                                <td>${order.price}</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.status"/></span></td>
                        <td><fmt:message key="${order.status.localeDescription}"/></td>
                    </tr>
                    <tr>
                        <td><span class="profile-header"><fmt:message key="user.orders.table.rejection.reason"/></span></td>
                        <td>${order.rejectionReason}</td>
                    </tr>
                    </tbody>
                </table>


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


</body>

</html>