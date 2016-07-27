<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.userdetails.User"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<sec:csrfMetaTags />
		<title><s:text name="app.nm"/>-<decorator:title/></title>
<%
String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

//Authentication auth = (Authentication) request.getUserPrincipal();
//User user = (User) auth.getPrincipal();
String sessiondId = session.getId();
%>
		<c:set var="base" value="<%=base %>" scope="request"></c:set>
		<link rel="icon" href="${base}/img/favicon.ico">
		<link href="${base}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${base}/css/custom.css" rel="stylesheet" type="text/css" />
		
		<!-- http://www.w3schools.com/tags/tag_base.asp
		<base href="${base}"> -->
		<decorator:head/>
		<sec:authentication property="principal" var="user"/>
	</head>
	<body>
		<nav id="top" class="navbar navbar-default navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${base}/home">
						<img alt="Brand" src="${base}/img/favicon.ico">
					</a>
					<a class="navbar-brand" href="${base}/home"><s:text name="app.nm"/></a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="${base}/home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><s:text name="homepage"/></a></li>
						<li><a href="#about"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span><s:text name="about"/></a></li>
						<li><a href="#contact"><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span><s:text name="contact"/></a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="<s:text name="search"/>" maxlength="12">
								</div>
								
								<button type="submit" class="btn btn-default">
								  <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</button>
							</form>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>${user.username}<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li class="dropdown-header"><s:text name="private"/></li>
								<li><a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><s:text name="info.upate"/></a></li>
								<li><a href="#"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span><s:text name="password.change"/></a></li>
								<li><a href="javascript:void(0);" onclick="logout('<c:url value="logout"/>');">
									<span class="glyphicon glyphicon-off" aria-hidden="true"></span><s:text name="logout"/></a>
									<div style="display: none;">
									<form id="f" action='${base}/<c:url value="logout"/>' method="post" class="navbar-form navbar-right">
										<sec:csrfInput />
									</form>
									</div>
								</li>
								<li role="separator" class="divider"></li>
								<li class="dropdown-header"><s:text name="manager"/></li>
								<li><a href="#"><s:text name="user.manager"/></a></li>
								<li><a href="#"><s:text name="authority.manager"/></a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<decorator:body />
		<footer class="footer">
			<div class="container">
				<p><s:text name="statement.copyright"/></p>
				<p><a href="#top"><s:text name="top.go"/></a></p>
				<p class="text-muted">session: <%=sessiondId %></p>
			</div>
		</footer>
		<script src="${base}/js/jquery-1.10.2.min.js"></script>
		<script src="${base}/js/bootstrap.min.js"></script>
		<script src="${base}/js/common.js"></script>
	</body>
</html>