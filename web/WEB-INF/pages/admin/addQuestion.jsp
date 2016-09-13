<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>
</head>
<body>
<%@include file="/WEB-INF/fragments/admin_fragments/bodyHeader.jspf" %>


<div class="container">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/test/add/question"
          method="post" id="formId">

        <div class="alert-danger">${noCorrectAnswers}</div>
        <div class="alert-danger">${sameAnswers}</div>

        <div class="form-group">
            <label for="question" class="control-label col-sm-2"><fmt:message key="txt.question"/>:</label>
            <div class="col-sm-5">
                <p><textarea rows="5" cols="45" name="questionText" placeholder="<fmt:message key="txt.questionPh"/>"
                             class="form-control"
                             id="question" required></textarea></p>
                <p id="questionError" class="alert-danger"></p>

            </div>
        </div>

        <div class="form-group">
            <label for="answer1" class="control-label col-sm-2"><fmt:message key="txt.answer"/>:</label>
            <div class="col-sm-5">
                <p><textarea rows="2" cols="30" name="answer" placeholder="<fmt:message key="txt.answerPh"/>"
                             class="form-control"
                             id="answer1" required></textarea></p>
                <p id="answer1Error" class="alert-danger"></p>
            </div>
        </div>

        <div class="form-group">
            <label for="correct1" class="control-label col-sm-2"><fmt:message key="txt.correct"/>:</label>
            <div class="col-sm-5">
                <select name="correct" id="correct1">
                    <option value="TRUE"><fmt:message key="txt.true"/></option>
                    <option value="FALSE" selected><fmt:message key="txt.false"/></option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="answer2" class="control-label col-sm-2"><fmt:message key="txt.answer"/>:</label>
            <div class="col-sm-5">
                <p><textarea rows="2" cols="30" name="answer" placeholder="<fmt:message key="txt.answerPh"/>"
                             class="form-control"
                             id="answer2" required></textarea></p>
                <p id="answer2Error" class="alert-danger"></p>
            </div>

        </div>
        <div class="form-group">
            <label for="correct2" class="control-label col-sm-2"><fmt:message key="txt.correct"/>:</label>
            <div class="col-sm-5">
                <select name="correct" id="correct2">
                    <option value="TRUE"><fmt:message key="txt.true"/></option>
                    <option value="FALSE" selected><fmt:message key="txt.false"/></option>
                </select>
            </div>

        </div>

        <div class="form-group">
            <label for="answer3" class="control-label col-sm-2"><fmt:message key="txt.answer"/>:</label>
            <div class="col-sm-5">
                <p><textarea rows="2" cols="30" name="answer" placeholder="<fmt:message key="txt.answerPh"/>"
                             class="form-control"
                             id="answer3" required></textarea></p>
                <p id="answer3Error" class="alert-danger"></p>
            </div>

        </div>
        <div class="form-group">
            <label for="correct3" class="control-label col-sm-2"><fmt:message key="txt.correct"/>:</label>
            <div class="col-sm-5">
                <select name="correct" id="correct3">
                    <option value="TRUE"><fmt:message key="txt.true"/></option>
                    <option value="FALSE" selected><fmt:message key="txt.false"/></option>
                </select>
            </div>

        </div>

        <div class="form-group">
            <label for="answer4" class="control-label col-sm-2"><fmt:message key="txt.answer"/>:</label>
            <div class="col-sm-5">
                <p><textarea rows="2" cols="30" name="answer" placeholder="<fmt:message key="txt.answerPh"/>"
                             class="form-control"
                             id="answer4" required></textarea></p>
                <p id="answer4Error" class="alert-danger"></p>
            </div>

        </div>
        <div class="form-group">
            <label for="correct4" class="control-label col-sm-2"><fmt:message key="txt.correct"/>:</label>
            <div class="col-sm-5">
                <select name="correct" id="correct4">
                    <option value="TRUE"><fmt:message key="txt.true"/></option>
                    <option value="FALSE" selected><fmt:message key="txt.false"/></option>
                </select>
            </div>

        </div>

        <input type="hidden" value="${id}" name="id">


    </form>

    <form action="${pageContext.request.contextPath}/admin/test" class="text-center" role="form" id="formId2"></form>

    <div class="form-group">

        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary" form="formId"><fmt:message key="app.addQuestion"/></button>
        </div>

        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" class="btn btn-warning" value="<fmt:message key="app.submit"/>"
                   form="formId2"/>
        </div>
    </div>

</div>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>


</body>
</html>
