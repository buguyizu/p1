<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <title><s:text name="register"/></title>
    <style type="text/css">
        form {
            max-width: 660px;
        }
    </style>
    <script type="text/javascript">
    this.pageLoad = function() {
    	$("[name='user.username']" ).attr('maxlength', 10).prop('required', true);
        $("[name='user.password']" ).attr('maxlength', 10).prop('required', true);
        $("[name='user.password2']").attr('maxlength', 10).prop('required', true);
        $("[name='user.idNumber']" ).attr('maxlength', 10);
        $("[name='user.code']"     ).attr('maxlength', 10);
        $("[name='user.name']"     ).attr('maxlength', 10);
        $("[name='user.comment']"  ).attr('rows', 3);
	}
    </script>
  </head>

  <body>
    <div class="container">
    <h2><s:text name="register"/></h2>
	<div>
        <s:fielderror />
        <s:actionerror />
		<s:form action="join" namespace="/" method="POST">
			<sec:csrfInput />
			<s:hidden name="source" value="signup" />
			<dl class="dl-horizontal">
				<dt><s:text name="username"/></dt>
				<dd><s:textfield cssClass="form-control" name='user.username' autofocus="autofocus"/></dd>
				<dt><s:text name="password"/></dt>
				<dd><s:password cssClass="form-control" name='user.password' /></dd>
				<dt><s:text name="user.password2"/></dt>
				<dd><s:password cssClass="form-control" name='user.password2' /></dd>
			<!-- 
				<dt><s:checkbox name="user.enabled"/></dt>
				<dd><s:label key="user.enabled" for="user.enabled"/></dd>
			-->
				<dt><s:text name="user.idNumber"/></dt>
				<dd><s:textfield cssClass="form-control" name='user.idNumber' /></dd>
				<dt><s:text name="user.code"/></dt>
				<dd><s:textfield cssClass="form-control" name='user.code'/></dd>
				<dt><s:text name="user.name"/></dt>
				<dd><s:textfield cssClass="form-control" name='user.name' /></dd>
				<dt><s:text name="user.gender"/></dt>
				<dd class="btn-group" data-toggle="buttons" style="display: block;"><s:radio name="user.gender" cssClass="form-control" list="getCodeList('02')" listKey="fCode" listValue="fValue" /></dd>
				<dt><s:text name="user.department"/></dt>
				<dd>
				    <s:select name="p.department" class="form-control"
	                       emptyOption="true"
	                       list="getCodeList('01')"
	                       listKey="fCode" listValue="fValue"/></dd>
				<dt><s:text name="user.comment"/></dt>
				<dd><s:textarea cssClass="form-control" name='user.comment' /></dd>
				<dt></dt>
				<dd style="text-align: right;">
					<s:submit key="register" class="btn btn-primary active"></s:submit>
                    <!--<s:reset key="reset" class="btn btn-default"></s:reset>-->
		            <s:a action="login" namespace="/" cssClass="btn btn-link">
		                <s:text name="back" />
		                <s:param name="source">-1</s:param>
		            </s:a>
				</dd>
			</dl>
		</s:form>
	</div>
    </div>
</body>
</html>
