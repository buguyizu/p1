require(['jquery', 'bootstrap'], function($, bootstrap) {
	$(document).ready(function() {
		if (typeof pageLoad === 'function') {
			pageLoad();
		}
	});
	this.logout = function() {
		/*
		$.post(url, d, function(data) {
			console.log(data);
		});*/
		$('#f').submit();
	}
});