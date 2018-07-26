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
<%--
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	Authentication auth = (Authentication) request.getUserPrincipal();
	User user = (User) auth.getPrincipal();
--%>
		<link rel="icon" href="${base}/img/favicon.ico">
        <s:head />
        <%-- http://www.w3schools.com/tags/tag_base.asp
        <base href="${base}"> --%>
        <%@ include file="./fw-front.jsp" %>
		<link href="${base}/css/custom.css" rel="stylesheet" type="text/css" />
		<decorator:head/>
		<sec:authentication property="principal" var="user"/>
        <script type="text/javascript">
        require(['sse'], function(sse) {
            window.addEventListener("load", sse.listener, false);
        });
        </script>
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
								<li><a href="${base}/user/homepage/"><span class="glyphicon glyphicon-bell" aria-hidden="true"></span><s:text name="message"/></a></li>
                                <li><a href="${base}/user/info/?source=2"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><s:text name="info.upate"/></a></li>
								<li><a href="${base}/user/info/?source=3"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span><s:text name="password.change"/></a></li>
								<li><a href="javascript:void(0);" onclick="logout();">
									<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span><s:text name="logout"/></a>
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
        <div style="display: none;">
          <span id="msg1"><s:text name="MI-SESSION-001"/></span>
          <span id="msg2"><s:text name="MW-USER-001"/></span>
          <span id="msg3"><s:text name="MC-USER-001"/></span>
          <span id="dt-lan">
{
    "emptyTable": "表格无数据",
    "info": "总件数：_TOTAL_ 总页数：_PAGES_",
    "infoEmpty": "显示无数据",
    "lengthMenu": "每页件数： _MENU_",
    "search": "搜索：",
    "processing": "处理中...",
    "paginate": {
        "first": "|&lt;",
        "last": "&gt;|",
        "next": "&raquo;",
        "previous": "&laquo;"
    },
    "zeroRecords": "无匹配数据"
}
          </span>
        </div>
		<footer class="footer">
			<div class="container">
				<p><s:text name="statement.copyright"/></p>
				<p><a href="#top"><s:text name="top.go"/></a></p>
				<p class="text-muted">session: <%=session.getId() %></p>
			</div>
		</footer>
	</body>
</html>