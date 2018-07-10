<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/6/29
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Success Page</h4>

    time:
    <br/>
    names: ${requestScope.names}
    <br/>
    request user: ${requestScope.user}
    <br/>
    session user: ${sessionScope.user}
    <br/>
    request school: ${requestScope.school}
    <br/>
    session school: ${sessionScope.school}
    <br/>
    abc user: ${requestScope.abc}
    <br/>
    mnxyz user: ${requestScope.mnxyz}
    <br/>
    <%--<fmt:message key="i18n.username"/>--%>
    <br/>
    <%--<fmt:message key="i18n.password"/>--%>

</body>
</html>
