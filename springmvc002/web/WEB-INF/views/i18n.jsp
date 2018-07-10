<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/7/10
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <fmt:message key="i18n.user"/>
    <br/>
    <a href="i18n2">I18N2 Page</a>

    <br/>
    <a href="i18n?locale=zh_CN">中文</a>

    <br/>
    <a href="i18n?locale=en_US">英文</a>

</body>
</html>
