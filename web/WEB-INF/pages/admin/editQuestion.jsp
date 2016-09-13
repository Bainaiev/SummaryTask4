<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <th><fmt:message key="txt.question"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${test.questions}" var="question">
        <tr>
            <td>${question.questionText}</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/test/update/question" method="post">
                    <input type="hidden" value="${question.id}" name="id">
                    <input type="submit" value="<fmt:message key="app.editQuestion"/>" class="btn btn-info">
                </form>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>


    <div class="form-group">

        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" value="<fmt:message key="app.addQuestion"/>" class="btn btn-primary" form="addId">
        </div>

        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" value="<fmt:message key="app.finishEdit"/>" class="btn btn-success" form="finishId">
        </div>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/admin/test" id="finishId">
    </form>

    <form method="post" action="${pageContext.request.contextPath}/admin/test/add" id="addId">
        <input type="hidden" value="${test.id}" name="id">
    </form>



</div>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>
