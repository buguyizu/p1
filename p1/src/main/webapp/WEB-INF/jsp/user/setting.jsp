<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><s:text name="user.homepage"/></title>
    <script src="${base}/webjars/requirejs/2.3.5/require.js" data-main="${base}/js/common.js"></script>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="../menu_side.jsp">
            <jsp:param name="index" value="12"/>
        </jsp:include>
        <div class="col-sm-9 col-md-10 main">
          <h2 class="page-header"><s:text name="user.setting"/></h2>
          <div class="row">
          </div>
        </div>
      </div>
    </div>
</body>
</html>