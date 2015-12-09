<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../favicon.ico">
    <title></title>
</head>
<body>
    <h1>Header</h1>
    <p><b>Navigation</b></p>
    <hr />
    <decorator:body />
    <hr />
    <h1>Footer</b></h1>
</body>
</html>