(function($) {
	$.widget("ui.mark", {
		version: "0.1",
		options: {
			//right, wrong
			type: 'r'
        },
        _init: function() {
        	var i = 'i' + (new Date()).getTime();
        	if (this.options.type == 'r') {
        		this.element.prepend('<span class="rMark" id="' + i + '"></span>');
        	} else if (this.options.type == 'w') {
        		this.element.prepend('<span class="wMark" id="' + i + '"></span>');
        	}
        	setTimeout(function() {
	        	$("#" + i).remove();
	        }, 1000);
        },
        destroy: function() {
        	$('.rMark, .wMark').remove();
        },
        r: function() {
        },
        w: function() {
        }
    });
})(jQuery);