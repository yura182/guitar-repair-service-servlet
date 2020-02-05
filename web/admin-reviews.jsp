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

    <title><fmt:message key="reviews.title"/></title>

    <link href="${pageContext.request.contextPath}/css/agency.css"  rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"  rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css"  rel="stylesheet">
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
                <h2 class="section-heading text-uppercase"><fmt:message key="review.title.body"/></h2>
                <h3 class="section-subheading text-muted"><fmt:message key="review.body.subtitle"/></h3>

                <c:if test="${not empty sessionScope.successMessage}">
                    <p class="text-success" ><fmt:message key="${sessionScope.successMessage}"/></p>
                </c:if>
                <c:remove var="successMessage" scope="session" />

                <c:forEach var="review" items="${entities}">
                    <div class="blockquote-wrapper">
                        <div class="blockquote">
                            <div class="review-admin-info-wrapper">
                                <div class="info-left">
                                    <a class="details-link" href="${pageContext.request.contextPath}/admin?command=adminOrderDetails&orderId=${review.order.id}">Order id ${review.order.id}</a>
                                </div>

                                <div class="info-right">
                                    <form method="post" action="delete-review">
                                        <input type="hidden" name="reviewId" value="${review.id}" />
                                        <input type="hidden"  name="command" value="deleteReview" />

                                        <button type="submit" name="submit" value="value" class="link-button"><fmt:message key="reviews.delete"/></button>
                                    </form>
                                </div>

                            </div>
                            <h1>
                                    ${review.text}
                            </h1>
                            <h4>&mdash; ${review.client.name} ${review.client.surname}<br><em><tags:localDateTime date="${review.date}"/></em><br>
                                <em>${review.client.email}</em>
                            </h4>
                        </div>
                    </div>
                </c:forEach>

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