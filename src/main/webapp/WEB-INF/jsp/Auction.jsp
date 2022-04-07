
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="/auctionhouse/addauction" >
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="text" class="form-control" name="eventTitle" id="eventTitle" placeholder="EventTitle">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" placeholder="dd/mm/yyyy">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="startTime">Start Time</label>
                <input type="time" class="form-control" name="startTime" id="startTime" >
            </div>
            <div class="form-group col-md-6">
                <label for="duration">Duration</label>
                <input type="number" class="form-control" id="duration" name="duration" placeholder="10">
            </div>
        </div>
        <div class="form-group">
            <label for="description">Description</label><br>
            <input type="text" class="form-control" id="description" name="description" >
        </div>
        <div class="form-group">
            <label for="image">Image</label><br>
            <input type="file" class="form-control" id="imgName" name="imgName" >
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="inputState">Catagory</label>
                <select id="category" name="category" class="form-control">
                    <option selected>Choose...</option>
                    <option>Arts and Antiques</option>
                    <option>Jwellery</option>
                    <option>Vehicales </option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="inputbid">Bid Start</label>
                <input type="number" class="form-control" id="currBidValue" name="currBidValue">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
    </form> 
</body>
</html>