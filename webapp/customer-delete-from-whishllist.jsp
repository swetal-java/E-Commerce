<%@page import="dao.Whishlistdao"%>
<%@page import="model.whishlistmodel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% int wisid = Integer.parseInt(request.getParameter("whishid"));
Whishlistdao.delfromwhishlist(wisid);
response.sendRedirect("customer-home.jsp");
%>
</body>
</html>