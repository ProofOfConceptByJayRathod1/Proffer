<html>
<head>
<title>Bidder Sign Up</title>
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
	<div class="container card shadow-lg " style="height: 82%;">
		<div class="card text-center mt-3"
			style="color: rgb(153, 40, 59); border: none;">
			<h2 style="font-weight: bold;">Create Bidder Account</h2>
		</div>
		<%
		if (null != request.getAttribute("error")) {
			out.println("<div class=\"alert alert-danger\"><b>" + request.getAttribute("error") + "</b></div>");
		}
		%>
		<form id="signup" method="POST" name="signup"
			action="http://localhost:9192/bidder/signup/save">
			<label>Email :</label> <input type="email" name="bidderEmail"
				id="bidderEmail" /> <label>First name :</label> <input type="text"
				name="bidderFirstName" id="bidderFirstName" /> <label>LastName
				:</label> <input type="text" name="bidderLastName" id="bidderLastName" /> <label>Contact
				:</label> <input type="tel" name="bidderContact" id="bidderContact" /> <label>Password
				:</label> <input type="password" name="bidderPassword" id="bidderPassword" />
			<input type="submit" value="SignUp">
			<div
				style="text-align: center; margin-top: 0.2em; width: 100%; font-weight: bold;">
				<br> <a href="/auctionhouse/signup">Create Auctioneer
					Account</a>
			</div>
		</form>
	</div>
</body>
</html>