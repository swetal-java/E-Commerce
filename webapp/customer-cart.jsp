<%@page import="java.util.List"%>
<%@page import="dao.Productdao"%>
<%@page import="model.ProductModel"%>
<%@page import="dao.Cartdao"%>
<%@page import="model.CartModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="customer-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">
							<li><a href="customer-home.jsp">Home<i
									class="ti-arrow-right"></i></a></li>
							<li class="active"><a href="customer-cart.jsp">Cart</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Shopping Cart -->
	<div class="shopping-cart section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
						<thead>
							<tr class="main-hading">
								<th>PRODUCT</th>
								<th>NAME</th>
								<th class="text-center">UNIT PRICE</th>
								<th class="text-center">QUANTITY</th>
								<th class="text-center">TOTAL</th>
								<th class="text-center"><i class="ti-trash remove-icon"></i></th>
							</tr>
						</thead>
						<tbody>
							
							<%
							int custid = cust.getId();
							%>
							<%
							List<CartModel> list = Cartdao.getProductByCid(custid);
							%>
							<% int totalprice = 0; %>
							<% for(CartModel cmodel : list){ 
								int pid = cmodel.getPid();
								ProductModel pm = Productdao.getProductById(pid);
								
								 totalprice = totalprice + cmodel.getTotal(); 
							%>
							
							<tr>
								<td class="image" data-title="No"><img src="img/<%= pm.getPimage()%>"
									alt="#"></td>
								<td class="product-des" data-title="Description">
									<p class="product-name">
										<a href="#"><%=cmodel.getPname()%></a>
									</p>

								</td>
								<td class="price" data-title="Price"><span><%=cmodel.getPrice()%></span></td>
								<td class="qty" data-title="Qty">
									<!-- Input Order -->
									<div class="input-group">
										<div class="button minus">
											
										</div>
										<input type="number" min="1" max="10" value="<%= cmodel.getQty() %>" name="pqty" onchange="this.form.submit();" style="margin-left: 100px;">
										
										<div class="button plus">
											
										</div>
									</div> <!--/ End Input Order -->
								</td>
								<td class="total-amount" data-title="Total"><span><%= cmodel.getTotal() %></span></td>
								<td class="action" data-title="Remove"><a href="customer-delete-from-cart.jsp?cartid=<%=cmodel.getCid()%>"><i
										class="ti-trash remove-icon"></i></a></td>
							</tr>
							<%} %>
						</tbody>
					</table>
					<!--/ End Shopping Summery -->
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<!-- Total Amount -->
					<div class="total-amount">
						<div class="row">
							<div class="col-lg-8 col-md-5 col-12">
								<div class="left">
									<div class="coupon">
										<form action="#" target="_blank">
											<input name="Coupon" placeholder="Enter Your Coupon">
											<button class="btn">Apply</button>
										</form>
									</div>
									<div class="checkbox">
										<label class="checkbox-inline" for="2"><input
											name="news" id="2" type="checkbox"> Shipping (+10$)</label>
									</div>
								</div>
							</div>
							<div class="col-lg-4 col-md-7 col-12">
								<div class="right">
								
									<ul>
										<li>Cart Subtotal<span><%= totalprice %></span></li>
										<li>Shipping<span>Free</span></li>
										
										<li class="last">You Pay<span><%= totalprice %></span></li>
									</ul>
									<div class="button5">
										<a href="#" class="btn">Checkout</a> <a href="#" class="btn">Continue
											shopping</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--/ End Total Amount -->
				</div>
			</div>
		</div>
	</div>
	<!--/ End Shopping Cart -->
</body>
</html>