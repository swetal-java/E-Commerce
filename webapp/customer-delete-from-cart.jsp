<%@page import="dao.Cartdao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int cartid= Integer.parseInt(request.getParameter("cartid"));
Cartdao.deletefromcart(cartid);
response.sendRedirect("customer-cart.jsp");
%>
</body>
</html>