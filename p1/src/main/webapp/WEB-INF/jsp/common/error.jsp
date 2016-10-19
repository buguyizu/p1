<%@page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>error page</title>
</head>
<body>
<%-- 当不经过struts2的filter，出错转到web.xml中设定的error.jsp时，因为其中用到了struts的tag，所以报错，页面空白。
所以暂时本页面中不使用struts的tag：
<s:text name="error.page" />
<s:text name="error.cd" />
 --%>
    <div class="container">
	<dl>
		<dt>error code： ${param.type}</dt>
		<dd>
			<c:choose>
				<c:when test="${param.type eq '0'}">
					请从链接访问主页！
					<script type="text/javascript">
					   //location = "/p1/home";	
					</script>
				</c:when>
				<c:when test="${param.type eq 'exception'}">
					${exception}
				</c:when>
				<c:when test="${param.type eq 'timeout'}">
					会话已结束！
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</dd>
	</dl>
    <a href="<%=request.getContextPath()%>/home">home</a>
	</div>
</body>
</html>