<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Auction events display</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="/css/dashboard.css" />
</head>
<body>
<!-- Navbar section -->
<nav class="navbar navbar-expand-sm navbar-light bg-white border-bottom"> <a class="navbar-brand ml-2 font-weight-bold" href="#"><span id="burgundy">Proxi</span><span id="orange">Bid</span></a> <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor" aria-controls="navbarColor" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
  <div class="collapse navbar-collapse" id="navbarColor">
      <ul class="navbar-nav">
          <!-- <li class="nav-item rounded bg-light search-nav-item"><input type="text" id="search" class="bg-light" placeholder="Search bread, cakes, desserts"><span class="fa fa-search text-muted"></span></li>
          <li class="nav-item"><a class="nav-link" href="#"><span class="fa fa-user-o"></span><span class="text">Login</span></a> </li>
          <li class="nav-item "><a class="nav-link" href="#"><span class="fa fa-shopping-cart"></span><span class="text">Cart</span></a> </li> -->
      </ul>
  </div>
</nav>
<div class="filter"> <button class="btn btn-default" type="button" data-toggle="collapse" data-target="#mobile-filter" aria-expanded="true" aria-controls="mobile-filter">Filters<span class="fa fa-filter pl-1"></span></button>
</div>
<div id="mobile-filter">
  <!-- <p class="pl-sm-0 pl-2"> Home | <b>All Breads</b></p> -->
  <div class="border-bottom pb-2 ml-2">
      <h4 id="burgundy">Categories</h4>
  </div>
  <div class="py-2 border-bottom ml-3">
      <h6 class="font-weight-bold">Categories</h6>
      <div id="orange"><span class="fa fa-minus"></span></div>
      <!-- <form>
          <div class="form-group"> <input type="checkbox" id="artisan"> <label for="artisan">Jewellery</label> </div>
          <div class="form-group"> <input type="checkbox" id="breakfast"> <label for="breakfast">Groceries</label> </div>
          <div class="form-group"> <input type="checkbox" id="healthy"> <label for="healthy">Vehicles</label> </div>
      </form> -->
  </div>
  <!-- <div class="py-2 border-bottom ml-3">
      <h6 class="font-weight-bold">Accompainments</h6>
      <div id="orange"><span class="fa fa-minus"></span></div>
      <form>
          <div class="form-group"> <input type="checkbox" id="tea"> <label for="tea">Tea Cakes</label> </div>
          <div class="form-group"> <input type="checkbox" id="cookies"> <label for="cookies">Cookies</label> </div>
          <div class="form-group"> <input type="checkbox" id="pastries"> <label for="pastries">Pastries</label> </div>
          <div class="form-group"> <input type="checkbox" id="dough"> <label for="dough">Cookie Dough</label> </div>
          <div class="form-group"> <input type="checkbox" id="choco"> <label for="choco">Chocolates</label> </div>
      </form>
  </div>
  <div class="py-2 ml-3">
      <h6 class="font-weight-bold">Top Offers</h6>
      <div id="orange"><span class="fa fa-minus"></span></div>
      <form>
          <div class="form-group"> <input type="checkbox" id="25off"> <label for="25">25% off</label> </div>
          <div class="form-group"> <input type="checkbox" id="5off"> <label for="5off" id="off">5% off on artisan breads</label> </div>
      </form>
  </div> -->
</div>
<!-- Sidebar filter section -->
<section id="sidebar">
  <!-- <p> Home | <b>All Breads</b></p> -->
  <div class="border-bottom pb-2 ml-2">
      <h4 id="burgundy">Filters</h4>
  </div>
  <div class="py-2 border-bottom ml-3">
      <h6 class="font-weight-bold">Categories</h6>
      <hr>
      <div id="orange"><span class="fa fa-minus"></span></div>
      <form action="/bidder/dashboard/" method="POST">
        <c:forEach var="category" items="${categories}">
          <div class="form-group"> <input type="checkbox"  name="checkbox" value="${category.categoryName}"> <label for="${category.categoryName}">${category.categoryName}</label> </div>
        </c:forEach>
        <button class="btn btn-warning" type="submit" >search results</button>
      </form>
  </div>
  <!-- <div class="py-2 border-bottom ml-3">
      <h6 class="font-weight-bold">Accompainments</h6>
      <div id="orange"><span class="fa fa-minus"></span></div>
      <form>
          <div class="form-group"> <input type="checkbox" id="tea"> <label for="tea">Tea Cakes</label> </div>
          <div class="form-group"> <input type="checkbox" id="cookies"> <label for="cookies">Cookies</label> </div>
          <div class="form-group"> <input type="checkbox" id="pastries"> <label for="pastries">Pastries</label> </div>
          <div class="form-group"> <input type="checkbox" id="dough"> <label for="dough">Cookie Dough</label> </div>
          <div class="form-group"> <input type="checkbox" id="choco"> <label for="choco">Chocolates</label> </div>
      </form>
  </div> -->
  <!-- <div class="py-2 ml-3">
      <h6 class="font-weight-bold">Top Offers</h6>
      <div id="orange"><span class="fa fa-minus"></span></div>
      <form>
          <div class="form-group"> <input type="checkbox" id="25off"> <label for="25">25% off</label> </div>
          <div class="form-group"> <input type="checkbox" id="5off"> <label for="5off" id="off">5% off on artisan breads</label> </div>
      </form>
  </div> -->
</section>
<!-- products section -->
<section id="products">
  <div class="container">
      <!-- <div class="d-flex flex-row"> 
          <div class="text-muted m-2" id="res">Showing 44 results</div>
          <div class="ml-auto mr-lg-4">
              <div id="sorting" class="border rounded p-1 m-1"> <span class="text-muted">Sort by</span> <select name="sort" id="sort">
                      <option value="popularity"><b>Popularity</b></option>
                      <option value="prcie"><b>Price</b></option>
                      <option value="rating"><b>Rating</b></option>
                  </select> </div>
          </div>
      </div> -->
     <c:forEach var="auction" items="${auctions}">
      <div class="card mb-3" style="max-width: fit-content;">
        <div class="row no-gutters">
          <div class="col-md-4">
            <img src="https://image.shutterstock.com/image-vector/graph-paper-coordinate-grid-squared-600w-1917481553.jpg" class="card-img" alt="...">
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">${auction.eventNo}.${auction.eventTitle}</h5>
              <p class="card-text">${auction.description} This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
              <p class="card-text"><small class="text-muted">Starts at ${auction.startTime} ${auction.startDate}</small></p>
              <a href="/bidder/event/${auction.eventNo}" class="btn btn-primary">Go somewhere</a>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>  

    <!-- <div class="card mb-3" style="max-width: fit-content;">
        <div class="row no-gutters">
          <div class="col-md-4">
            <img src="https://image.shutterstock.com/image-vector/graph-paper-coordinate-grid-squared-600w-1917481553.jpg" class="card-img" alt="...">
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
              <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="card mb-3" style="max-width: fit-content;">
        <div class="row no-gutters">
          <div class="col-md-4">
            <img src="https://image.shutterstock.com/image-vector/graph-paper-coordinate-grid-squared-600w-1917481553.jpg" class="card-img" alt="...">
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
              <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
            </div>
          </div>
        </div>
      </div>
      <div class="card mb-3" style="max-width: fit-content;">
        <div class="row no-gutters">
          <div class="col-md-4">
            <img src="https://image.shutterstock.com/image-vector/graph-paper-coordinate-grid-squared-600w-1917481553.jpg" class="card-img" alt="...">
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
              <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
            </div>
          </div>
        </div>
      </div> -->
          <!-- <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
              <div class="card"> <img class="card-img-top" src="https://images.pexels.com/photos/3085146/pexels-photo-3085146.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500">
                  <div class="card-body">
                      <h5><b>Bagels</b> </h5>
                      <div class="d-flex flex-row my-2">
                          <div class="text-muted">₹35/piece</div>
                          <div class="ml-auto"> <button class="border rounded bg-white sign"><span class="fa fa-plus" id="orange"></span></button> <span class="px-sm-1">1 pc</span> <button class="border rounded bg-white sign"><span class="fa fa-minus" id="orange"></span></button> </div>
                      </div> <button class="btn w-100 rounded my-2">Add to cart</button>
                  </div>
              </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
              <div class="card"> <img class="card-img-top" src="https://images.pexels.com/photos/1448665/pexels-photo-1448665.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500">
                  <div class="card-body">
                      <h5><b>White Bread</b> </h5>
                      <div class="d-flex flex-row my-2">
                          <div class="text-muted">₹80/loaf</div>
                          <div class="ml-auto"> <button class="border rounded bg-white sign"><span class="fa fa-plus" id="orange"></span></button> <span class="px-sm-1">1 loaf</span> <button class="border rounded bg-white sign"><span class="fa fa-minus" id="orange"></span></button> </div>
                      </div> <button class="btn w-100 rounded my-2">Add to cart</button>
                  </div>
              </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
              <div class="card"> <img class="card-img-top" src="https://images.pexels.com/photos/461060/pexels-photo-461060.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500">
                  <div class="card-body">
                      <h5><b>Baguette</b> </h5>
                      <div class="d-flex flex-row my-2">
                          <div class="text-muted">₹160/piece</div>
                          <div class="ml-auto"> <button class="border rounded bg-white sign"><span class="fa fa-plus" id="orange"></span></button> <span class="px-sm-1">1 pc</span> <button class="border rounded bg-white sign"><span class="fa fa-minus" id="orange"></span></button> </div>
                      </div> <button class="btn w-100 rounded my-2">Add to cart</button>
                  </div>
              </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
              <div class="card"> <img class="card-img-top" src="https://images.pexels.com/photos/209206/pexels-photo-209206.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500">
                  <div class="card-body">
                      <h5><b>Masala Bun</b> </h5>
                      <div class="d-flex flex-row my-2">
                          <div class="text-muted">₹85/piece</div>
                          <div class="ml-auto"> <button class="border rounded bg-white sign"><span class="fa fa-plus" id="orange"></span></button> <span class="px-sm-1">1 pc</span> <button class="border rounded bg-white sign"><span class="fa fa-minus" id="orange"></span></button> </div>
                      </div> <button class="btn w-100 rounded my-2">Add to cart</button>
                  </div>
              </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
              <div class="card d-relative"> <img class="card-img-top" src="https://images.pexels.com/photos/3570/morning-breakfast-croissant.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500">
                  <div class="card-body">
                      <h5><b>Croissant</b> </h5>
                      <div class="rounded bg-white discount" id="orange">10% off</div>
                      <div class="d-flex flex-row my-2">
                          <div class="text-muted price"><del>₹55</del>₹45/piece</div>
                          <div class="ml-auto"> <button class="border rounded bg-white sign"><span class="fa fa-plus" id="orange"></span></button> <span>1pc</span> <button class="border rounded bg-white sign"><span class="fa fa-minus" id="orange"></span></button> </div>
                      </div> <button class="btn w-100 rounded my-2">Add to cart</button>
                  </div>
              </div>
          </div> -->
  </div>
</section>
<!-- <script> 
  $("input[name='checkbox']").click(function(e){ 
  if (e.target.checked) { 
  localStorage.checked = true; 
  } else { 
  localStorage.checked = false; 
  } 
  }) 
   
  $( document ).ready(function() { 
    <c:forEach var="category" items="${categories}">
      document.querySelector("input[name='checkbox']").checked = localStorage.checked 
  </c:forEach>
   
  }); 
  </script>  -->
</body>
</html>