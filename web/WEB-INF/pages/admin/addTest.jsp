<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<link href="<c:url value="${pageContext.request.contextPath}/assets/css/custom.css"/>" type="text/css" rel="stylesheet"
      media="screen,projection"/>
<head>
</head>
<body>
<%@include file="/WEB-INF/fragments/admin_fragments/bodyHeader.jspf" %>

<div class="container">

    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/test/add" method="post"
          onsubmit="return validate()">
        <div class="form-group">
            <label for="title" class="control-label col-sm-2"><fmt:message key="txt.title"/>:</label>
            <div class="col-sm-5">
                <input type="text" name="title" placeholder="<fmt:message key="txt.titlePh"/>"  class="form-control" id="title">
                <p id="titleError" class="validation">${title}</p>

            </div>
        </div>

        <div class="form-group">
            <label for="complexity" class="control-label col-sm-2"><fmt:message key="txt.complexity"/>:</label>
            <div class="col-sm-5">
                <input type="text" name="complexity" placeholder="<fmt:message key="txt.complexityPh"/>" id="complexity" class="form-control">
                <p id="compError" class="validation">${complexity}</p>
            </div>
        </div>

        <div class="form-group">
            <label for="timePassing" class="control-label col-sm-2"><fmt:message key="txt.time"/>:</label>
            <div class="col-sm-5">
                <input type="text" name="timePassing" placeholder="<fmt:message key="txt.timePh"/>" id="timePassing" class="form-control">
                <p id="timeError" class="validation">${timePassing}</p>
            </div>
        </div>


        <div class="form-group">
            <label for="ids" class="control-label col-sm-2"><fmt:message key="txt.subject"/>:</label>

            <div class="col-sm-5">

                <select id="ids" class="form-control" name="subject">
                    <c:forEach items="${listSubject}" var="subject">
                        <option>${subject}</option>
                    </c:forEach>
                </select>
            </div>

        </div>


        <div class="form-group">

            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info"><fmt:message key="app.add"/></button>
            </div>

        </div>

    </form>
</div>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
<script src="${pageContext.request.contextPath}/assets/js/admin/test.js"></script>

</body>
</html>
