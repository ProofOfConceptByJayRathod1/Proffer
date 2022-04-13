var stompClient = null;

var socket = new SockJS('/bidsocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {
	stompClient.subscribe('/bid/RefreshFeed', function(greeting) {
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
		success: function(data) {
			$("#" + target).load(location.href + " #" + target);
			stompClient.send("/app/UpdateLiveBid", {}, {});
		}
	});
}