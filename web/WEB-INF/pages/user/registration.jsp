<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<link href="<c:url value="${pageContext.request.contextPath}/assets/css/custom.css"/>" type="text/css" rel="stylesheet"
      media="screen,projection"/>
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
</head>
<body>
<%@include file="/WEB-INF/fragments/user_fragments/bodyHeader.jspf" %>

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="app.register"/></div>
            <div class="panel-body">
                <form action="${pageContext.request.contextPath}/user/register" method="post" class="form-horizontal"
                      role="form"
                      onsubmit="return regValidate()">

                    <div class="form-group">
                        <label for="fn" class="control-label col-sm-2"><fmt:message key="txt.firstName"/>:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="firstName" id="fn"
                                   placeholder="<fmt:message key="txt.firstNamePh"/>">
                            <p id="fnError" class="validation">${firstName}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="ln" class="control-label col-sm-2"><fmt:message key="txt.lastName"/>:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="lastName" id="ln"
                                   placeholder="<fmt:message key="txt.lastNamePh"/>">
                            <p class="validation" id="lnError">${lastName}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="em" class="control-label col-sm-2"><fmt:message key="txt.email"/>:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="em"
                                   placeholder="<fmt:message key="txt.emailPh"/>">
                            <p class="validation" id="emError">${Email}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lg" class="control-label col-sm-2"><fmt:message key="txt.login"/>:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="login" id="lg"
                                   placeholder="<fmt:message key="txt.loginPh"/>">
                            <p class="validation" id="logError">${loginInvalid}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="ps" class="control-label col-sm-2"><fmt:message key="txt.password"/>:</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" id="ps"
                                   placeholder="<fmt:message key="txt.passwordPh"/>">
                            <p class="validation" id="passError">${passwordInvalid}</p>
                        </div>
                    </div>

                    <div class="col-sm-10">
                        <p class="validation" id="error">${error}</p>
                    </div>

                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" class="btn btn-default" value="<fmt:message key="app.submit"/>">
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
