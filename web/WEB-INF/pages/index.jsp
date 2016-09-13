<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="/WEB-INF/fragments/headTag.jspf" %>

<head>
    <title>Home Page</title>
</head>

<body>
<%@include file="/WEB-INF/fragments/user_fragments/bodyHeader.jspf" %>


<h1 class="center center-align -align-center text-center"><fmt:message key="app.tests"/></h1>
<img src="assets/img/homepage.jpg" width="400" height="300" alt="" style="display:block; margin:0 auto;">


<div class="text-center">
    <a href="${pageContext.request.contextPath}/user/catalog" class="btn btn-lg btn-primary"><fmt:message
            key="app.tests"/> </a>
</div>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>

</body>
</html>
