<%--
  Created by IntelliJ IDEA.
  User: Nitans
  Date: 6/17/2018
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
hello
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>

    <h3>Hello : ${user.userName}</h3>

    User Name: <b>${user.userName}</b>
    <br/>
    Gender: ${user.gender} <br/>

    <jsp:include page="_footer.jsp"/>
</body>
</html>
