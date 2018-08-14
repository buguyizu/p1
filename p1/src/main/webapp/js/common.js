require(['jquery', 'bootstrap', 'base'], function($, bs, base) {
	$(document).ready(function() {
		if (typeof pageLoad === 'function') {
			pageLoad();
		}
	});
});