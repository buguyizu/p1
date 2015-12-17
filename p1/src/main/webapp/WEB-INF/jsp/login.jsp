<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>Login</title>
  </head>

  <body onload="document.f.j_username.focus();">
    <h1>Login</h1>

    <%-- this form-login-page form is also used as the
         form-error-page to ask for a login again.
         --%>
    <c:if test="${not empty error}">
      <font color="red">
        Your login attempt was not successful, try again.<br/><br/>
        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if>

	<form action="<c:url value='j_check'/>" method="POST">
		<sec:csrfInput />
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='u'
					value='<c:if test="${not empty error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='p'></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="r"></td>
				<td>Don't ask for my password for two weeks</td>
			</tr>

			<tr>
				<td colspan='2'><input name="submit" type="submit"></td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>
