<%@page contentType="text/html;charset=UTF-8" %>
<html>
<body>
		<form action="<%=request.getContextPath()%>/login.action" method="get">
		户名：<input type="text" name="username"><br>
		密码：<input type="password" name="password"><br>
		<input type="submit" value="login">
		</form>
</body>
</html>