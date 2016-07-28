<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <title><s:text name="register"/></title>
    <s:head />
  </head>

  <body>
    <h2><s:text name="register"/></h2>

	<div style="color:red;">
		<s:fielderror />
		<s:actionerror />
	</div>
	<s:form action="join" namespace="/" method="POST">
		<sec:csrfInput />
		<s:hidden name="source" value="signup" />
		<table>
			<tr>
				<td><s:text name="username"/>:</td>
				<td><s:textfield name='user.username' autofocus="autofocus" maxlength="5" /></td>
			</tr>
			<tr>
				<td><s:text name="password"/>:</td>
				<td><s:password name='user.password' maxlength="10" /></td>
			</tr>
			<tr>
				<td><s:text name="user.password2"/>:</td>
				<td><s:password name='user.password2' maxlength="10" /></td>
			</tr>
			<!-- 
			<tr>
				<td><s:checkbox name="user.enabled"/></td>
				<td><s:label key="user.enabled" for="user.enabled"/></td>
			</tr> -->
			<tr>
				<td><s:text name="user.idNumber"/>:</td>
				<td><s:textfield name='user.idNumber' maxlength="5" /></td>
			</tr>
			<tr>
				<td><s:text name="user.code"/>:</td>
				<td><s:textfield name='user.code' maxlength="5"/></td>
			</tr>
			<tr>
				<td><s:text name="user.name"/>:</td>
				<td><s:textfield name='user.name' maxlength="5" /></td>
			</tr>
			<tr>
				<td><s:text name="user.gender"/>:</td>
				<td><s:textfield name='user.gender' maxlength="5" /></td>
			</tr>
			<tr>
				<td><s:text name="user.department"/>:</td>
				<td><s:textfield name='user.department' maxlength="5" /></td>
			</tr>
			<tr>
				<td><s:text name="user.comment"/>:</td>
				<td><s:textfield name='user.comment' maxlength="5" /></td>
			</tr>
			<tr>
				<td colspan='2'>
					<s:submit key="register"></s:submit>
					<s:reset key="clear"></s:reset>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>
