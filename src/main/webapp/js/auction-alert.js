var stompClient = null;

var socket = new SockJS('/alertsocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {

	stompClient.subscribe('/alert/' + $("#navbarDropdownMenuLink").text().trim(), function(result) {
		alert(result.body);
		//$("#AllProducts").load(location.href + " #AllProducts");
		$("#main").load(location.href + " #main");
	});
});

function createAlert(eventNo) {
	$.ajax({
		type: "POST",
		url: "http://localhost:9192/public/createNotification?eventNo="
			+ eventNo,
		contentType: "application/json",
		async: false,
		success: function(result) {
			alert(result)
		}
	});
}