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
	<script type="text/javascript" charset="utf8" src="../js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" charset="utf8" src="../js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">
        function pageLoad() {
        	//https://datatables.net/manual/server-side
        	//https://datatables.net/reference/option/language
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
                    //"processing": "处理中...",
                    "paginate": {
                        "first": "|<",
                        "last": ">|",
                        "next": "&raquo;",
                        "previous": "&laquo;"
                    },
                    "zeroRecords": "无匹配数据"
                },
                //"destroy": true,
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
            $('#t').DataTable({
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
                        delete d["draw"];
                        delete d["length"];
                        delete d["start"];
                        delete d["order"];
                        delete d["search"];
                	},
                	"dataSrc": function ( json ) {
                		
                		return json.data;
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
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span><s:text name="search"/></button>
            <button type="button" class="btn btn-default" onclick="searchData();"><s:text name="clear"/></button>
          </s:form>
          <h3 class="sub-header" id="h">信息一览</h3>
          
          <div class="table-responsive" id="d">
            <table id="t" class="table table-striped">
              <thead>
                <tr>
                  <th>用户名</th>
                  <th>编号</th>
                  <th>姓名</th>
                  <th>性别</th>
                  <th>部门</th>
                  <th>说明</th>
                </tr>
              </thead>
            </table>
          </div>
        </div>
      </div>
    </div>
</body>
</html>