<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.userdetails.User"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <sec:csrfMetaTags />
	    <meta name="description" content="">
	    <meta name="author" content="">
    	<link rel="icon" href="../../favicon.ico">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//Authentication auth = (Authentication) request.getUserPrincipal();
//User user = (User) auth.getPrincipal();
%>
		<title><s:text name="title"/>-<decorator:title/></title>
		<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" />
		<base href="${basePath}">
		<decorator:head/>
		<sec:authentication property="principal" var="user"/>
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
				<form action='<c:url value="logout"/>' method="post" class="navbar-form navbar-right">
	    			<sec:csrfInput />
					<a href='#'>${user.username}</a>
					<a href='<%=path%>/<c:url value="j_spring_security_logout"/>'><s:text name="logout"/></a>
					<button type="submit" class="btn btn-success"><s:text name="logout"/></button>
				</form>
				<form action='<c:url value="j_spring_security_login"/>' method="post" class="navbar-form navbar-right">
	    			<sec:csrfInput />
					<div class="form-group">
						<input type="text" placeholder="<s:text name="mail"/>" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="<s:text name="password"/>" class="form-control">
					</div>
					<button type="submit" class="btn btn-success"><s:text name="login"/></button>
				</form>
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