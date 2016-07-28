<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title><s:text name="login"/></title>
  </head>

  <body>
    <h2><s:text name="login"/></h2>

    <c:if test="${not empty error}">
		<div style="color:red;">
			<s:actionerror />
		</div>
    </c:if>
	<s:actionmessage />
	<form action="<c:url value='auth'/>" method="POST">
		<sec:csrfInput />
		<table>
			<tr>
				<td><s:text name="username"/>:</td>
				<td><s:textfield name='u' autofocus="autofocus" maxlength="5" /></td>
			</tr>
			<tr>
				<td><s:text name="password"/>:</td>
				<td><s:password name='p' maxlength="10" /></td>
			</tr>
			<tr>
				<td><s:checkbox name="r"/></td>
				<td><s:label key="remember" for="r"/></td>
			</tr>
			<tr>
				<td colspan='2'>
					<s:submit key="login"></s:submit>
					<s:reset key="clear"></s:reset>
					<s:a action="join" namespace="/">
						<s:text name="register" />
						<s:param name="source">login</s:param>
					</s:a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
