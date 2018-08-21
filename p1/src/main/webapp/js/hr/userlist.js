require(['jquery', 'bootstrap', 'datatables.net', 'datatables.bs', 'base'], function($, bootstrap, datatables, db, base) {

/*{
	"emptyTable": "表格无数据",
	"info": "总件数：_TOTAL_ 总页数：_PAGES_",
	"infoEmpty": "显示无数据",
	"lengthMenu": "每页件数： _MENU_",
	"search": "搜索：",
	"processing": "处理中...",
	"paginate": {
		"first": "|&lt;",
		"last": "&gt;|",
		"next": "&raquo;",
		"previous": "&laquo;"
	},
	"zeroRecords": "无匹配数据"
}*/
	
	var dt_text = [
		decodeURIComponent("%E8%A1%A8%E6%A0%BC%E6%97%A0%E6%95%B0%E6%8D%AE"),
		decodeURIComponent("%E6%80%BB%E4%BB%B6%E6%95%B0%EF%BC%9A_TOTAL_%20%E6%80%BB%E9%A1%B5%E6%95%B0%EF%BC%9A_PAGES_"),
		decodeURIComponent("%E6%98%BE%E7%A4%BA%E6%97%A0%E6%95%B0%E6%8D%AE"),
		decodeURIComponent("%E6%AF%8F%E9%A1%B5%E4%BB%B6%E6%95%B0%EF%BC%9A%20_MENU_"),
		decodeURIComponent("%E6%90%9C%E7%B4%A2%EF%BC%9A"),
		decodeURIComponent("%E5%A4%84%E7%90%86%E4%B8%AD..."),
		decodeURIComponent("%E6%97%A0%E5%8C%B9%E9%85%8D%E6%95%B0%E6%8D%AE")
	],
	dt_lan = {
		"emptyTable": dt_text[0],
		"info":       dt_text[1],
		"infoEmpty":  dt_text[2],
		"lengthMenu": dt_text[3],
		"search":     dt_text[4],
		"processing": dt_text[5],
		"paginate": {
			"first": "|&lt;",
			"last": "&gt;|",
			"next": "&raquo;",
			"previous": "&laquo;"
		},
		"zeroRecords": dt_text[6]
	},
	dt_option = {
		"columnDefs": [
			{ "searchable": false, "targets": "_all" }
		],
		"language": dt_lan,
		"destroy": true,
		"searching": false,
		"processing": true,
		"serverSide": true,
		"ajax": {
			"type": "POST"
		},
		"lengthMenu": [ 10, 25, 50 ]
	},
	table = null;

	$(document).ready(function() {
		pageLoad();
	});

    function pageLoad() {
        //https://datatables.net/manual/server-side
        //https://datatables.net/reference/option/language
        //https://datatables.net/reference/event/error
        $.fn.dataTable.ext.errMode = 'none';
        $.extend( $.fn.dataTable.defaults, dt_option);

        $('#t').on('error.dt', function ( e, settings, techNote, message ) {
            console.log( 'An error has been reported by DataTables: ', message );
            alert(base.msgs.session_001i);
            location.reload(true);
        });
        
        $('#d1').on('show.bs.modal', function (event) {
            var tr = $(event.relatedTarget).closest('tr'), username = tr.data('username'), version = tr.data('version'), modal = $(this);
            $('#c_username').val(username);
            $('#c_version').val(version);

            UserDwr.getUser(username, {
                callback: function(str) {
                    $('[name="username"]').val(username);
                    $('[name="cd"]').val(str['cd']);
                    $('[name="name"]').val(str['name']);
                    $('[name="gender"][value="' + str['gender'] + '"]').parent().trigger("click");
                    $('[name="department"]').val(str['department']);
                    $('[name="comment"]').val(str['comment']);
                    modal.data("version", str['version']).data("serial", modal.find('input, select, textarea').serialize());
                }, errorHandler:function(message) {
                    alert(message);
                }
            });
        });
        $('#d2').on('show.bs.modal', function (event) {
            var tr = $(event.relatedTarget).closest('tr'), username = tr.data('username'), version = tr.data('version'), modal = $(this);
            $('#c_username').val(username);
            $('#c_version').val(version);
            modal.find('.label').text(username);

            UserDwr.logged(username, function(str) {
                if (str)
                    $("#d2Msg").text(base.msgs.user_001w);
                else
                    $("#d2Msg").text(base.msgs.user_001c);
            });
        });
        $('#d1 .alert, #d2 .alert').hide();
        $('#d1, #d2').on('hidden.bs.modal', function (event) {
        	if ($(this).is('#d1'))
        		$(this).find(':radio').prop('checked', false).closest('label').removeClass('active');
            $(this).find('.alert').hide();
            table.ajax.reload();
        });
        $('.btn-primary:first').click(searchData);
        $('[type=reset]').click(function() {
        	$('form :radio').closest('label').removeClass('active');
        });
        $('#d1 .btn-primary').click(updateUser);
        $('#d2 .btn-primary').click(removeUser);
        //$('#h,#d').hide();
    }
    function searchData() {
        //$('#h,#d').show();
        table = $('#t')
/*                .on('error.dt', function ( e, settings, techNote, message ) {
                    console.log( 'An error has been reported by DataTables: ', message );
                    alert(base.msgs.session_001i);
                    location.reload(true);
                })
*/
          .DataTable({
            "order": [[ 0, "asc" ]],
            "ajax": {
                "url": "./search",
                "data": function ( d ) {
                    d["p.username"]     = $("[name='p.username']").val();
                    d["p.cd"]           = $("[name='p.cd']").val();
                    d["p.name"]         = $("[name='p.name']").val();
                    d["p.department"]   = $("[name='p.department']").val();
                    d["p.gender"]       = $(":checked[name='p.gender']").val();
                    d["p.status"]       = $(":checked[name='p.status']").val();
                    d["dp.draw"]        = d["draw"];
                    d["dp.length"]      = d["length"];
                    d["dp.start"]       = d["start"];
                    d["dp.orderColumn"] = d["order"][0]["column"];
                    d["dp.orderDir"]    = d["order"][0]["dir"];
                    delete d["-"];
                    //delete d["draw"];
                    //delete d["length"];
                    //delete d["start"];
                    //delete d["order"];
                    //delete d["search"];
                },
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                { "data": "0" },
                { "data": "1" },
                { "data": "2" },
                { "data": "3" },
                { "data": "4" },
                { "data": "5" },
                {
                    "orderable": false,
                    "data":      "0",
                    //https://datatables.net/reference/option/columns.render
                    "render":    function ( data, type, full, meta ) {
                        return "";
                    }
                }
            ],
            "rowCallback": function( row, data, index ) {
                $("#temp .btn-group").clone().appendTo($('td:eq(6)', row));
                $(row).data("username", data['0']).data("version", data['6']).dblclick(function() {
                    $(this).find("button:eq(0)").trigger('click')
                });
            }
        });

        $('#t tbody').on( 'mouseenter', 'tr', function () {
            $('#t tr').removeClass( 'highlight' );
            $(this).addClass( 'highlight' );
        }).on( 'mouseleave', 'tr', function () {
            $('#t tr').removeClass( 'highlight' );
        });
    }
    function removeUser() {
        var u = $('#c_username').val();
        UserDwr.removeUser(u, $('#c_version').val(), {
            callback: function(str) {
                if (str['cd'] == undefined) {
                    alert(base.msgs.session_001i);
                    location.reload(true);
                } else {
                    $('#d2 .alert span').text(str['msg']);
                	$('#d2 .alert').show();
                    if (str['cd'] == '0') {
                    	setTimeout(function() {
                    		$('#d2').modal('hide');
                    	}, 1000);
                    }
                }
            }, errorHandler: function(message) {
                alert(message);
                $('#d2').modal('hide');
            }
        });
    }
    function updateUser() {
    	var origin = $('#d1').data("serial"), serial = $('#d1').find('input, select, textarea').serialize();
    	if (origin == serial) {
            $('#d1 .alert span').text(base.msgs.user_004i);
        	$('#d1 .alert').show();
        	setTimeout(function() {
        		$('#d1 .alert').fadeOut();
        	}, 1000);
    		return;
    	}

        UserDwr.updateUser({
            "username":   $("[name='username']").val(),
            "cd":         $("[name='cd']").val(),
            "name":       $("[name='name']").val(),
            "gender":     $(":checked[name='gender']").val(),
            "department": $("[name='department']").val(),
            "comment":    $("[name='comment']").val(),
            "version":    $('#d1').data("version")
        }, {
            callback: function(str) {
                if (str['cd'] == undefined) {
                    alert(base.msgs.session_001i);
                    location.reload(true);
                } else {
                    $('#d1 .alert span').text(str['msg']);
                	$('#d1 .alert').show();
                    if (str['cd'] == '0') {
                    	setTimeout(function() {
                    		$('#d1').modal('hide');
                    	}, 1000);
                    }
                }
            }, errorHandler: function(message) {
                alert(message);
                $('#d1').modal('hide');
            }
        });
    }
});