$(function() {
	$('span[onload]').trigger('onload');
});

function countdownTimeStart(startTime, target) {

	let countDownDate = new Date(startTime).getTime();
	//let countDownDate = new Date("Apr 11, 2022 " + startTime).getTime();

	// Update the count down every 1 second
	let x = setInterval(
		function() {

			var now = new Date().getTime();
			distance = countDownDate - now;
			let hours = Math
				.floor((distance % (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60));
			let minutes = Math.floor((distance % (1000 * 60 * 60))
				/ (1000 * 60));
			let seconds = Math
				.floor((distance % (1000 * 60)) / 1000);

			let updatedTime = hours + "h " + minutes + "m "
				+ seconds + "s ";

			if (document.getElementsByClassName(target)[0] != undefined) {
				document.getElementsByClassName(target)[0].innerText = updatedTime;
			}


			if (distance < 0) {
				clearInterval(x);
			}
		}, 1000);
}