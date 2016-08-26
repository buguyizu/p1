$(document).ready(function() {
	try {
		pageLoad();
	} catch (e) {
		console.log(e);
	}
});
function logout() {
	/*
	$.post(url, d, function(data) {
		console.log(data);
	});*/
	$('#f').submit();
}