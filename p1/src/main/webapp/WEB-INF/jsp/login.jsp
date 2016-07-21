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

    <%-- this form-login-page form is also used as the
         form-error-page to ask for a login again.
         --%>
    <c:if test="${not empty error}">
      <div style="color:red;">
      	<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
      </div>
    </c:if>

	<form action="<c:url value='auth'/>" method="POST">
		<sec:csrfInput />
		<table>
			<tr>
				<td><s:text name="username"/>:</td>
				<td><input type='text' name='u' autofocus="autofocus" maxlength="5"
					value='<c:if test="${not empty error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' /></td>
			</tr>
			<tr>
				<td><s:text name="password"/>:</td>
				<td><input type='password' name='p' maxlength="10"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="r"></td>
				<td><s:text name="remember"/></td>
			</tr>
			<tr>
				<td colspan='2'>
					<s:submit key="login"></s:submit>
					<s:reset key="clear"></s:reset>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
