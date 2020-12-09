<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><s:text name="user.homepage"/></title>
    <script src="${base}/webjars/requirejs/${require_ver}/require.js" data-main="${base}/js/common.js"></script>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="../menu_side.jsp">
            <jsp:param name="index" value="13"/>
        </jsp:include>
        <div class="col-sm-9 col-md-10 main">
          <h2 class="page-header"><s:text name="user.homepage"/></h2>
          <div class="row">
              <span class="glyphicon glyphicon-bell" aria-hidden="true"></span><s:text name="message"/> dummy
	          <div class="alert alert-success" role="alert">成功</div>
	          <div class="alert alert-info" role="alert">信息</div>
	          <div class="alert alert-warning" role="alert">警告</div>
          </div>
        </div>
      </div>
    </div>
</body>
</html>