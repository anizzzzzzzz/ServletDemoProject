<%--
  Created by IntelliJ IDEA.
  User: Nitans
  Date: 6/17/2018
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>

    <h3>Create Product</h3>

    <p style="color: red;">${errorString}</p>

    <form method="POST" action="${pageContext.request.contextPath}/createProduct">
        <table border="0">
            <tr>
                <td>Code</td>
                <td><input type="text" name="pCode" value="${product.pCode}" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${product.name}" /></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="${product.price}" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit" />
                    <a href="productList">Cancel</a>
                </td>
            </tr>
        </table>
    </form>

    <jsp:include page="_footer.jsp"/>
</body>
</html>
