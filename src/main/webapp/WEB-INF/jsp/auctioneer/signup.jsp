<!doctype html>
<html>
<head>
<title>Auctioneer Sign Up</title>
<!-- Include CSS File Here -->
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<!-- Include JS File Here -->
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
	<div class="container card shadow-lg " style="height: 75%;">
		<div class="card text-center mt-3"
			style="color: rgb(153, 40, 59); border: none;">
			<h2 style="font-weight: bold;">Create Auctioneer Account</h2>
		</div>

		<%
		if (null != request.getAttribute("error")) {
			out.println("<div class=\"alert alert-danger\"><b>" + request.getAttribute("error") + "</b></div>");
		}
		%>
		<form id="signup" method="POST" name="signup"
			action="http://localhost:9192/auctionhouse/signup/save">
			<label>Email Id :</label> <input type="email" name="email" id="email" />
			<label>AuctionHouse Name :</label> <input type="text"
				name="houseName" id="houseName" /> <label>Address :</label> <input
				type="text" name="address" id="address" /> <label>Contact :</label>
			<input type="text" name="contact" id="contact" /> <label>Password
				:</label> <input type="password" name="password" id="password" /> <input
				type="submit" value="Sign Up">
			<div
				style="text-align: center; margin-top: 0.2em; width: 100%; font-weight: bold;">
				<br> <a href="/login">Already Have Account?</a>
			</div>
		</form>
	</div>

	<script>
		function flagCheckForAlert() {
			let myform = document.getElementById("signup");
			let fd = new FormData(myform);
			$
					.ajax({
						type : "POST",
						url : "http://localhost:9192/auctionhouse/signup",
						data : fd,
						cache : false,
						processData : false,
						contentType : false,
						success : function(result) {
							if (result == "failure")
								document.getElementById("message").innerHTML = "Email already exists";
							else if (result == "success") {
								swal({
									title : "Successfully signed up",
									text : "You have successfully registered",
									icon : "success",
								})
										.then(
												function() {
													window.location.href = "http://localhost:9192/auctionhouse/login";
												});
							}
						}
					});
		}
	</script>
</body>
</html>