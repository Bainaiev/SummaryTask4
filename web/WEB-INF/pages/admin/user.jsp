<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>
</head>
<body>
<%@include file="/WEB-INF/fragments/admin_fragments/bodyHeader.jspf" %>

<div class="container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="txt.id"/></th>
                <th><fmt:message key="txt.firstName"/></th>
                <th><fmt:message key="txt.lastName"/></th>
                <th><fmt:message key="txt.login"/></th>
                <th><fmt:message key="txt.email"/></th>
                <th><fmt:message key="txt.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.status}</td>
                    <c:choose>
                        <c:when test="${user.status eq 'ACTIVE'}">
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/user" method="post">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="status" value="BANNED">
                                    <input type="submit" value="<fmt:message key="app.ban"/>" class="btn btn-danger">
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/user" method="post">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="status" value="ACTIVE">
                                    <input type="submit" value="<fmt:message key="app.unban"/>" class="btn btn-success">
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>

</body>
</html>
