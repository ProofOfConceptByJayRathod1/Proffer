<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="/css/dashboard.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var down = false;
		$('#bell').click(function(e) {
			var color = $(this).text();
			if (down) {
				$('#box').css('height', '0px');
				$('#box').css('opacity', '0');
				down = false;
			} else {
				$('#box').css('height', 'auto');
				$('#box').css('opacity', '1');
				down = true;
			}
		});
	});
</script>
<style type="text/css">
* {
	padding: 0px;
	margin: 0px
}

.icon {
	cursor: pointer;
	/* line-height: 50px */
}

.icon span {
	background: #f00;
	padding: 7px;
	border-radius: 50%;
	vertical-align: top;
	margin-left: -10px
}

.icon img {
	display: inline-block;
	width: 26px;
	margin-top: 4px
}

.icon:hover {
	opacity: .7
}

.logo {
	flex: 1;
	color: #eee;
	font-size: 20px;
	font-family: monospace
}

.notifications {
	width: 300px;
	height: 20em;
	opacity: 0;
	position: absolute;
	z-index: 5;
	top: 63px;
	right: 62px;
	overflow-y: scroll;
	border-radius: 5px 0px 5px 5px;
	background-color: #fff;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	border-radius: 5px 0px 5px 5px;
	top: 63px;
}

.notifications h2 {
	font-size: 14px;
	padding: 10px;
	border-bottom: 1px solid #eee;
}

.notifications h2 span {
	color: #f00
}

.notifications-item {
	display: flex;
	border-bottom: 1px solid #eee;
	padding: 6px 9px;
	margin-bottom: 0px;
	cursor: pointer
}

.notifications-item:hover {
	background-color: #eee
}

.notifications-item img {
	display: block;
	width: 50px;
	height: 50px;
	margin-right: 9px;
	border-radius: 50%;
	margin-top: 2px
}

.notifications-item .n-text h4 {
	font-size: 16px;
	margin-top: 3px
}

.notifications-item .n-text p {
	font-size: 12px;
	margin-bottom: 0;
}
</style>
</head>
<body>

	<div id="main">
		<!-- navigation bar start -->
		<nav class="navbar navbar-expand-lg"
			style="border-bottom: 1px solid grey;">
			<a class="navbar-brand" href="#"><span
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
				</ul>

				<div class="nav-item icon nav-link" id="bell"
					style="font-size: 1em;">
					<i class="fa fa-bell"></i>
					<c:if test="${todaysAlerts.size()!=0}">
						<span
							style="font-size: 8px; height: 18px; width: 18px; margin-top: 0px; opacity: 0.7;"
							class="badge badge-info">${todaysAlerts.size()}</span>
					</c:if>
				</div>

				<div class="notifications" id="box">
					<c:if test="${todaysAlerts.size()!=0}">

						<h2>
							Today - <span>${todaysAlerts.size()}</span>
						</h2>
					</c:if>


					<c:forEach var="n" items="${todaysAlerts}">
						<div class="notifications-item">
							<img src="/auctionimage/${n.imageName}" alt="img">
							<div class="n-text">
								<h4>${n.eventTitle}</h4>
								<p>${n.message}</p>
							</div>
						</div>
					</c:forEach>

					<div class="dropdown-divider" style="background-color: gray;"></div>
					<c:forEach var="n" items="${pastAlerts}">
						<div class="notifications-item">

							<img src="/auctionimage/${n.imageName}" alt="img">
							<div class="n-text">
								<p style="font-size: 10px; color: grey;">${n.notifyAt}</p>
								<h4>${n.eventTitle}</h4>
								<p>${n.message}</p>
							</div>
						</div>
					</c:forEach>
				</div>


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
		<!-- navigation bar end -->


		<!-- Navbar section -->

		<div class="filter">
			<button class="btn btn-default" type="button" data-toggle="collapse"
				data-target="#mobile-filter" aria-expanded="true"
				aria-controls="mobile-filter">
				Filters<span class="fa fa-filter pl-1"></span>
			</button>
		</div>
		<div id="mobile-filter">
			<div class="border-bottom pb-2 ml-2">
				<h4 id="burgundy">Categories</h4>
			</div>
			<div class="py-2 border-bottom ml-3">
				<h6 class="font-weight-bold">Categories</h6>
				<div id="orange">
					<span class="fa fa-minus"></span>
				</div>
			</div>

		</div>
		<!-- Sidebar filter section -->
		<section id="sidebar">
			<div class="border-bottom pb-2 ml-2">
				<h4 id="burgundy">Filters</h4>
			</div>
			<div class="py-2 border-bottom ml-3">
				<h6 class="font-weight-bold">Categories</h6>
				<hr>
				<div id="orange">
					<span class="fa fa-minus"></span>
				</div>
				<form action="/bidder/dashboard/" method="GET">
					<c:forEach var="category" items="${categories}">
						<div class="form-group">
							<input type="checkbox" name="checkbox"
								value="${category.categoryName}"> <label
								for="${category.categoryName}">${category.categoryName}</label>
						</div>
					</c:forEach>
					<button class="btn btn-warning" type="submit">search
						results</button>
				</form>
			</div>

		</section>


		<div id="AllProducts">


			<!-- live event products start -->
			<section id="products">
				<div class="container">
					<h2>Live events</h2>
					<c:forEach var="auction" items="${auctions}">
						<div class="card mb-3" style="max-width: fit-content;">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img src="/auctionimage/${auction.imageName}" class="card-img"
										alt="...">
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h5 class="card-title">${auction.eventNo}.${auction.eventTitle}</h5>
										<p class="card-text">${auction.description}Thisisawidercard
											with supporting text below as a natural lead-in to additional
											content. This content is a little bit longer.</p>
										<p class="card-text">
											<span style="font-weight: 800; color: red;"><i
												class="fa fa-wifi" aria-hidden="true"></i> LIVE</span>
										</p>
										<a href="/bidder/event/${auction.eventNo}"
											class="btn btn-primary"><b>Enter this auction event</b></a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</section>
			<!-- live event products end -->

			<!-- products section -->
			<section id="products">
				<div class="container">
					<h2>Today's upcoming events</h2>
					<c:forEach var="auction" items="${upcomingTodaysAuctions}"
						varStatus="loop">
						<div class="card mb-3" style="max-width: fit-content;">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img src="/auctionimage/${auction.imageName}" class="card-img"
										alt="...">
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h5 class="card-title">${auction.eventNo}.${auction.eventTitle}</h5>
										<p class="card-text">${auction.description}Thisisawidercard
											with supporting text below as a natural lead-in to additional
											content. This content is a little bit longer.</p>

										<p class="card-text">
											<b> Starts in</b>&nbsp;&nbsp; <span
												onload="countdownTimeStart('${auction.startDateTime}','timer${loop.index}')"
												class="timer${loop.index}">00:00:00</span>
										</p>

										<button onclick="createAlert('${auction.eventNo}')"
											class="btn btn-outline-success" style="width: 10em;">Notify</button>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</section>

		</div>

	</div>
	<script type="text/javascript" src="/js/countdown-timer.js"></script>
	<script type="text/javascript" src="/js/auction-alert.js"></script>
</body>
</html>