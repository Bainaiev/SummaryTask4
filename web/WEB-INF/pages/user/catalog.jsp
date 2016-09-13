<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/fragments/headTag.jspf" %>
<head>
    <title>Catalog page</title>
</head>
<body>
<%@include file="/WEB-INF/fragments/user_fragments/bodyHeader.jspf" %>


<div class="container">


    <form action="${pageContext.request.contextPath}/user/catalog" method="post">
        <div>
            <div class="form-group  col-md-3">

                <label for="title"><fmt:message key="txt.search"/></label>
                <select name="title" class="form-control" id="title">
                    <option selected></option>
                    <c:forEach var="subject" items="${listSubject}">
                        <option value="${subject}">${subject}</option>
                    </c:forEach>
                </select>

                <label for="sort"><fmt:message key="txt.sort"/></label>
                <select name="sort" class="form-control" id="sort">
                    <option selected></option>
                    <option value="title"><fmt:message key="txt.title"/></option>
                    <option value="complexity"><fmt:message key="txt.complexity"/></option>
                    <%--<option value="numberOfQuestions">numberOfQuestions</option>--%>
                    <option value="time_passing"><fmt:message key="txt.time"/></option>
                </select>

                <label for="order"><fmt:message key="txt.order"/></label>
                <select name="order" class="form-control" id="order">
                    <option selected></option>
                    <option value="ASC"><fmt:message key="txt.asc"/></option>
                    <option value="DESC"><fmt:message key="txt.desc"/></option>
                </select>


                <input type="submit" value="<fmt:message key="app.filter"/>" class="btn btn-primary">

            </div>
        </div>
    </form>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>№</th>
            <th><fmt:message key="txt.test"/></th>
            <th><fmt:message key="txt.complexity"/></th>
            <th><fmt:message key="txt.time"/></th>
            <th><fmt:message key="txt.subject"/></th>
            <th><fmt:message key="txt.numberOfQuestions"/> </th>
        </tr>
        </thead>


        <c:forEach var="test" items="${listTests}">
            <tbody>
            <tr>
                <td>${test.id}</td>
                <td>${test.title}</td>
                <td>${test.complexity}</td>
                <td>${test.timePassing} <fmt:message key="txt.minutes"/></td>
                <td>${test.subject}</td>
                <td>${test.questions.size()}</td>
                <c:if test="${not empty sessionScope.currentUser}">
                    <c:set var="flag" value="false"/>
                    <c:forEach var="storage" items="${listStorage}">
                        <c:if test="${test.id eq storage.testId}">
                            <c:set var="flag" value="true" scope="page"/>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${flag eq 'true'}">
                            <td class="success">
                                Тест пройден!
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <form action="${pageContext.request.contextPath}/user/test" method="post">
                                    <input type="hidden" value="${test.id}" name="id">
                                    <input type="submit" value="Пройти тест" class="btn btn-primary">
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </tr>
            </tbody>
        </c:forEach>


    </table>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>

</body>
</html>
