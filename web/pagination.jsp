<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>

<head>

    <title>Pagination</title>

</head>

<body id="page-top">


<nav aria-label="Navigation">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="${page}?command=${command}&currentPage=${currentPage-1}&recordsPerPage=${recordsPerPage}"><fmt:message key="pagination.prev"/></a>
            </li>
        </c:if>

        <c:if test="${numberOfPages != 1}">
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="${page}?command=${command}&currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>

        <c:if test="${currentPage < numberOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="${page}?command=${command}&currentPage=${currentPage+1}&recordsPerPage=${recordsPerPage}"><fmt:message key="pagination.next"/></a>
            </li>
        </c:if>

    </ul>
</nav>

</body>

</html>