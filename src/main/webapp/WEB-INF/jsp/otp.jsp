<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>OTP Verification</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
	rel="stylesheet" type="text/css">

<link href="/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function(event) {

		function OTPInput() {
			const inputs = document.querySelectorAll('#otp > *[id]');
			for (let i = 0; i < inputs.length; i++) {
				inputs[i].addEventListener('keydown', function(event) {
					if (event.key === "Backspace") {
						inputs[i].value = '';
						if (i !== 0)
							inputs[i - 1].focus();
					} else {
						if (i === inputs.length - 1 && inputs[i].value !== '') {
							return true;
						} else if (event.keyCode > 47 && event.keyCode < 58) {
							inputs[i].value = event.key;
							if (i !== inputs.length - 1)
								inputs[i + 1].focus();
							event.preventDefault();
						} else if (event.keyCode > 64 && event.keyCode < 91) {
							inputs[i].value = String
									.fromCharCode(event.keyCode);
							if (i !== inputs.length - 1)
								inputs[i + 1].focus();
							event.preventDefault();
						}
					}
				});
			}
		}
		OTPInput();

	});
</script>
<style type="text/css">
.height-100 {
	height: 70vh
}

.card {
	width: 400px;
	border: none;
	height: 300px;
	box-shadow: 0px 5px 20px 0px #d2dae3;
	z-index: 1;
	display: flex;
	justify-content: center;
	align-items: center
}

.card h6 {
	color: red;
	font-size: 20px
}

.inputs input {
	width: 40px;
	height: 40px
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	margin: 0
}

.card-2 {
	background-color: #fff;
	padding: 10px;
	width: 350px;
	height: 100px;
	bottom: -50px;
	left: 20px;
	position: absolute;
	border-radius: 5px
}

.card-2 .content {
	margin-top: 50px
}

.card-2 .content a {
	color: red
}

.form-control:focus {
	box-shadow: none;
	border: 2px solid red
}

.validate {
	border-radius: 20px;
	height: 40px;
	background-color: red;
	border: 1px solid red;
	width: 140px
}

.error-space {
	text-align: center;
	height: 3em;
	width: 100%;
	height: 3em;
}
</style>

</head>
<body>

	<div class="error-space">

		<%
		if (null != request.getAttribute("error")) {
			out.println("<div class=\"error alert alert-danger\"><b>" + request.getAttribute("error") + "</b></div>");
		}
		%>
	</div>

	<div id="otp-popup"
		class="container height-100 d-flex justify-content-center align-items-center">
		<div class="position-relative">
			<div class="card p-2 text-center">
				<h6>
					Please enter the one time password <br> to verify your account
				</h6>
				<div>
					<span>A code has been sent to</span> <small>${email}</small>
				</div>
				<form action="/bidder/validateOTP" method="post">

					<div id="otp"
						class="inputs d-flex flex-row justify-content-center mt-2">
						<input class="m-2 text-center form-control rounded" type="text"
							id="first" maxlength="1" name="d1" /> <input
							class="m-2 text-center form-control rounded" type="text"
							id="second" maxlength="1" name="d2" /> <input
							class="m-2 text-center form-control rounded" type="text"
							id="third" maxlength="1" name="d3" /> <input
							class="m-2 text-center form-control rounded" type="text"
							id="fourth" maxlength="1" name="d4" /> <input
							class="m-2 text-center form-control rounded" type="text"
							id="fifth" maxlength="1" name="d5" /> <input
							class="m-2 text-center form-control rounded" type="text"
							id="sixth" maxlength="1" name="d6" />
					</div>
					<div class="mt-4">
						<button class="btn btn-danger px-4 validate">Validate</button>
					</div>
				</form>
			</div>
			<div class="card-2">
				<div
					class="content d-flex justify-content-center align-items-center">
					<span>Didn't get the code</span> <a href="/public/resendOTP"
						class="text-decoration-none ms-3">&nbsp;Resend</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
