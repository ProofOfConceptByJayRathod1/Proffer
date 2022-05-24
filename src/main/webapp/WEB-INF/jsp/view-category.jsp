<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
	rel="stylesheet" type="text/css">

<link href="/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="caontiner">
		<!-- navigation bar start -->
		<nav class="navbar navbar-expand-lg"
			style="border-bottom: 1px solid grey;">
			<a class="navbar-brand" href="http://localhost:9192/"><span
				style="color: rgb(153, 40, 59); font-weight: bolder;">Proxi</span><span
				style="color: orange; font-weight: bolder;">Bid</span></a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon">=</span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">

					<li class="nav-item 	dropdown" style="font-weight: bold;"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Categories </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<c:forEach var="category" items="${categories}">
								<a class="dropdown-item"
									href="http://localhost:9192/proxibid.com/ViewCategory?category=${category.categoryName}">${category.categoryName}</a>
							</c:forEach>

							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="http://localhost:9192/proxibid.com/ViewCategory?category=all">View
								all categories</a>
						</div></li>
				</ul>
				<form class="form-inline my-2 my-lg-0"
					style="color: rgb(153, 40, 59); font-weight: bold;">

					<a class="mr-3" href="#">Help</a> <a class="mr-3" href="#">Selling</a>
					<a class="mr-3" href="#">Buying</a> <a
						class=" my-3 my-sm-0 font-weight-bold"
						style="color: rgb(153, 40, 59); outline-color: rgb(153, 40, 59);"
						href="http://localhost:9192/bidder/login">Login</a>
				</form>
			</div>
		</nav>
		<!-- navigation bar end -->
		<c:if test="${category.equals('All')}">
			<div class="caontiner text-center"
				style="font-family: monospace; color: rgb(153, 40, 59);">
				<form
					action="http://localhost:9192/proxibid.com/ViewCategory?keyword"
					class="mt-4 container">
					<input type="text" class="form-control" name="keyword" id="keyword"
						placeholder="Search">
				</form>
			</div>
		</c:if>

		<div class="container">
			Results for
			<c:if test="${category!=null}">
				<b>${category}</b>
			</c:if>
			<c:if test="${keyword!=null}">
				<b>/ ${keyword}</b>
			</c:if>

		</div>


		<c:if test="${categorizedList.size()==0}">
			<div class="container card text-center" style="margin-bottom: 30em;">
				<div class="text-center" style="font-size: 2em;">
					Oops, sorry for the inconvenience. <br> No results found for
					this category. <br>
				</div>
				<a href="http://localhost:9192/">Back to home</a>
			</div>
		</c:if>


		<!-- selected category items start -->
		<c:forEach var="item" items="${categorizedList}">
			<div class="container py-3">
				<div class="card"
					style="overflow: hidden; padding: 5; border: none; border-radius: .28571429rem; box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5; margin-top: 20px; flex-direction: row; align-items: center;">


					<img src="/catalogimage/${item.itemImage}"
						style="width: 20%; border-top-right-radius: 0; border-bottom-left-radius: calc(0.25rem - 1px); margin-left: 1em;"
						class="card-img-top" />

					<div class="card-body">
						<h5 class="card-title">${item.itemName}</h5>
						<hr>

						<div class="card-text">
							<div class="container">
								<div class="row">
									<div class="col"
										style="border-right: 1px solid rgb(170, 170, 170);">
										<b>Description</b><br> ${item.itemDesc}
									</div>
								</div>

								<div class="row">
									<div class="col" style="font-size: 12px; opacity: 0.6;">
										<b>Bid : </b>$ ${item.itemStartBid}
									</div>
								</div>
								<div class="row mt-4">
									<div class="col">
										<a href="/bidder/live-auction/" class="btn btn-danger"> <i
											class="fa fa-wifi" aria-hidden="true"></i> Enter live auction
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- selected category items end -->

		<!-- Footer -->
		<footer class="text-center text-lg-start bg-light text-muted">
			<!-- Section: Social media -->
			<section
				class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom"
				style="background-color: #dbdbdb; font-weight: bold; color: black;">
				<!-- Left -->
				<div class="me-5 d-none d-lg-block">
					<span>Get connected with us on social networks:</span>
				</div>
				<!-- Left -->

				<!-- Right -->
				<div style="font-size: 20px;">
					<a href="" class="me-4 text-reset"> <i class="fab fa-twitter"></i>
					</a> <a href="" class="me-4 text-reset"> <i class="fab fa-google"></i>
					</a> <a href="" class="me-4 text-reset"> <i class="fab fa-linkedin"></i>
					</a> <a href="" class="me-4 text-reset"> <i class="fab fa-github"></i>
					</a>
				</div>
				<!-- Right -->
			</section>
			<!-- Section: Social media -->
			<!-- Section: Links  -->
			<section class="" style="background-color: #dbdbdb;">
				<div class="container text-center text-md-start ">
					<!-- Grid row -->
					<div class="row p-2">
						<!-- Grid column -->
						<div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4 mt-3">
							<!-- Content -->
							<h6 class="text-uppercase fw-bold mb-4">
								<i class="fas fa-gem me-3"></i>Proxobid
							</h6>
							<p style="color: orange; font-weight: bold;">Bid, buy and
								enjoy!</p>
						</div>
						<!-- Grid column -->

						<!-- Grid column -->
						<div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
							<!-- Links -->
							<h6 class="text-uppercase fw-bold mb-4">About</h6>
							<p>
								<a href="#!" class="text-reset">About us</a>
							</p>
							<p>
								<a href="#!" class="text-reset">Newsletter</a>
							</p>
							<p>
								<a href="#!" class="text-reset">Careers</a>
							</p>
						</div>
						<!-- Grid column -->

						<!-- Grid column -->
						<div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
							<!-- Links -->
							<h6 class="text-uppercase fw-bold mb-4">Buying & Selling</h6>
							<p>
								<a href="http:/localhost:9192/login" class="text-reset">Seller
									Log in</a>
							</p>
							<p>
								<a href="http://localhost:9192/bidder/signup" class="text-reset">Buyer
									Sign Up</a>
							</p>
							<p>
								<a href="http://localhost:9192/auctionhouse/signup"
									class="text-reset">Apply To Sell</a>
							</p>

						</div>
						<!-- Grid column -->

						<!-- Grid column -->
						<div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
							<!-- Links -->
							<h6 class="text-uppercase fw-bold mb-4">Contact</h6>
							<p>
								<i class="fas fa-home me-3"></i> New York, NY 10012, US
							</p>
							<p>
								<i class="fas fa-envelope me-3"></i> info@proxibid.com
							</p>
							<p>
								<i class="fas fa-phone me-3"></i> + 01 234 567 88
							</p>
							<p>
								<i class="fas fa-print me-3"></i> + 01 234 567 89
							</p>
						</div>
						<!-- Grid column -->
					</div>
					<!-- Grid row -->
				</div>
			</section>
			<!-- Section: Links  -->
			<!-- Copyright -->
			<div class="text-center p-4"
				style="background-color: rgba(0, 0, 0, 0.05); font-weight: bold; color: black;">
				© 2022 Copyright: <a class="text-reset fw-bold"
					href="http://localhost:9192/">Prixibid.com</a>
			</div>
			<!-- Copyright -->
		</footer>
		<!-- Footer -->
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
</html>
