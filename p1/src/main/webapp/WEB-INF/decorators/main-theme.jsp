<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.security.Principal"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
    	<link rel="icon" href="../../favicon.ico">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Principal principal = request.getUserPrincipal();


%>
		<title><s:text name="title"/>-<decorator:title/></title>
		<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" />
		<base href="${basePath}">
		<decorator:head/>
		
	</head>
	<body>
	    <nav class="navbar navbar-inverse navbar-fixed-top">
	    <div class="container">
	    	<div class="navbar-header">
	    		<a class="navbar-brand" href="#"><s:text name="title"/></a>
	    	</div>
	    	<div id="navbar" class="navbar-collapse collapse">
          		<ul class="nav navbar-nav">
		            <li class="active"><a href="#"><s:text name="homepage"/></a></li>
		            <li><a href="#about"><s:text name="about"/></a></li>
		            <li><a href="#contact"><s:text name="contact"/></a></li>
	          	</ul>
				<form action="login" class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="<s:text name="mail"/>" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="<s:text name="password"/>" class="form-control">
					</div>
					<button type="submit" class="btn btn-success"><s:text name="login"/></button>
				</form>
				<p><a href="<c:url value="../j_spring_security_logout"/>"><s:text name="logoff"/></a></p>
			</div>
	    </div>
	    </nav>
	    <div class="container">
	    	<decorator:body />
	    </div>
	    <hr/>
	    <footer>
        	<p>© Company 2015</p>
      	</footer>
	    <script src="<%=path%>/js/jquery-1.10.2.min.js"></script>
		<script src="<%=path%>/js/bootstrap.min.js"></script>
		<script src="<%=path%>/js/common.js"></script>
	</body>
</html>