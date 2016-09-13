<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>
</head>
<body>
<%@include file="/WEB-INF/fragments/admin_fragments/bodyHeader.jspf" %>
<div class="container">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/test/update/question"
          method="post" id="formId">

        <div class="alert-danger">${noCorrectAnswers}</div>
        <div class="alert-danger">${sameAnswers}</div>

        <div class="form-group">
            <label for="question" class="control-label col-sm-2"><fmt:message key="txt.question"/>:</label>
            <div class="col-sm-5">
                <p><textarea rows="5" cols="45" name="questionText" placeholder="<fmt:message key="txt.questionPh"/>"
                             class="form-control"
                             id="question">${question.questionText}</textarea></p>
                <p id="questionError" class="alert-danger"></p>

            </div>
        </div>

        <c:forEach items="${question.answers}" var="answer">
            <div class="form-group">
                <label for="answer1" class="control-label col-sm-2"><fmt:message key="txt.answer"/>:</label>
                <div class="col-sm-5">
                    <p><textarea rows="2" cols="30" name="answer" placeholder="<fmt:message key="txt.answerPh"/>"
                                 class="form-control"
                                 id="answer1" required>${answer.content}</textarea></p>
                    <p id="answer1Error" class="alert-danger"></p>
                </div>
            </div>

            <div class="form-group">
                <label for="correct1" class="control-label col-sm-2"><fmt:message key="txt.correct"/>:</label>
                <div class="col-sm-5">
                    <select name="correct" id="correct1">
                        <c:choose>
                            <c:when test="${answer.correct eq 'true'}">
                                <option value="TRUE" selected><fmt:message key="txt.true"/></option>
                                <option value="FALSE"><fmt:message key="txt.false"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="TRUE"><fmt:message key="txt.true"/></option>
                                <option value="FALSE" selected><fmt:message key="txt.false"/></option>
                            </c:otherwise>

                        </c:choose>

                    </select>
                </div>
            </div>
            <input type="hidden" name="answerId" value="${answer.id}">
        </c:forEach>

        <input type="hidden" name="id" value="0">
        <input type="hidden" name="testId" value="${question.testId}">
        <input type="hidden" name="questionId" value="${question.id}">
    </form>

    <div class="form-group">

        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary" form="formId"><fmt:message key="app.editQuestion"/></button>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>
