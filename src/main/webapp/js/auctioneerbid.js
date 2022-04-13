var stompClient = null;

var socket = new SockJS('/bidsocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {

	//console.log('Connected: ' + frame);
	stompClient.subscribe('/bid/returnbid', function(greeting) {
		//update to bidder side
		updateBid(JSON.parse(greeting.body));
	});

	stompClient.subscribe('/bid/placebid', function(showbid) {
		showBid(JSON.parse(showbid.body));
	});

	stompClient.subscribe('/bid/win', function(winbid) {
		winBid(JSON.parse(winbid.body));
	});
});

function winBid(win) {
	//console.log(win.bidValue);
	var bu_id = "#" + win.itemId + "b";
	$(bu_id).html("bid completed");
}
function sendName() {
	stompClient.send("/app/hello", {}, JSON.stringify({ 'bidValue': $("#name").val() }));
}

function updateBid(message) {
	//console.log(message);

	/*$.ajax({
		url: "http://localhost:9192/public/GetCurrentBid",
		data: message,
		success: function(result) {
			console.log(result);
		}
	});*/
	let b_id = "#" + message.itemId + "b";
	$(b_id).html(message.rbid);
}

function finish(iId, eventno, itemId) {
	let div_id = "#" + itemId + "auction";
	let content = $(div_id).text();

	let amt = content.substring(8, content.indexOf(" by "));
	let bidder = content.substring(content.indexOf(" by ") + 4);

	//save bid winner
	stompClient.send("/app/hello2", {}, JSON.stringify({ bidderId: bidder, eventNo: Number(eventno), itemId: iId, amount: Number(amt) }));
}
function showBid(showbid) {
	console.log("showbid", showbid.bidderEmail, $("#b_id").val());
	var div_id = "#" + showbid.itemId + "c";
	var div_id_auctionhouse = "#" + showbid.itemId + "auction";
	var butt_accept = "#" + showbid.itemId + "winbid";
	$(div_id).text("highbid:" + showbid.bidValue + " by " + showbid.bidderEmail);
	$(div_id_auctionhouse).text("highbid:" + showbid.bidValue + " by " + showbid.bidderEmail);
	$(butt_accept).show();

	var bu_id = "#" + showbid.itemId + "b";
	var newbid = showbid.bidValue + 10;
	$(bu_id).html(newbid);

	if ($("#b_id").val() == showbid.bidderEmail) {

		$(bu_id).prop('disabled', true);
	}
	else {
		$(bu_id).prop('disabled', false);
	}

}
function trigger(id) {
	var v = "#" + id + "t";
	var value = $(v).val();// get value from input
	stompClient.send("/app/hello", {}, JSON.stringify({ 'bidValue': value, 'itemId': id }));

}

function highbid(id, eno, bidvalue, bidderemail) {
	//console.log("itemid",id,"eno",eno,"bidvalue",bidvalue,"bidderemail",bidderemail);
	//send to other bodders and auctioneer
	stompClient.send("/app/hello1", {}, JSON.stringify({ 'bidValue': bidvalue, 'itemId': id, 'eventNo': eno, 'bidderEmail': bidderemail }));

}

$(function() {
	$("#bid").click(function(e) {
		e.preventDefault();
		stompClient.send("/app/hello", {}, JSON.stringify({ 'bidValue': $("#bid-value").val() }));
	});


});