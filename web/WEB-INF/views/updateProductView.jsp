<%--
  Created by IntelliJ IDEA.
  User: Nitans
  Date: 6/17/2018
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>

    <h3>Edit Product</h3>

    <p style="color: red">${errorString}</p>

    <c:if test="${not empty product}">
        <form method="post" action="${pageContext.request.contextPath}/editProduct">
            <input type="hidden" name="pCode" value="${product.pCode}"/>
            <table border="0">
                <tr>
                    <td>Code</td>
                    <td style="color: red">${product.pCode}</td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="${product.name}"/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="price" value="${product.price}"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit"/>
                        <a href="${pageContext.request.contextPath}/productList">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>

    <jsp:include page="_footer.jsp"/>
</body>
</html>
