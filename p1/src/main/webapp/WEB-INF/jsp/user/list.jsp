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
    </style>
	<script type="text/javascript" charset="utf8" src="../js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="../js/dataTables.bootstrap.min.js"></script>
    <script src='../dwr/engine.js'></script>
    <script src='../dwr/interface/UserDwr.js'></script>
    <script type="text/javascript">
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
        	$('#h,#d').hide();
        }
        function searchData() {
            $('#h,#d').show();
            var table = $('#t')
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
                        	return '<a href="javascript:void();" title="<s:text name="edit"/>" data-toggle="modal" data-target="#d1" data-username="' + data + '"><span class="glyphicon glyphicon-edit"></span></a>'
                            + '<a href="javascript:void();" title="<s:text name="remove"/>" data-toggle="modal" data-target="#d2" data-username="' + data + '"><span class="glyphicon glyphicon-remove"></span></a>';
                        }
                    }
                ],
                "rowCallback": function( row, data, index ) {
                	//$('td:eq(4)', row).html( '<b>A</b>' );
                }
            });

            $('#t').on('error.dt', function ( e, settings, techNote, message ) {
                console.log( 'An error has been reported by DataTables: ', message );
                alert('<s:text name="MI-SESSION-001"/>');
                location.reload(true);
            });
            
            $('#t tbody').on( 'mouseenter', 'tr', function () {
            	 $('#t tr').removeClass( 'highlight' );
            	$(this).addClass( 'highlight' );
            }).on( 'mouseleave', 'tr', function () {
                 $('#t tr').removeClass( 'highlight' );
            });
        }
        function removeUser() {
        	UserDwr.removeUser();
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
            <button type="button" class="btn btn-primary" onclick="searchData();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span><s:text name="search"/></button>
            <button type="button" class="btn btn-default" ><s:text name="clear"/></button>
          </s:form>
          <h3 class="sub-header" id="h"><s:text name="list.info"/></h3>
          
          <div class="table-responsive" id="d">
            <table id="t" class="table table-striped">
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
          </div>
          <div id="d1" class="modal fade" tabindex="-1" role="dialog">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><s:text name="user.edit"/></h4>
                  </div>
                  <div class="modal-body">
                    <p>One fine body&hellip;</p>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="back"/></button>
                    <button type="button" class="btn btn-primary"><s:text name="ok"/></button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </div>
          <div id="d2" class="modal fade" tabindex="-1" role="dialog">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title"><s:text name="user.remove"/></h4>
			      </div>
			      <div class="modal-body">
			        <p><s:text name="MC-USER-001"/></p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="back"/></button>
			        <button type="button" class="btn btn-primary"><s:text name="ok"/></button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
        </div>
      </div>
    </div>
</body>
</html>