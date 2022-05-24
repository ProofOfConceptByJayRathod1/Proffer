<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Create Auction</title>
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

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
	<div class="container">
		<form method="POST" id="auctionform" name="auctionform"
			enctype="multipart/form-data" action="/auctionhouse/auction">
			<div class="container">
				<div class="jumbotron text-center bg-success text-white">
					<h1>Auction details</h1>
					<p>Enter auction detalils</p>
				</div>

				<!--This is to add auction details-->
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">Event Name</label> <input type="text"
							class="form-control" name="eventTitle" id="eventTitle"
							placeholder="EventTitle" required>
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">Start Date</label> <input type="date"
							class="form-control" id="startDate" name="startDate"
							placeholder="dd/mm/yyyy" required>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="startTime">Start Time</label> <input type="time"
							class="form-control" name="startTime" id="startTime" required>
					</div>
					<div class="form-group col-md-6">
						<label for="duration">Duration</label> <input type="number"
							class="form-control" id="duration" name="duration"
							placeholder="10" required>
					</div>
				</div>
				<div class="form-group">
					<label for="description">Description</label><br> <input
						type="text" class="form-control" id="description"
						name="description" required>
				</div>



				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="image">Image</label><br> <input type="file"
							accept="image/*" class="form-control" id="imgName" name="imgName"
							required>
					</div>
					<div class="form-group col-md-6">
						<label for="inputState">Catagory</label> <select id="category"
							name="category" class="form-control" required>
							<option selected>Choose...</option>
							<c:forEach var="category" items="${categories}">
								<option>${category.categoryName}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<br> <br> <br> <br>

				<!--this is to add auction details-->
				<div class="jumbotron text-center bg-info text-white">
					<h1>Auction catalog</h1>
					<p>Create and delete the items to be added in the auction</p>
				</div>
				<div class="text-right">
					<button type="button" id="addRow" class="btn btn-primary">Add
						another product</button>
				</div>
				<div>
					<br>
				</div>

				<div class="row">
					<div class="col-lg-12">
						<div id="inputFormRow">
							<div class="input-group mb-3">

								<div class="form-group col-md-3">
									<label name="itemImage">Select Image of the Item</label> <input
										accept="image/*" type="file" class="form-control"
										name="itemImage" id="itemImage"
										placeholder="Select image of the item" required />
								</div>
								<div class="form-group col-md-3">
									<label name="itemName">Product/Item name:</label> <input
										type="text" class="form-control" id="itemName" name="itemName"
										placeholder="Enter the name of the product/object/entity/Item"
										required />
								</div>

								<div class="form-group col-md-3">
									<label name="itemStartBid">Enter the initial bid:</label> <input
										type="number" class="form-control" name="itemStartBid"
										id="itemStartBid"
										placeholder="enter the minimum bidding amount" required />
								</div>
								<div class="form-group col-md-3">
									<label name="itemDesc">Product/Item description:</label> <input
										type="text" class="form-control" id="itemDesc" name="itemDesc"
										placeholder="Describe the product/object/entity/Item" required />
								</div>

								<!-- <div class="text-right"> -->
								<div class="input-group">
									<button id="removeRow" type="button" class="btn btn-danger">remove
										this product</button>
								</div>
							</div>
						</div>

						<div id="newRow"></div>
						<button type="button" onclick="auctionCreated()"
							class="btn btn-success">Save all the catalog item
							details</button>
						<span id="message" style="color: red"></span>

					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		// add row
		$("#addRow")
				.click(
						function() {
							var html = '';
							html += '<div id="inputFormRow">';
							html += '<div class="input-group mb-3">';
							//all the way from 24 line 52
							html += '<div class="form-group col-md-3">';
							html += '<label name="itemImage">Select Image of the Item</label>';
							html += '<input type="file" class="form-control" name="itemImage" id="itemImage" placeholder="Select image of the item" required/>';
							html += '</div>';
							html += '<div class="form-group col-md-3">';
							html += '<label name="itemName">Product/Item name:</label>';
							html += '<input type="text" class="form-control" id="itemName" name="itemName" placeholder="Enter the name of the product/object/entity/Item" required/>';
							html += '</div>';
							html += '<div class="form-group col-md-3">';
							html += '<label name="itemStartBid">Enter the initial bid:</label>';
							html += '<input type="number" class="form-control" name="itemStartBid" id="itemStartBid" placeholder="enter the minimum bidding amount" required/>';
							html += '</div>';
							html += '<div class="form-group col-md-3">';
							html += '<label name="itemDesc">Product/Item description:</label>';
							html += '<input type="text" class="form-control" id="itemDesc" name="itemDesc" placeholder="Describe the product/object/entity/Item" required/>';
							html += '</div>';
							html += '<div class="input-group">';
							html += ' <button id="removeRow" type="button" class="btn btn-danger">remove this product</button>';
							html += '</div>';
							html += '</div>';

							$('#newRow').append(html);
						});

		// remove row
		$(document).on('click', '#removeRow', function() {
			$(this).closest('#inputFormRow').remove();
		});

		function auctionCreated() {
			let myform = document.getElementById("auctionform");
			let fd = new FormData(myform);
			$
					.ajax({
						type : "POST",
						url : "http://localhost:9192/auctionhouse/auction",
						data : fd,
						cache : false,
						processData : false,
						contentType : false,
						success : function(result) {
							if (result == "failure") {
								document.getElementById("message").innerHTML = "Sorry auction could not be created";
								document.getElementById("auctionform").reset();
							} else if (result == "success") {
								swal(
										{
											title : "Auction successfully created",
											text : "Auction will be live in few minutes",
											icon : "success",
										})
										.then(
												function() {
													window.location.href = "http://localhost:9192/auctionhouse/auction";
												});
							}
						}
					});
		}
	</script>
</body>

</html>
