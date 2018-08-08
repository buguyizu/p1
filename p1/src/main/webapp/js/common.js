require(['jquery', 'bootstrap'], function($, bootstrap) {
	$(document).ready(function() {
		if (typeof pageLoad === 'function') {
			pageLoad();
		}
	});
});