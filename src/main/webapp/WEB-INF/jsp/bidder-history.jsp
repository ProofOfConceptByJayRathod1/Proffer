<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Live Auction</title>
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
						class="dropdown-item"
						href="http://localhost:9192/bidder/dashboard">My Cart</a> <a
						class="dropdown-item"
						href="http://localhost:9192/bidder/dashboard">History</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="http://localhost:9192/logout">Log
						Out</a>
				</div>
			</div>
		</div>
	</nav>

	<!-- navbar end -->
	<input type="hidden" id="b_id" value="${bidderEmail}">
	<section>
		<div class="tabs">

			<div class="container" id="live-container">
				<c:forEach var="c" items="${liveItems}" varStatus="loopStatus">
					<div class="card">
						<img src="/catalogimage/${c.catalog.itemImage}"
							style="border: 5px solid #555;" class="card-img-top" />


						<div class="card-body" id="liveBidArea${loopStatus.index}">
							<h5 class="card-title">${c.catalog.itemName}</h5>
							<p class="card-text">${c.catalog.itemDesc}</p>


							<div id="name-from">
								<div class="container-fluid">
									<div class="row">
										<div class="offset-md-12">
											<div>
												<!-- amount bid by this person  -->
												<small id="${c.catalog.itemId}c" class="text-muted"></small>
											</div>
											<div class="input-group"></div>
										</div>
									</div>
									<div class="row">
										<div class="col">
											<div class="conatiner">

												<c:if test="${c.bidStatus.equals('SOLD')}">
													<span style="font-size: 20px; font-weight: 300;">STATUS
														: <span style="font-weight: 800; color: green;">
															${c.bidStatus}</span>
													</span>
												</c:if>
												<c:if test="${c.bidStatus.equals('LIVE')}">
													<span style="font-size: 20px; font-weight: 300;">STATUS
														: <span style="font-weight: 800; color: red;"><i
															class="fa fa-wifi" aria-hidden="true"></i> ${c.bidStatus}</span>
													</span>
												</c:if>

												<c:if test="${c.bidStatus.equals('INITIAL')}">
													<span style="font-size: 20px; font-weight: 300;">STATUS
														: <span style="font-weight: 800; color: #ffb03b;">
															${c.bidStatus}</span>
													</span>
												</c:if>

											</div>
										</div>
										<div class="col">
											<div class="conatiner">
												<span style="font-size: 20px; font-weight: 300;">HIGH
													BID : <span style="font-weight: 800; color: blue;">$${c.currentBidValue}</span>
												</span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col">
											<div class="conatiner">
												<span style="font-size: 20px; font-weight: 300;"> <span
													style="font-weight: 800; color: red;"></span>
												</span>
											</div>
										</div>
										<div class="col">
											<div class="conatiner">
												<c:choose>
													<c:when test="${not c.bidderId.equals(bidderId)}">
														<span style="font-size: 16px; font-weight: 300;">BY
															: <span style="font-weight: 300; color: #0d11e0;">${c.bidderId}</span>
														</span>
													</c:when>
													<c:otherwise>
														<span style="font-size: 16px; font-weight: 300;">BY
															: <span style="font-weight: 300; color: #0d11e0;">You</span>
														</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>
							</div>

							<c:if
								test="${c.bidStatus.equals('SOLD') && c.bidderId.equals(bidderId)}">

								<div class="container text-center"
									style="font-weight: 800; color: red; font-size: 3em;">
									<span>You won!!!</span> <br>
									<button class="btn btn-dark float-right"
										style="margin-right: 0.5em; margin-top: 2em; width: 10em;"
										onClick="">Checkout Now</button>
								</div>

							</c:if>

							<c:if
								test="${c.bidStatus.equals('LIVE') && c.bidderId.equals(bidderId)}">

								<div class="container text-center"
									style="font-weight: 800; color: green; font-size: 2.5em;">
									<span>You Made BID!</span>

								</div>

							</c:if>
							<c:if
								test="${c.bidStatus.equals('LIVE') && not c.bidderId.equals(bidderId)}">
								<button class="btn btn-success float-right"
									style="margin-right: 0.8em; margin-top: 2em; width: 10em;"
									id="live-btn1${loopStatus.index}"
									onClick="updateBid('${c.id}','${bidderId}','${c.currentBidValue+10}','liveBidArea${loopStatus.index}')">BID
									$${c.currentBidValue+10}</button>
							</c:if>

							<c:if test="${c.bidStatus.equals('INITIAL')}">
								<button class="btn btn-success float-right"
									style="margin-right: 0.8em; margin-top: 2em; width: 10em;"
									id="live-btn1${loopStatus.index}"
									onClick="updateBid('${c.id}','${bidderId}','${c.currentBidValue+10}','liveBidArea${loopStatus.index}')">START
									BID $${c.currentBidValue+10}</button>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</section>
	<br>
	<hr>
	<br>

	<footer style="text-align: center; color: white;"> ProxiBid
		All rights reserved</footer>

</body>
</html>