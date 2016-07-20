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
    <link rel="icon" href="./img/favicon.ico">
    <title><s:text name="app.nm" />-<decorator:title default="welcome" /></title>
    <decorator:head/>
    <c:set var="contextPath" value="<%=request.getContextPath() %>" scope="request"></c:set>
</head>
<body>
    <s:text name="app.nm" />
    <hr />
    <decorator:body />
    <hr />
    <s:text name="statement.copyright" />
</body>
</html>