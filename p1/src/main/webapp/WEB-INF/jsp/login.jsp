<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title><s:text name="login"/></title>
    <style type="text/css">
        form {
            max-width: 330px;
        }
    </style>
  </head>

  <body>
  <div class="container">
    <h2><s:text name="login"/></h2>
    <c:if test="${not empty error}">
		<s:actionerror />
    </c:if>
	<s:actionmessage />
	<form action="<c:url value='auth'/>" method="POST">
		<sec:csrfInput />
        <div class="form-group">
			<label><s:text name="username"/></label >
			<s:textfield name='u' autofocus="autofocus" maxlength="5" class="form-control" />
        </div>
		<div class="form-group">
			<label><s:text name="password"/></label>
			<s:password name='p' maxlength="10" class="form-control" />
		</div>
		<div class="form-group">
			<s:checkbox name="r"/>
			<s:label key="remember" for="r"/>
		</div>
		<div class="form-group">
			<s:submit key="login" cssClass="btn btn-primary active"></s:submit>
			<s:reset key="reset" cssClass="btn btn-default"></s:reset>
			<s:a action="join" namespace="/" cssClass="btn btn-link">
				<s:text name="register" />
				<s:param name="source">login</s:param>
			</s:a>
		</div>
	</form>
  </div>
  </body>
</html>
