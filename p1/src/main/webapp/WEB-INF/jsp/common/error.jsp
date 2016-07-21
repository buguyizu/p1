<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><s:text name="error.page" /></title>
</head>
<body>
	<div><s:text name="error.cd" />${param.type}</div>
	<br />
	<c:choose>
		<c:when test="${param.type eq '-1'}">
			<div>403</div>
		</c:when>
		<c:when test="${param.type eq '0'}">
			<div>404</div>
		</c:when>
		<c:when test="${param.type eq '1'}">
			<div>505</div>
		</c:when>
		<c:when test="${param.type eq '2'}">
			<div>Exception in web.xml</div>
		</c:when>
		<c:when test="${param.type eq '3'}">
			<div>exception in struts</div>
		</c:when>
		<c:when test="${param.type eq '4'}">
			<div>请用以下链接访问主页！</div>
		</c:when>
		<c:when test="${param.type eq '5'}">
			<div>会话已结束！</div>
		</c:when>
	</c:choose>
	<s:a action="home" namespace="/">
		<s:text name="homepage" />
	</s:a>
</body>
</html>