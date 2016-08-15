<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>${action.getText("homepage")}</title>
</head>
<body>
    <div class="jumbotron">
      <div class="container">
        <h2>系统介绍</h2>
        <p>系统介绍...</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
      </div>
    </div>
    <div class="container">
		<div class="row">
	        <div class="col-md-4">
	          <h2>用户管理</h2>
	          <p>用户管理... </p>
	          <p><a class="btn btn-default" href="./user/list" role="button">进入 &raquo;</a></p>
	        </div>
	        <div class="col-md-4">
	          <h2>BOM</h2>
	          <p>Bill of material.</p>
	          <p><a class="btn btn-default" href="./bom/" role="button">Go...</a></p>
	        </div>
	    </div>
    </div>
</body>
</html>