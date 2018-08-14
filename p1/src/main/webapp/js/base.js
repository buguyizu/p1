define(['jquery', 'sse'], function($, sse) {
	
	function getMsg() {
		if ($('#msgs').length) {
			var msg = {}
			$('#msgs dt').each(function(index, element) {
				var t = $(element).text(), d = $(element).next().text()
				msg[t] = d
			})

			$('#msgs').remove()
			return msg
		}
	}

	function logout() {
		$('#logout form').submit()
	}

	$('#logout a').click(logout)

// can not call listen here while access homepage
//	sse.listen();
//	window.addEventListener("load", sse.listen, false);
//	$(window).load(function() {
//		sse.listen();
//	});

	return {
		msgs   : getMsg(),
		listen : sse.listen,
		notify : sse.notify,
		other  : function() {}
	};
});