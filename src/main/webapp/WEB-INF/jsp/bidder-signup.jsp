<html>

<head>
    <title>Javascript Login Form Validation</title>
    <!-- Include CSS File Here -->
    <link rel="stylesheet" href="/css/style.css" />
    <!-- Include JS File Here -->

</head>

<body>
    <div class="container">
        <div class="main">
            <h2>Sign Up for bidder</h2>
            <form id="signup" method="post" name="signup" action="http://localhost:9192/bidder/signup">
                <label>Email :</label>
                <input type="email" name="bidderEmail" id="bidderEmail" />
                <label>First name :</label>
                <input type="text" name="bidderFirstName" id="bidderFirstName" />
                <label>LastName :</label>
                <input type="text" name="bidderLastName" id="bidderLastName" />
                <label>Contact :</label>
                <input type="text" name="bidderContact" id="bidderContact" />
                <label>Password :</label>
                <input type="password" name="bidderPassword" id="bidderPassword" />
                <input type="submit" value="SignUp">
            </form>
            <!-- <span><b class="note">Note : </b>For this demo use following username and password. <br /><b
                    class="valid">User Name : Formget<br />Password : formget#123</b></span> -->
        </div>
    </div>
</body>

</html>