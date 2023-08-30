<%@page import="model.whishlistmodel"%>
<%@page import="dao.Whishlistdao"%>
<%@page import="dao.Cartdao"%>
<%@page import="model.CartModel"%>
<%@page import="dao.Productdao"%>
<%@page import="model.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="customer-navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name='copyright' content=''>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title Tag  -->
<title>Eshop - eCommerce HTML5 Template.</title>
<!-- Favicon -->
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- Web Font -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap"
	rel="stylesheet">

<!-- StyleSheet -->

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- Magnific Popup -->
<link rel="stylesheet" href="css/magnific-popup.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="css/font-awesome.css">
<!-- Fancybox -->
<link rel="stylesheet" href="css/jquery.fancybox.min.css">
<!-- Themify Icons -->
<link rel="stylesheet" href="css/themify-icons.css">
<!-- Jquery Ui -->
<link rel="stylesheet" href="css/jquery-ui.css">
<!-- Nice Select CSS -->
<link rel="stylesheet" href="css/niceselect.css">
<!-- Animate CSS -->
<link rel="stylesheet" href="css/animate.css">
<!-- Flex Slider CSS -->
<link rel="stylesheet" href="css/flex-slider.min.css">
<!-- Owl Carousel -->
<link rel="stylesheet" href="css/owl-carousel.css">
<!-- Slicknav -->
<link rel="stylesheet" href="css/slicknav.min.css">

<!-- Eshop StyleSheet -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="css/responsive.css">
<style type="text/css">
<
style> /* Style your modal and overlay */ .modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}
</style>


<script>
	// Get references to the button and modal
	var openModalBtn = document.getElementById("openModalBtn");
	var closeModalBtn = document.getElementById("closeModalBtn");
	var modal = document.getElementById("myModal");

	// Open the modal when the button is clicked
	openModalBtn.addEventListener("click", function() {
		modal.style.display = "block";
	});

	// Close the modal when the close button is clicked
	closeModalBtn.addEventListener("click", function() {
		modal.style.display = "none";
	});

	// Close the modal if the user clicks outside of it
	window.addEventListener("click", function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	});
</script>
</head>
<body>
	<section class="cown-down">
		<div class="section-inner ">
			<div class="container-fluid">
				<div class="row">
					<%
					CartModel cm = Cartdao.getcartid();
					int cartid = cm.getCid();
					int cusid = cm.getCustid();
					%>
					<%
					int pid = Integer.parseInt(request.getParameter("pid"));
					%>
					<%
					ProductModel pm = Productdao.getProductById(pid);
					%>
					<div class="col-lg-6 col-12 padding-right">
						<div class="image">
							<img src="img/<%=pm.getPimage()%>" alt="#">
						</div>
					</div>
					<div class="col-lg-6 col-12 padding-left">
						<div class="content">
							<div class="heading-block">
								<h3 class="title"><%=pm.getPdesc()%></h3>
								<p class="small-title"><%=pm.getPcategory()%>
									(256GB) - Purple
								</p>

								<p class="text"></p>
								<h1 class="price">
									M.R.P:<%=pm.getPprice()%></s>
								</h1>
								<div class="coming-time">


									<form method="get" action="CartController">
										<input type="hidden" name="pid" value="<%=pm.getPid()%>">
										<input type="hidden" name="cusid" value="<%=cust.getId()%>">


										<button type="submit" class="btn" name="action"
											value="addtocart" style="margin-top: 29px;">Add to
											Cart</button>
									</form>



									<%
									whishlistmodel wm = Whishlistdao.getdatabyid();
									int wid = wm.getWid();
									boolean flag1 = Whishlistdao.getdata(wid, cust.getId());
									%>
									<%
									if (flag1 == false) {
									%>

									<form method="post" action="WhishlistController">
										<input type="hidden" name="pid" value="<%=pm.getPid()%>">
										<input type="hidden" name="cusid" value="<%=cust.getId()%>">


										<button type="submit" class="btn" name="action"
											value="addtowhishlist" style="margin-top: 29px;">Add
											to Whishlist</button>
									</form>



									<%
									} else {
									%>
									<button type="submit" class="btn" name="action"
										id="openModalBtn" value="addtowhishlist"
										style="margin-top: 29px;">Add to Whishlist</button>

									<!-- The Modal -->
									<div id="myModal" class="modal">
										<div class="modal-content">
											<span id="closeModalBtn">&times;</span>
											<p>Already Added To Your List.. </p>
										</div>
									</div>

									<%
									}
									%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Jquery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-migrate-3.0.0.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<!-- Popper JS -->
	<script src="js/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Color JS -->
	<script src="js/colors.js"></script>
	<!-- Slicknav JS -->
	<script src="js/slicknav.min.js"></script>
	<!-- Owl Carousel JS -->
	<script src="js/owl-carousel.js"></script>
	<!-- Magnific Popup JS -->
	<script src="js/magnific-popup.js"></script>
	<!-- Fancybox JS -->
	<script src="js/facnybox.min.js"></script>
	<!-- Waypoints JS -->
	<script src="js/waypoints.min.js"></script>
	<!-- Countdown JS -->
	<script src="js/finalcountdown.min.js"></script>
	<!-- Nice Select JS -->
	<script src="js/nicesellect.js"></script>
	<!-- Ytplayer JS -->
	<script src="js/ytplayer.min.js"></script>
	<!-- Flex Slider JS -->
	<script src="js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script src="js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script src="js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script src="js/easing.js"></script>
	<!-- Active JS -->
	<script src="js/active.js"></script>

	<script>
		// Get references to the button and modal
		var openModalBtn = document.getElementById("openModalBtn");
		var closeModalBtn = document.getElementById("closeModalBtn");
		var modal = document.getElementById("myModal");

		// Open the modal when the button is clicked
		openModalBtn.addEventListener("click", function() {
			modal.style.display = "block";
		});

		// Close the modal when the close button is clicked
		closeModalBtn.addEventListener("click", function() {
			modal.style.display = "none";
		});

		// Close the modal if the user clicks outside of it
		window.addEventListener("click", function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		});
	</script>
</body>
</html>