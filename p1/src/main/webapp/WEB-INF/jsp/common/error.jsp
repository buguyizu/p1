<%@page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><s:text name="error.page" /></title>
</head>
<body>
    <div class="container">
	<dl>
		<dt><s:text name="error.cd" />： ${param.type}</dt>
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
                    <script type="text/javascript">
                       //location = "/p1/home";   
                    </script>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</dd>
	</dl>
	</div>
</body>
</html>