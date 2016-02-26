function logout(url, d) {
	$.post(url, d, function(data) {
		console.log(data);
	});
}