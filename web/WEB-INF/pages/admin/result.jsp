<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<table>
    <c:forEach items="${list}" var="userBean">
        <tr>
            <td>${userBean.firstName}</td>
            <td>${userBean.lastName}</td>
            <td>${userBean.result}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
