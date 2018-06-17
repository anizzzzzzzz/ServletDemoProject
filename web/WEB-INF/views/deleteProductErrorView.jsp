<%--
  Created by IntelliJ IDEA.
  User: Nitans
  Date: 6/17/2018
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>

    <h3>Delete Product</h3>

    <p style="color: red;">${errorString}</p>
    <a href="productList">Product List</a>

    <jsp:include page="_footer.jsp"/>
</body>
</html>
