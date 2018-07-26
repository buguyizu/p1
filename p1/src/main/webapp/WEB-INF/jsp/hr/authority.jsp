<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="user.manager"/></title>
    <%@ include file="../../decorators/fw-datatables.jsp" %>
    <%@ include file="../../decorators/fw-dwr.jsp" %>
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
    <script src="${base}/webjars/requirejs/2.3.5/require.js" data-main="${base}/js/hr/authority.js"></script>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="../menu_side.jsp">
            <jsp:param name="index" value="22"/>
        </jsp:include>
        <div class="col-sm-9 col-md-10 main">
          <h3 class="page-header"><s:text name="authority.manager"/></h3>
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
            <button type="button" class="btn btn-primary" onclick="searchData();"><span class="glyphicon glyphicon-search"></span><s:text name="search"/></button>
            <button type="button" class="btn btn-default" ><s:text name="clear"/></button>
          </div>
          <h3 class="sub-header" id="h"><s:text name="list.info"/></h3>
          
          <div class="table-responsive" id="d">
            <div id="temp" style="display: none;">
	         <div class="btn-group" role="group">
	             <button type="button" class="btn btn-default" title="<s:text name="edit"/>" data-toggle="modal" data-target="#d1"><span class="glyphicon glyphicon-edit"></span></button>
	             <button type="button" class="btn btn-default" title="<s:text name="remove"/>" data-toggle="modal" data-target="#d2"><span class="glyphicon glyphicon-remove"></span></button>
	         </div>
	        </div>
            <table id="t" class="table table-striped table-bordered" style="width: 100%;">
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
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
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
			        <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			        <h4 class="modal-title"><s:text name="user.remove"/></h4>
			      </div>
			      <div class="modal-body">
			        <h2><span class="label label-default glyphicon glyphicon-user username"></span></h2><span id="d2Msg"></span>
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