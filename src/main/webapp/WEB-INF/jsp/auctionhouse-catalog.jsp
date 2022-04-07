<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Catalog</title>

        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body>
        <div class="container">

            <div class="container">
                <div class="jumbotron text-center bg-success text-white">
                    <h1>Auction catalog</h1>
                    <p>Create and delete the items to be added in the auction</p>
                </div>
                <!-- <button id="addRow" type="button" class="btn btn-success">Add another product</button> -->
                <div class="text-right">
                    <button type="button" id="addRow" class="btn btn-primary">Add another product</button>
                </div>
                <div><br></div>
                <!-- <svg xmlns="http://www.w3.org/2000/svg" id="addRow" style="size: 1cm;" type="button" viewBox="0 0 24 24"><path d="M12,2A10,10,0,1,0,22,12,10,10,0,0,0,12,2Zm0,18a8,8,0,1,1,8-8A8,8,0,0,1,12,20Zm4-9H13V8a1,1,0,0,0-2,0v3H8a1,1,0,0,0,0,2h3v3a1,1,0,0,0,2,0V13h3a1,1,0,0,0,0-2Z"/></svg> -->
                <!-- <form method="post" name="catalog" enctype="multipart/form-data" id="catalogForm" action="/auctionhouse/catalog" > -->
                <form method="POST" enctype="multipart/form-data" action="/auctionhouse/catalog">
                    <!-- method="post" enctype="multipart/form-data" action="/auctionhouse/addauction" -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="inputFormRow">
                                <div class="input-group mb-3">
                                    <!--this is input just in the beginning not after we add-->
                                    <!-- <input type="text" name="title[]" class="form-control m-input" placeholder="Enter title" autocomplete="off"> -->


                                    <div class="form-group col-md-3">
                                        <label name="itemImage">Select Image of the Item</label>
                                        <input type="file" class="form-control" name="itemImage" id="itemImage"
                                            placeholder="Select image of the item" required />
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label name="itemName">Product/Item name:</label>
                                        <input type="text" class="form-control" id="itemName" name="itemName"
                                            placeholder="Enter the name of the product/object/entity/Item" required />
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label name="itemStartBid">Enter the initial bid:</label>
                                        <input type="number" class="form-control" name="itemStartBid" id="itemStartBid"
                                            placeholder="enter the minimum bidding amount" required />
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label name="itemDesc">Product/Item description:</label>
                                        <input type="text" class="form-control" id="itemDesc" name="itemDesc"
                                            placeholder="Describe the product/object/entity/Item" required />
                                        <!-- <textarea rows="4" cols="50" name="itemDesc"  type="text" class="form-control" id="itemDesc" form="catalogForm" placeholder="Describe the product/object/entity/Item">
                                        </textarea> -->
                                    </div>

                                    <!-- <div class="text-right"> -->
                                    <div class="input-group">
                                        <button id="removeRow" type="button" class="btn btn-danger">remove this
                                            product</button>
                                    </div>
                                </div>
                            </div>

                            <div id="newRow"></div>
                            <button type="submit" class="btn btn-success">Save all the catalog item details</button>
                            <!-- the new row div above will replace the html tags with the script below -->
                        </div>
                    </div>
                    <!-- </form> -->
                </form>
            </div>

            <script type="text/javascript">
                // add row
                $("#addRow").click(function () {
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
                $(document).on('click', '#removeRow', function () {
                    $(this).closest('#inputFormRow').remove();
                });
            </script>
    </body>

    </html>