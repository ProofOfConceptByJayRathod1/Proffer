
<!DOCTYPE html>
<%@page import="java.util.Arrays"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
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
				<!-- <li class="nav-item rounded bg-light search-nav-item"><input type="text" id="search" class="bg-light" placeholder="Search bread, cakes, desserts"><span class="fa fa-search text-muted"></span></li>
              <li class="nav-item"><a class="nav-link" href="#"><span class="fa fa-user-o"></span><span class="text">Login</span></a> </li>
              <li class="nav-item "><a class="nav-link" href="#"><span class="fa fa-shopping-cart"></span><span class="text">Cart</span></a> </li> -->
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
					<a class="dropdown-item" href="http://localhost:9192/logout">Log
						Out</a>
				</div>
			</div>
		</div>
		</div>
	</nav>
	<!-- navbar end -->
	<form method="post" enctype="multipart/form-data"
		action="/auctionhouse/addauction">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputEmail4">Email</label> <input type="text"
					class="form-control" name="eventTitle" id="eventTitle"
					placeholder="EventTitle">
			</div>
			<div class="form-group col-md-6">
				<label for="inputPassword4">Start Date</label> <input type="date"
					class="form-control" id="startDate" name="startDate"
					placeholder="dd/mm/yyyy">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="startTime">Start Time</label> <input type="time"
					class="form-control" name="startTime" id="startTime">
			</div>
			<div class="form-group col-md-6">
				<label for="duration">Duration</label> <input type="number"
					class="form-control" id="duration" name="duration" placeholder="10">
			</div>
		</div>
		<div class="form-group">
			<label for="description">Description</label><br> <input
				type="text" class="form-control" id="description" name="description">
		</div>
		<div class="form-group">
			<label for="image">Image</label><br> <input type="file"
				class="form-control" id="imgName" name="imgName">
		</div>
		<div class="form-row">
			<div class="form-group col-md-4">
				<label for="inputState">Catagory</label> <select id="category"
					name="category" class="form-control">
					<option selected>Choose...</option>
					<option>Arts and Antiques</option>
					<option>Jwellery</option>
					<option>Vehicales</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<label for="inputbid">Bid Start</label> <input type="number"
					class="form-control" id="currBidValue" name="currBidValue">
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Sign in</button>
	</form>
</body>
</html>