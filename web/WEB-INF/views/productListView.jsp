<%--
  Created by IntelliJ IDEA.
  User: Nitans
  Date: 6/17/2018
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>

    <h3>Product List</h3>

    <p style="color:red">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Price</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.pCode}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>
                    <a href="editProduct?pCode=${product.pCode}">Edit</a>
                </td>
                <td>
                    <a href="deleteProduct?pCode=${product.pCode}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="createProduct" >Create Product</a>

    <jsp:include page="_footer.jsp"/>
</body>
</html>
