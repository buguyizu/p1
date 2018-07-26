require(['jquery', 'bootstrap', 'datatables.net', 'datatables'], function($, bootstrap, dn, datatables) {

    this.dt_lan = JSON.parse($('#dt-lan').text());
    this.msgs = {
		session_001i: $('#msg1').text(),
		user_001w: $('#msg2').text(),
		user_001c: $('#msg3').text()
    };

	$(document).ready(function() {
		if (typeof pageLoad === 'function') {
			pageLoad();
		}
	});

    var table = null;
    function pageLoad() {
        //https://datatables.net/manual/server-side
        //https://datatables.net/reference/option/language
        //https://datatables.net/reference/event/error
        $.fn.dataTable.ext.errMode = 'none';
        $.extend( $.fn.dataTable.defaults, {
            "columnDefs": [
                { "searchable": false, "targets": "_all" }
            ],
            "language": this.dt_lan,
            "destroy": true,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "type": "POST"
            },
            "lengthMenu": [ 2, 25, 50 ]
        });

        $('#t').on('error.dt', function ( e, settings, techNote, message ) {
            console.log( 'An error has been reported by DataTables: ', message );
            alert(this.msgs.session_001i);
            location.reload(true);
        });
        
        $('#d1').on('show.bs.modal', function (event) {
            var tr = $(event.relatedTarget).parents('tr'), username = tr.data('username'), version = tr.data('version'), modal = $(this);
            $('#c_username').val(username);
            $('#c_version').val(version);

            var u = $('#c_username').val();
            UserDwr.getUser(u, {
                callback: function(str) {
                    $('[name="username"]').val(u);
                    $('[name="cd"]').val(str['cd']);
                    $('[name="name"]').val(str['name']);
                    var g = $('[name="gender"][value="' + str['gender'] + '"]');
                    g.parent().trigger("click");
                    //g.prop('checked', true);
                    $('[name="department"]').val(str['department']);
                    $('[name="comment"]').val(str['comment']);
                    $('#d1').data("version", str['version']);
                }, errorHandler:function(message) {
                    alert(message);
                }
            });
        });
        $('#d2').on('show.bs.modal', function (event) {
            var tr = $(event.relatedTarget).parents('tr'), username = tr.data('username'), version = tr.data('version'), modal = $(this);
            $('#c_username').val(username);
            $('#c_version').val(version);
            modal.find('.username').text(username);

            var u = $('#c_username').val();
            UserDwr.logged(u, function(str) {
                if (str)
                    $("#d2Msg").text(this.msgs.user_001w);
                else
                    $("#d2Msg").text(this.msgs.user_001c);
            });
        });
        //$('#h,#d').hide();
    }
    function searchData() {
        //$('#h,#d').show();
        table = $('#t')
/*                .on('error.dt', function ( e, settings, techNote, message ) {
                    console.log( 'An error has been reported by DataTables: ', message );
                    alert(this.msgs.session_001i);
                    location.reload(true);
                })
*/
            .DataTable({
            "order": [[ 0, "asc" ]],
            "ajax": {
                "url": "./search",
                "data": function ( d ) {
                    d["p.username"] = $("[name='p.username']").val();
                    d["p.cd"] = $("[name='p.cd']").val();
                    d["p.name"] = $("[name='p.name']").val();
                    d["p.department"] = $("[name='p.department']").val();
                    d["p.gender"] = $(":checked[name='p.gender']").val();
                    d["p.status"] = $(":checked[name='p.status']").val();
                    d["dp.draw"] = d["draw"];
                    d["dp.length"] = d["length"];
                    d["dp.start"] = d["start"];
                    d["dp.orderColumn"] = d["order"][0]["column"];
                    d["dp.orderDir"] = d["order"][0]["dir"];
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
                //$('td:eq(6)', row).find("button:eq(0)").attr('id', "b1_" + data['0']);
                //$('td:eq(6)', row).find("button:eq(1)").attr('id', "b2_" + data['0']);
                //$('td:eq(6)', row).find("button:eq(1)").data("username", data['0']).data("version", data['6']);
                $(row).data("username", data['0']);
                $(row).data("version", data['6']);
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
                    alert(this.msgs.session_001i);
                    location.reload(true);
                } else {
                    alert(str['msg']);
                    $('#d2').modal('hide');
                    if (str['cd'] == '0')
                        table.ajax.reload();
                }
            }, errorHandler: function(message) {
                alert(message);
                $('#d2').modal('hide');
                table.ajax.reload();
            }
        });
    }
    function updateUser() {
        UserDwr.updateUser({
            "username": $("[name='username']").val(),
            "cd": $("[name='cd']").val(),
            "name": $("[name='name']").val(),
            "gender": $(":checked[name='gender']").val(),
            "department": $("[name='department']").val(),
            "comment": $("[name='comment']").val(),
            "version": $('#d1').data("version")
        }, {
            callback: function(str) {
                if (str['cd'] == undefined) {
                    alert(this.msgs.session_001i);
                    location.reload(true);
                } else {
                    alert(str['msg']);
                    if (str['cd'] == '0') {
                        $('#d1').modal('hide');
                        table.ajax.reload();
                    }
                }
            }, errorHandler: function(message) {
                alert(message);
                table.ajax.reload();
            }
        });
    }
    //the this is window
    this.searchData = searchData;
    this.removeUser = removeUser;
    this.updateUser = updateUser;
	this.logout = function() {
		/*
		$.post(url, d, function(data) {
			console.log(data);
		});*/
		$('#f').submit();
	}
});