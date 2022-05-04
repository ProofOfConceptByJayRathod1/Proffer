<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>My History</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="/css/event.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
</head>
<body>


	<!-- navbar start -->
	<nav style="z-index: 1000; position: sticky; top: 0;"
		class="navbar navbar-expand-sm navbar-light bg-white border-bottom">
		<a class="navbar-brand ml-2 font-weight-bold" href="#"><span
			id="burgundy">Proxi</span><span id="orange">Bid</span></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor" aria-controls="navbarColor"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor">
			<ul class="navbar-nav mr-auto">
			</ul>

			<div class="nav-item dropdown ">

				<a class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <%
 JspWriter out2 = out;
 if (request.getCookies() != null) {
 	Arrays.asList(request.getCookies()).forEach((c) -> {
 		if (c.getName().equals("username")) {
 	try {
 		out2.print(c.getValue());
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
 		}
 	});
 }
 %> <i class="fa fa-user rounded-circle" aria-hidden="true"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right"
					style="margin-right: 10px;">
					<a class="dropdown-item"
						href="http://localhost:9192/bidder/dashboard">Dashboard</a> <a
						class="dropdown-item" href="http://localhost:9192/bidder/cart">My
						Cart</a> <a class="dropdown-item"
						href="http://localhost:9192/bidder/history">History</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="http://localhost:9192/logout">Log
						Out</a>
				</div>
			</div>
		</div>
	</nav>

	<div id="cart">
		<c:if test="${items==null}">
			<section style="margin-top: 1em; margin-bottom: 35em;">
				<div class="card container">
					<div class="card-body">
						<h5 class="card-title">No history available</h5>
						<p class="card-text">Start bidding to create history.</p>

					</div>
				</div>
			</section>
		</c:if>

		<!-- navbar end -->

		<c:if test="${items!=null}">

			<div class="card container" style="font-size: 1.5em;">Your
				history</div>
			<section style="margin-top: 1em;">
				<div class="container" id="live-container">
					<c:forEach var="c" items="${items}" varStatus="loopStatus">
						<div class="card">
							<div class="container-fluid">
								<div class="row">
									<img src="/catalogimage/${c.image}" class="card-img-top"
										style="width: 12em;" />
									<div class="col">
										<div class="card-body" id="liveBidArea${loopStatus.index}">
											<h5 class="card-title">${c.name}</h5>
											<p class="card-text">${c.description}</p>
										</div>
										<div class="row">
											<div class="col">
												<p class="card-text">
													<span class="font-weight-bold">Category :</span>
													${c.category}
												</p>
												<div class="conatiner">
													<span class="font-weight-bold">Auction :</span>
													${c.auctionTitle}
												</div>
											</div>
											<div class="col">
												<p class="card-text">
													<span class="font-weight-bold">Price :</span> $${c.price}
												</p>
												<div class="conatiner">
													<span class="font-weight-bold">Seller :</span>
													${c.sellerId}
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<p class="card-text">
													<span class="font-weight-bold">Payment :</span> <span
														style="color: green;">Successful!</span>
												</p>
											</div>
											<div class="col"></div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</section>
		</c:if>
		<br>
		<hr>
		<br>
	</div>
	<footer style="text-align: center; color: white;"> ProxiBid
		All rights reserved</footer>
	<script type="text/javascript" src="/js/live-bid.js"></script>
	<script type="text/javascript" src="/js/auction-alert.js"></script>
</body>
</html>