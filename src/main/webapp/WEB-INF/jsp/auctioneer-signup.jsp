<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>

<head>
    <title>Javascript Login Form Validation</title>
    <!-- Include CSS File Here -->
    <link rel="stylesheet" href="/css/style.css" />
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Include JS File Here -->

</head>

<body>
    <div class="container">
        <div class="main">
            <h2>Sign Up for Auctioneer</h2>
            <form id="signup" method="post" name="signup" action="http://localhost:9192/auctionhouse/signup">
                <label>Seller Id :</label>
                <input type="email" name="email" id="email" />
                <label>AuctionHouse Name :</label>
                <input type="text" name="houseName" id="houseName" />
                <label>Address :</label>
                <input type="text" name="address" id="address" />
                <label>Contact :</label>
                <input type="text" name="contact" id="contact" />
                <label>Password :</label>
                <input type="password" name="password" id="password" />
                <!-- <input type="submit" value="SignUp"> -->
                <button value="Submit" type="button" onclick="flagCheckForAlert()" class="button">Signup</button>
                <h1>${checkForExistingEmail}</h1>
                <span id="message" style="color:red"></span>
            </form>
            <!-- <span><b class="note">Note : </b>For this demo use following username and password. <br /><b
                    class="valid">User Name : Formget<br />Password : formget#123</b></span> -->
        </div>
    </div>
    <script>


        function flagCheckForAlert() {
            let myform = document.getElementById("signup");
            let fd = new FormData(myform);
            $.ajax({
                type: "POST",
                url: "http://localhost:9192/auctionhouse/signup",
                data: fd,
                cache: false,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result == "failure") document.getElementById("message").innerHTML = "Email already exists";
                    else if (result == "success") {
                        swal({
                            title: "Successfully signed up",
                            text: "You have successfully registered",
                            icon: "success",
                        }).then(function () {
                            window.location.href = "http://localhost:9192/auctionhouse/login";
                        });
                    }
                    console.log(result);
                }
            });
        }

    </script>
</body>

</html>