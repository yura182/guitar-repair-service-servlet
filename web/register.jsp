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

    <title><fmt:message key="register.title"/></title>

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

<!-- Register -->
<section class="page-section" id="services">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase" ><fmt:message key="register.title.body"/></h2>
                <h3 class="section-subheading text-muted" ><fmt:message key="register.body.subtitle"/></h3>
                <c:if test="${alreadyRegisteredError}">
                    <p class="text-danger" ><fmt:message key="register.error.already.registered"/></p>
                </c:if>

                <c:if test="${registrationError}">
                    <p class="text-danger" ><fmt:message key="register.error"/></p>
                </c:if>
            </div>
        </div>
        <div class="row text-center">
            <div class="limiter">
                <div class="container-login100">
                    <div class="wrap-login100 p-t-50 p-b-90">
                        <form class="login100-form validate-form flex-sb flex-w"  action="register" method="post">
                            <input type="hidden" name="command" value="register">
                            <div class="wrap-input100 validate-input m-b-16" data-validate = "Name is required">
                                <input class="input100" type="text" name="name" id="name"
                                       pattern="[a-zA-Zа-яА-Яієї']{2,25}"
                                       placeholder=<fmt:message key="registration.field.name"/> required title=<fmt:message key="registration.name.error"/>>
                                <span class="focus-input100"></span>
                            </div>

                            <div class="wrap-input100 validate-input m-b-16" data-validate = "Surname is required">
                                <input class="input100" type="text" id="surname"  name="surname"
                                       pattern="[a-zA-Zа-яА-Яієї']{2,25}"
                                       placeholder=<fmt:message key="registration.field.surname"/> required title=<fmt:message key="registration.surname.error"/>>
                                <span class="focus-input100"></span>
                            </div>

                            <div class="wrap-input100 validate-input m-b-16" data-validate = "Email is required">
                                <input class="input100" type="email" id="email"  name="email"
                                       pattern="^\w+\.?\w{1,}@\w+\.[a-z]{2,4}$"
                                       placeholder=<fmt:message key="registration.field.email"/> required title=<fmt:message key="registration.email.error"/>>
                                <span class="focus-input100"></span>
                            </div>

                            <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                                <input class="input100" type="password" id="password"  name="password"
                                       pattern="[A-Za-z0-9]{8,}"
                                        placeholder=<fmt:message key="registration.field.password"/> required title=<fmt:message key="registration.password.error"/>>
                                        <span class="focus-input100"></span>
                            </div>

                            <div class="wrap-input100 validate-input m-b-16" data-validate = "Phone number is required">
                                <input class="input100" type="text" id="phone"  name="phone"
                                       pattern="^[0-9]{10}$"
                                        placeholder=<fmt:message key="registration.field.phone"/> required title=<fmt:message key="registration.field.phone"/>>
                                <span class="focus-input100"></span>
                            </div>

                            <div class="container-login100-form-btn m-t-17">
                                <button class="login100-form-btn" type="submit">
                                    <fmt:message key="register.button"/>
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
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
<script src="${pageContext.request.contextPath}vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${pageContext.request.contextPath}vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Contact form JavaScript -->
<script src="${pageContext.request.contextPath}/js/jqBootstrapValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/contact_me.js"></script>


</body>

</html>
