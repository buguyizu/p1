<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="basic">
    <title><s:text name="app.nm" />-<decorator:title default="welcome" /></title>
    <c:set var="base" value="<%=request.getContextPath() %>" scope="session"></c:set>
    <c:set var="require_ver" value="2.3.6" scope="session"></c:set>
    <link rel="icon" href="${base}/img/favicon.ico">
    <%@ include file="./fw-basic-css.jsp" %>
    <script src="${base}/webjarsjs"></script>
    <script src="${base}/js/require.config.js"></script>
    <script src="${base}/webjars/requirejs/${require_ver}/require.js" data-main="${base}/js/common.js"></script>
    <decorator:head/>
    <s:head />
</head>
<body>
    <nav id="top" class="navbar navbar-default navbar-static-top">
        <div class="container">
	        <div class="navbar-header">
                <a class="navbar-brand" href="${base}/home">
                    <img alt="Brand" src="${base}/img/favicon.ico">
                </a>
	            <a href="${base}/home" class="navbar-brand"><s:text name="app.nm" /></a>
	        </div>
        </div>
    </nav>
    <decorator:body />
<%
String sessiondId = session.getId();
%>
    <footer class="footer">
        <div class="container">
            <p><s:text name="statement.copyright"/></p>
            <p class="text-muted">session: <%=sessiondId %></p>
        </div>
    </footer>
</body>
</html>