<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Dashboard</title>
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

<style>
.buttonarrow {
	display: inline-block;
	font-family: "Montserrat", Helvetica, sans-serif;
	/* Button is responsive to font size.*/
	font-size: 15px;
	-webkit-font-smoothing: antialiased;
	position: relative;
	padding: 0.8em 1.5em;
	border: none;
	color: white;
	transition: 0.2s;
	text-decoration: none;
	border: white solid 1px;
	text-transform: uppercase;
	letter-spacing: 1px;
	opacity: 0.95;
	padding-right: 3.5em;
	cursor: pointer;
}

.buttonarrow:before, .buttonarrow:after {
	position: absolute;
	padding-top: inherit;
	padding-bottom: inherit;
	font-size: inherit;
	top: 0;
	bottom: 0;
	right: 0;
	width: 2.8em;
	transition: 0.2s;
	transform-origin: 50% 60%;
}

/* Arrow Button Markup  */
.buttonarrow {
	background: #3c3b6e;
	padding-right: 50px;
}

.buttonarrow:hover {
	background: #b22234;
	color: azure;
}

.buttonarrow:active, .buttonarrow:focus {
	background: #b22234;
}
/* Entity Icon   */
.buttonarrow:after {
	content: "\2794";
}
/* Set Arrow Icon Bounce Animation   */
.buttonarrow:hover:after {
	-webkit-animation: bounceright 0.3s alternate ease infinite;
	animation: bounceright 0.3s alternate ease infinite;
}

/* Animations  */
@
-webkit-keyframes bounceright {from { -webkit-transform:translateX(0);
	
}

to {
	-webkit-transform: translateX(3px);
}

}
@
-webkit-keyframes wiggle {from { -webkit-transform:rotate(0deg);
	
}

to {
	-webkit-transform: rotate(30deg);
}

}
@
keyframes bounceright {from { transform:translateX(0);
	
}

to {
	transform: translateX(3px);
}

}
@
keyframes wiggle {from { transform:rotate(0deg);
	
}

to {
	transform: rotate(30deg);
}
}
</style>
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
						href="http://localhost:9192/auctionhouse/dashboard">Dashboard</a>
					<a class="dropdown-item"
						href="http://localhost:9192/auctioneer/history">My Auction</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="http://localhost:9192/logout">Log
						Out</a>
				</div>
			</div>
		</div>
	</nav>
	<!-- navbar end -->
	<div class="container py-3">
		<h2>Events created by you</h2>
		<div style="text-align: right;">
			<a href="http://localhost:9192/auctionhouse/auction"
				title="create event and add products" role="button"
				class="buttonarrow">Create an event</a>
		</div>

		<h2>Live events</h2>
		<!-- Card Start -->
		<c:forEach var="auction" items="${auctions}">

			<div class="card">
				<img src="/auctionimage/${auction.imageName}"
					style="border: 10px solid;" class="card-img-top" />
				<div class="card-body">
					<p class="card-text">
					<div class="container">
						<div class="row">
							<div class="col-sm-6">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">
											<b>Event Title: </b>${auction.eventTitle}</h5>
										<p class="card-text">
											<b>Description: </b>${auction.description}</p>
										<p class="card-text">
											<b>Category: </b>${auction.category}</p>

										<p class="card-text">
											<span style="font-weight: 800; color: red;"><i
												class="fa fa-wifi" aria-hidden="true"></i> LIVE</span>
										</p>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">Timings of the event</h5>
										<p class="card-text">
											<b>Event starts on: </b>${auction.startDate}</p>
										<p class="card-text">
											<b>Start Time : </b>${auction.startTime}
										</p>
										<p class="card-text">
											<b>End Time : </b>${auction.getEndDateTime().toLocalTime().toString()}

										</p>
										<p class="card-text">
											<b>Duration : </b>${auction.duration} minutes
										</p>

										<a href="/auctionhouse/event/${auction.eventNo}"
											class="btn btn-danger btn-block">Enter this event</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<!-- End of card -->
		<br>

		<hr>

		<h2>Today's Upcoming events</h2>
		<!-- Card Start -->
		<c:forEach var="auction" items="${todayUpcomingAuctions}">
			<div class="card">
				<img src="/auctionimage/${auction.imageName}"
					style="border: 10px solid;" class="card-img-top" />
				<div class="card-body">
					<p class="card-text">
					<div class="container">
						<div class="row">
							<div class="col-sm-6">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">
											<b>Event Title: </b>${auction.eventTitle}</h5>
										<p class="card-text">
											<b>Description: </b>${auction.description}</p>
										<p class="card-text">
											<b>Category: </b>${auction.category}</p>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">Timings of the event</h5>
										<p class="card-text">
											<b>Event starts on: </b>${auction.startDate}</p>
										<p class="card-text">
											<b>Start Time : </b>${auction.startTime}
										</p>
										<p class="card-text">
											<b>End Time : </b>${auction.getEndDateTime().toLocalTime().toString()}
										</p>
										<p class="card-text">
											<b>Duration : </b>${auction.duration} minutes
										</p>
										<a href="#" class="btn btn-outline-success btn-block">Notify</a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- End of card -->
	</div>
	<br>
	<hr>
	<br>
	<footer style="text-align: center; color: white;"> ProxiBid
		All rights reserved</footer>
	<script type="text/javascript">
		function showName(name) {
			alter(name)
		}
	</script>
</body>
</html>