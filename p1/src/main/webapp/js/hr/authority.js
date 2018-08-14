require(['jquery', 'bootstrap', 'base'], function($, bs, base) {

	$(document).ready(function() {
		$("#clear").on('click', clearInput);
		$("#username").on('keyup', getUsers).on('mouseover',
			function() {
				$(this).focus();
			});
		$("#users").on('change', getAuthorities).on('mouseout',
			function() {
				$("#users option").css('background-color', 'initial');
			});
		$("#objs").siblings('a').click(getAuthorities);
	});

	function getUsers() {
		if ($("#username").val().trim() == '') {
			return false;
		}
		$(".modal").modal('show');
		$("#users option").remove();
		UserDwr.users($("#username").val(), {
			callback : function(ar) {
				$.each(ar, function(i, val) {
					$("#users").append($('<option></option>').attr('value', val).text(val));
				});
				$("#users option").on('mouseover',
					function() {
						$("#users option").css('background-color', 'initial');
						$(this).css('background-color', '#e7e7e7');
					});
				$(".modal").modal('hide');
			},
			errorHandler : function(message) {
				alert(message);
			}
		});
	}

	function clearInput() {
		$("#users option, #op a").remove();
		$("#objs").text('');
		$('#username').val('').focus();
	}

	function getAuthorities() {

		if ($("#users").val() == null) {
			return;
		}
		if(!$('.modal').hasClass('in')) {
			$(".modal").modal('show');
		}
		$("#objs").text($("#users").val());
		$("#op a").remove();

		UserDwr.authorities($("#users").val(), {
			callback: function(ar) {
				$.each(ar, function(k, v) {
					var a = $('<a href="javascript:void(0);"></a>').text(k);
					if (v == '1') {
						a.addClass('list-group-item-success').append('<span class="glyphicon-minus-sign"></span><span class="glyphicon-check"></span>').appendTo('#op');
					} else if (v == '0') {
						a.addClass('list-group-item-danger').append('<span class="glyphicon-plus-sign"></span>').appendTo('#op');
					} else if (v == '-1') {
						var b = a.clone();
						a.addClass('list-group-item-success').append('<span class="glyphicon-minus-sign"></span>').appendTo('#op');
						b.addClass('list-group-item-danger').append('<span class="glyphicon-plus-sign"></span>').appendTo('#op');
					} else {
						a.addClass('disabled').append('<span class="glyphicon-check"></span>').appendTo('#op');
					}
				});
				$('#op a').addClass('list-group-item');
				$('#op a span').addClass('glyphicon')
						.not(".glyphicon-check").addClass('pull-right');
				$('#op a.list-group-item-success').click(function() {
					operate($(this).text(), -1);
				});
				$('#op a.list-group-item-danger').click(function() {
					operate($(this).text(), 1);
				});
				$(".modal").modal('hide');
			},
			errorHandler : function(message) {
				alert(message);
			}
		});
	}

	function operate(authority, op) {
		if ($("#users").val() != null) {
			if ($("#users").val().length > 1 && !confirm(base.msgs.authority_001c)) {
				return;
			}
			$(".modal").modal('show');
			UserDwr.operate($("#users").val(), authority, op, {
				callback : function(res) {
					if ($("#users").val().length == 1) {
						getAuthorities();
					} else {
						$(".modal").modal('hide');
						$(".glyphicon-ok").text(res.count);
						$(".glyphicon-ok").fadeIn(1000);
						$(".glyphicon-ok").fadeOut(1000);
					}
				}
			});
		}
	}
});