var stompClient = null;

var socket = new SockJS('/bidsocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {

	stompClient.subscribe('/bid/RefreshFeed', function(result) {
		$("#live-container").load(location.href + " #live-container");
	});
});



function updateBid(liveBidId, bidderId, bidValue, target) {
	$.ajax({
		type: "POST",
		url: "http://localhost:9192/public/PlaceBid?id=" + liveBidId
			+ "&bidderId=" + bidderId + "&bidValue=" + bidValue,
		contentType: "application/json",
		async: false,
		success: function(result) {
			$("#" + target).load(location.href + " #" + target);
			stompClient.send("/app/UpdateLiveBid", {}, {});
		}
	});
}

function closeBid(liveBidId, bidderId, bidValue, target) {
	if (confirm('Are you sure you want to close this bid?')) {
		$.ajax({
			type: "POST",
			url: "http://localhost:9192/public/CloseBid?id="
				+ liveBidId + "&bidderId=" + bidderId
				+ "&bidValue=" + bidValue,
			contentType: "application/json",
			async: false,
			success: function(result) {
				$("#" + target).load(location.href + " #" + target);
				stompClient.send("/app/UpdateLiveBid", {}, {});
			}
		});
	}
}

function checkoutCartItems() {
	if (confirm('Checkout all items?')) {
		$.ajax({
			type: "POST",
			url: "http://localhost:9192/public/bidder/checkout",
			contentType: "application/json",
			async: false,
			success: function(result) {
				$("#cart").load(location.href + " #cart");
			}
		});
	}
}

function setSecondaryStatus(liveBidId, bidderId, bidValue, status, target) {
	$.ajax({
		type: "POST",
		url: "http://localhost:9192/public/setSecodaryStatus?id=" + liveBidId
			+ "&bidderId=" + bidderId + "&bidValue=" + bidValue + "&status=" + status,
		contentType: "application/json",
		async: false,
		success: function(result) {
			$("#" + target).load(location.href + " #" + target);
			stompClient.send("/app/UpdateLiveBid", {}, {});
		}
	});
}
