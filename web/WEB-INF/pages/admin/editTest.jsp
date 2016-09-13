<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>

</head>
<body>
<%@include file="/WEB-INF/fragments/admin_fragments/bodyHeader.jspf" %>

<div class="container">

    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/test/update" method="post"
          onsubmit="return validate()">
        <div class="form-group">
            <label for="title" class="control-label col-sm-2"><fmt:message key="txt.title"/>:</label>
            <div class="col-sm-5">
                <input type="text" name="title" placeholder="<fmt:message key="txt.timePh"/>" class="form-control" id="title"
                       value="${test.title}">
                <p id="titleError" class="alert-danger"></p>

            </div>
        </div>

        <div class="form-group">
            <label for="complexity" class="control-label col-sm-2"><fmt:message key="txt.complexity"/>:</label>
            <div class="col-sm-5">
                <input type="text" name="complexity" placeholder="<fmt:message key="txt.complexityPh"/>" id="complexity"
                       class="form-control" value="${test.complexity}">
                <p id="compError" class="alert-danger"></p>
            </div>
        </div>

        <div class="form-group">
            <label for="timePassing" class="control-label col-sm-2"><fmt:message key="txt.time"/>:</label>
            <div class="col-sm-5">
                <input type="text" name="timePassing" placeholder="<fmt:message key="txt.timePh"/>" id="timePassing"
                       class="form-control" value="${test.timePassing}">
                <p id="timeError" class="alert-danger"></p>
            </div>
        </div>


        <div class="form-group">
            <label for="ids" class="control-label col-sm-2"><fmt:message key="txt.subject"/>:</label>

            <div class="col-sm-5">

                <select id="ids" class="form-control" name="subject">
                    <option selected>${test.subject}</option>
                    <c:forEach items="${listSubject}" var="subject">
                        <option>${subject}</option>
                    </c:forEach>
                </select>
            </div>

        </div>


        <div class="form-group">

            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default"><fmt:message key="app.submit"/></button>
            </div>

        </div>

        <input type="hidden" value="${test.id}" name="id">

    </form>
</div>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
<script src="${pageContext.request.contextPath}/assets/js/admin/test.js"></script>

</body>
</html>
