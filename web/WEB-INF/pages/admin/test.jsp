<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>
</head>
<body>
<%@include file="/WEB-INF/fragments/admin_fragments/bodyHeader.jspf" %>


<div class="container">
    <form action="${pageContext.request.contextPath}/admin/test/add" method="post">
        <input type="hidden" name="add" value="add">
        <button type="submit" class="btn btn-success"><fmt:message key="app.add"/></button>
    </form>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th><fmt:message key="txt.id"/></th>
            <th><fmt:message key="txt.title"/></th>
            <th><fmt:message key="txt.complexity"/></th>
            <th><fmt:message key="txt.time"/></th>
            <th><fmt:message key="txt.subject"/></th>
            <th><fmt:message key="txt.numberOfQuestions"/></th>
        </tr>
        </thead>

        <c:forEach items="${listTests}" var="test">
            <tbody>
            <tr>
                <td>${test.id}</td>
                <td>${test.title}</td>
                <td>${test.complexity}</td>
                <td>${test.timePassing}</td>
                <td>${test.subject}</td>
                <td>${test.questions.size()}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/test/edit" method="post">
                        <input type="hidden" value="${test.id}" name="id">
                        <input type="submit" value="<fmt:message key="app.edit"/>" class="btn btn-warning">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/test/delete" method="post">
                        <input type="hidden" value="${test.id}" name="id">
                        <input type="submit" value="<fmt:message key="app.delete"/>" class="btn btn-danger">
                    </form>
                </td>
            </tr>
            </tbody>
        </c:forEach>


    </table>
</div>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>
