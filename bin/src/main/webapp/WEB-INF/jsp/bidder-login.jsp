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
            <h2>Log In for Bidder </h2>
            <form id="form_id" method="post" name="myform" action="http://localhost:9192/authenticate">
                <label>Email :</label>
                <input type="email" name="userName" id="userName" />
                <label>Password :</label>
                <input type="password" name="password" id="password" />
                <input type="submit" value="Submit">
            </form>
            <!-- <span><b class="note">Note : </b>For this demo use following username and password. <br /><b
                    class="valid">User Name : Formget<br />Password : formget#123</b></span> -->
        </div>
    </div>
</body>

</html>