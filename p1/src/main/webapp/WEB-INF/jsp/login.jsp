<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title><s:text name="login"/></title>
    <style type="text/css">
    </style>
  </head>

  <body>
  <div class="container">
    <h2><s:text name="login"/></h2>
    <c:if test="${not empty error}">
		<s:actionerror />
    </c:if>
	<s:actionmessage />
	<div class="col-sm-6 col-md-5" style="padding-left: 0;">
		<form action="<c:url value='auth'/>" method="POST">
			<sec:csrfInput />
	        <div class="form-group">
				<label><s:text name="username"/></label >
				<s:textfield name='u' autofocus="autofocus" maxlength="5" class="form-control" required="required" />
	        </div>
			<div class="form-group">
				<label><s:text name="password"/></label>
				<s:password name='p' maxlength="10" class="form-control" required="required" />
			</div>
			<div class="form-group">
				<s:checkbox name="r"/>
				<s:label key="remember" for="r"/>
			</div>
			<div class="form-group" style="text-align: right;">
				<button type="submit" class="btn btn-primary active"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span><s:text name="login"/></button>
				<!--<s:reset key="reset" cssClass="btn btn-default"></s:reset>-->
				<s:a action="join" namespace="/" cssClass="btn btn-link">
					<s:text name="register" />
					<s:param name="source">login</s:param>
				</s:a>
			</div>
		</form>
	</div>
	<div class="col-sm-6 col-md-7">
	
	</div>
  </div>
  </body>
</html>
