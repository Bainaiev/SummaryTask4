<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>
    <title>Profile User page</title>
</head>
<body>
<%@include file="/WEB-INF/fragments/user_fragments/bodyHeader.jspf" %>

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3" >
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${sessionScope.currentUser.firstName}</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td><fmt:message key="txt.firstName"/></td>
                            <td>${sessionScope.currentUser.firstName}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="txt.lastName"/></td>
                            <td>${sessionScope.currentUser.lastName}</td>
                        </tr>
                        <tr>
                        <tr>
                            <td><fmt:message key="txt.email"/></td>
                            <td>${sessionScope.currentUser.email}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="txt.login"/></td>
                            <td>${sessionScope.currentUser.login}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="panel-footer">

        </div>

    </div>
</div>


<br/>

<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><fmt:message key="txt.test"/></th>
            <th><fmt:message key="txt.complexity"/></th>
            <th><fmt:message key="txt.time"/></th>
            <th><fmt:message key="txt.result"/></th>
        </tr>
        </thead>

        <c:forEach var="test" items="${listTests}">
            <tbody>
            <tr>
                <td>${test.title}</td>
                <td>${test.complexity}</td>
                <td>${test.timePassing} <fmt:message key="txt.minutes"/></td>
                <c:forEach var="storage" items="${listStorage}">
                    <c:if test="${storage.testId eq test.id}">
                        <td>${storage.result}</td>
                    </c:if>
                </c:forEach>

            </tr>
            </tbody>
        </c:forEach>

    </table>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>

</body>
</html>
