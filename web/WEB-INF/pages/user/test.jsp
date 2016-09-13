<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<link href="<c:url value="${pageContext.request.contextPath}/assets/css/timer.css"/>" type="text/css" rel="stylesheet" media="screen,projection"/>
<head>
    <title>Test user page</title>
</head>
<body>
<%@include file="/WEB-INF/fragments/user_fragments/bodyHeader.jspf" %>

<div class="container">


    <h1 class="timer-h1"><fmt:message key="txt.message"/>!</h1>
    <div id="clockdiv">
        <div>
            <span class="hours"></span>
            <div class="smalltext"><fmt:message key="txt.hour"/></div>
        </div>
        <div>
            <span class="minutes"></span>
            <div class="smalltext"><fmt:message key="txt.minutes"/></div>
        </div>
        <div>
            <span class="seconds"></span>
            <div class="smalltext"><fmt:message key="txt.second"/></div>
        </div>
    </div>


    <div class="text-left">
        <form action="${pageContext.request.contextPath}/user/profile" method="post" name="form">

            <c:forEach var="question" items="${test.questions}">

                <h4> ${question.questionText}</h4>
                <table class="table table-bordered">
                    <c:forEach var="answer" items="${question.answers}">
                        <tr>
                            <td>
                                <input class='answers' type="checkbox" name="${answer.questionId}"
                                       value="${answer.correct}"> ${answer.content}<br/>
                            </td>
                        </tr>
                    </c:forEach>
                    <br/>
                </table>

            </c:forEach>

            <input type="hidden" value="${test.id}" name="id">
            <input type="submit" value="<fmt:message key="app.submit"/>">
        </form>
    </div>

</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
<script src="${pageContext.request.contextPath}/assets/js/user/timer.js"></script>
<script>
    var deadline = new Date(Date.parse(new Date()) + ${test.timePassing} * 60 * 1000);
    window.onload = initializeClock('clockdiv', deadline);
    window.onload = counter(${test.timePassing});
</script>

</body>
</html>

