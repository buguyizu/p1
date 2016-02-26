<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="./img/favicon.ico">
    <title><s:text name="app.nm"/>-<decorator:title/></title>
</head>
<body>
    <s:text name="app.nm"/>
    <hr />
    <decorator:body />
    <hr />
    <s:text name="statement.copyright"/>
</body>
</html>