<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="user.manager"/></title>
	<!-- <link href="../css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
    <link href="../css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
	    table.dataTable tbody tr.selected {
	        background-color: #B0BED9;
	    }
	    tr.highlight {
		    background-color: #e6e6e6 !important;
		}
		td .btn-group {
		  margin: -8px;
		}
    </style>
	<script type="text/javascript" charset="utf8" src="../js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="../js/dataTables.bootstrap.min.js"></script>
    <script src='../dwr/engine.js'></script>
    <script src='../dwr/interface/UserDwr.js'></script>
    <script type="text/javascript">
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
                "language": {
                	"emptyTable": "表格无数据",
                    "info": "总件数：_TOTAL_ 总页数：_PAGES_",
                    "infoEmpty": "显示无数据",
                    "lengthMenu": "每页件数： _MENU_",
                    "search": "搜索：",
                    "processing": "处理中...",
                    "paginate": {
                        "first": "|<",
                        "last": ">|",
                        "next": "&raquo;",
                        "previous": "&laquo;"
                    },
                    "zeroRecords": "无匹配数据"
                },
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
                alert('<s:text name="MI-SESSION-001"/>');
                location.reload(true);
            });
            
            $('#d1').on('show.bs.modal', function (event) {
                var tr = $(event.relatedTarget).parents('tr'), username = tr.data('username'), version = tr.data('version'), modal = $(this);
                $('#c_username').val(username);
                $('#c_version').val(version);

                var u = $('#c_username').val();
                UserDwr.getUser(u, function(str) {
                    $('[name="username"]').val(u);
                    $('[name="cd"]').val(str['cd']);
                    $('[name="name"]').val(str['name']);
                    var g = $('[name="gender"][value="' + str['gender'] + '"]');
                    g.parent().trigger("click");
                    //g.prop('checked', true);
                    $('[name="department"]').val(str['department']);
                    $('[name="comment"]').val(str['comment']);
                    $('#d1').data("version", str['version']);
                });
            });
            $('#d2').on('show.bs.modal', function (event) {
                var tr = $(event.relatedTarget).parents('tr'), username = tr.data('username'), version = tr.data('version'), modal = $(this);
                $('#c_username').val(username);
                $('#c_version').val(version);
                modal.find('.username').text(username);
            });
            //$('#h,#d').hide();
        }
        function searchData() {
            //$('#h,#d').show();
            table = $('#t')
/*                .on('error.dt', function ( e, settings, techNote, message ) {
                    console.log( 'An error has been reported by DataTables: ', message );
                    alert('<s:text name="MI-SESSION-001"/>');
                    location.reload(true);
                })
*/
                .DataTable({
                "processing": true,
                "serverSide": true,
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
        	UserDwr.removeUser(u, $('#c_version').val(), function(str) {
        		if (str['cd'] == undefined) {
                    alert('<s:text name="MI-SESSION-001"/>');
                    location.reload(true);
        		} else {
        			alert(str['msg']);
        			$('#d2').modal('hide');
	        		if (str['cd'] == '0')
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
        	}, function(str) {
        		if (str['cd'] == undefined) {
	                alert('<s:text name="MI-SESSION-001"/>');
	                location.reload(true);
	            } else {
	                alert(str['msg']);
	                if (str['cd'] == '0') {
		                $('#d1').modal('hide');
		                table.ajax.reload();
	                }
	            }
        	});
        }
    </script>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
      	<%@ include file="../menu_side.jsp" %>
        <div class="col-sm-9 col-md-10 main">
          <h3 class="page-header"><s:text name="user.manager"/></h3>
          <div class="alert alert-danger" role="alert" style="display: none;">
            <p>test</p>
          </div>
          <s:form action="search">
            <div class="row">
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="username"/></span>
                  <s:textfield name="p.username" cssClass="form-control" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.code"/></span>
                  <s:textfield name="p.cd" cssClass="form-control" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.name"/></span>
                  <s:textfield name="p.name" cssClass="form-control" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="department"/></span>
                  	<s:select class="form-control"
				       name="p.department" emptyOption="true"
				       list="getCodeList('01')"
				       listKey="fCode" listValue="fValue"/>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="gender"/></span>
                  <div class="form-control btn-group" data-toggle="buttons">
                    <s:radio name="p.gender" list="getCodeList('02')" listKey="fCode" listValue="fValue" />
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.enabled"/></span>
                  <div class="form-control btn-group" data-toggle="buttons">
                    <s:radio name="p.status" list="getCodeList('03')" listKey="fCode" listValue="fValue" />
                  </div>
                </div>
              </div>
            </div>
          </s:form>
          <div class="pull-right">
            <button type="button" class="btn btn-primary" onclick="searchData();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span><s:text name="search"/></button>
            <button type="button" class="btn btn-default" ><s:text name="clear"/></button>
          </div>
          <h3 class="sub-header" id="h"><s:text name="list.info"/></h3>
          
          <div class="table-responsive" id="d">
            <div id="temp" style="display: none;">
	         <div class="btn-group" role="group" aria-label="...">
	             <button type="button" class="btn btn-default" title="<s:text name="edit"/>" data-toggle="modal" data-target="#d1"><span class="glyphicon glyphicon-edit"></span></button>
	             <button type="button" class="btn btn-default" title="<s:text name="remove"/>" data-toggle="modal" data-target="#d2"><span class="glyphicon glyphicon-remove"></span></button>
	         </div>
	        </div>
            <table id="t" class="table table-striped table-bordered">
              <thead>
                <tr>
                  <th><s:text name="username"/></th>
                  <th><s:text name="cd"/></th>
                  <th><s:text name="name"/></th>
                  <th><s:text name="gender"/></th>
                  <th><s:text name="department"/></th>
                  <th><s:text name="user.comment"/></th>
                  <th></th>
                </tr>
              </thead>
            </table>
            <input type="hidden" id="c_username" /><input type="hidden" id="c_version" />
          </div>
          <div id="d1" class="modal fade" tabindex="-1" role="dialog">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><s:text name="user.edit"/></h4>
                  </div>
                  <div class="modal-body">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="username"/></span>
                            <input type="text" name="username" class="form-control" readonly="readonly" />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="user.code"/></span>
                            <input type="text" name="cd" class="form-control" maxlength="20" />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="user.name"/></span>
                            <input type="text" name="name" class="form-control" maxlength="20" />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="gender"/></span>
                            <div class="form-control btn-group" data-toggle="buttons">
                                <s:radio name="gender" list="getCodeList('02')" listKey="fCode" listValue="fValue" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="department"/></span>
                              <s:select name="department" class="form-control"
                                 emptyOption="true"
                                 list="getCodeList('01')"
                                 listKey="fCode" listValue="fValue"
                              />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="user.enabled"/></span>
                            <div class="form-control btn-group" data-toggle="buttons">
                                <s:radio name="status" list="getCodeList('03')" listKey="fCode" listValue="fValue" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                        <div class="input-group form-group">
                          <span class="input-group-addon"><s:text name="user.comment"/></span>
                            <s:textarea name="comment" class="form-control" rows="3"></s:textarea>
                          </div>
                        </div>
                      </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="back"/></button>
                    <button type="button" class="btn btn-primary" onclick="updateUser();"><span class="glyphicon glyphicon-ok"></span><s:text name="ok"/></button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <div id="d2" class="modal fade" tabindex="-1" role="dialog">
			  <div class="modal-dialog modal-sm" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title"><s:text name="user.remove"/></h4>
			      </div>
			      <div class="modal-body">
			        <h2><span class="label label-default glyphicon glyphicon-user username"></span></h2><s:text name="MC-USER-001"/>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="back"/></button>
			        <button type="button" class="btn btn-primary" onclick="removeUser();"><s:text name="ok"/></button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
        </div>
      </div>
    </div>
</body>
</html>