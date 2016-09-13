<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<link href="<c:url value="${pageContext.request.contextPath}/assets/css/custom.css"/>" type="text/css" rel="stylesheet"
      media="screen,projection"/>

<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>

<body>

<%@include file="/WEB-INF/fragments/user_fragments/bodyHeader.jspf" %>

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="txt.login"/></div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/login"
                      method="post"
                      onsubmit="return validate()">

                    <div class="form-group">

                        <label class="control-label col-sm-2" for="login"><fmt:message key="txt.login"/>:</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="login" name="login" placeholder="<fmt:message
                        key="txt.loginPh"/>">
                            <p class="validation" id="logError"></p>
                            <p class="validation">${loginInvalid}</p>
                            <p class="validation">${status}</p>
                            <p class="validation">${rights}</p>
                        </div>


                    </div>

                    <div class="form-group">

                        <label class="control-label col-sm-2" for="password"><fmt:message key="txt.password"/>:</label>

                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="<fmt:message key="txt.passwordPh"/>">
                            <p class="validation" id="passError"></p>
                            <p class="validation">${passwordInvalid}</p>
                        </div>


                    </div>

                    <div class="form-group">

                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default"><fmt:message key="app.login"/></button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>
<script src="${pageContext.request.contextPath}/assets/js/user/user.js"></script>


</body>
</html>
