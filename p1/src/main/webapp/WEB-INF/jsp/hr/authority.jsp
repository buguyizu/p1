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
        .modal-backdrop { background-color: initial; }
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
          <s:form action="." class="form-horizontal">
            <div class="form-group">
              <div class="col-sm-8">
                <div class="has-feedback input-group">
                  <input type="search" id="username" name="p.username" autofocus="autofocus" class="form-control" />
                  <span class="input-group-btn">
                    <button type="button" id="clear" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span></button>
                  </span>
                </div>
                <select id="users" multiple="multiple" class="form-control input-lg"></select>
              </div>
              <label for="username" class="control-label col-sm-3">
                <span class="glyphicon glyphicon-filter"></span><s:text name="user.filter"/>
              </label>
            </div>
          </s:form>
          <h3 class="sub-header"><s:text name="authority.op"/></h3>
          <div class="alert alert-info" role="alert">
            <span class="glyphicon glyphicon-info-sign"></span><strong><s:text name="user.objs"/></strong><span id="objs"></span>
            <span class="glyphicon glyphicon-ok" style="display: none; color: green; font-size: 16px;"></span>
            <a href="javascript:void(0);" class="alert-link pull-right"><span class="glyphicon glyphicon-refresh"></span></a>
          </div>
          <div id="op" class="list-group col-sm-8"></div>
        </div>
      </div>
    </div>
    <div class="modal" tabindex="-1" role="dialog" data-backdrop="static">
      <span class="glyphicon glyphicon-hourglass"></span>
    </div>
</body>
</html>