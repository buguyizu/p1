<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><s:text name="error.page" /></title>
</head>
<body>
	<dl>
		<dt><s:text name="error.cd" />： ${param.type}</dt>
		<dd>
			<c:choose>
				<c:when test="${param.type eq '2'}">
					Exception in web.xml
				</c:when>
				<c:when test="${param.type eq 'exception'}">
					exception in struts
					<s:property value="exception" />
				</c:when>
				<c:when test="${param.type eq '4'}">
					请从链接访问主页！
				</c:when>
				<c:when test="${param.type eq '5'}">
					会话已结束！
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</dd>
	</dl>
</body>
</html>