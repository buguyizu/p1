<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="user.info"/></title>
	<style type="text/css">
	   .nav-tabs {
	       margin-bottom: 20px;
	   }
	   .tab-pane dl dt {
	       margin-bottom: 15px;
	   }
	</style>
	<script type="text/javascript">
	   function d() {
		   
	   };
	</script>
</head>
<body>
    <div class="container-fluid">
      <div class="row">
      	<%@ include file="../menu_side.jsp" %>
        <div class="col-sm-9 col-md-10 main">
          <h2 class="page-header"><s:text name="user.info"/></h2>
          <div class="row">
	          <ul class="nav nav-tabs nav-justified" role="tablist">
	            <li role="presentation"><a href="#show" aria-controls="show" role="tab" data-toggle="tab"><s:text name="tab.show"/></a></li>
	            <li role="presentation" class="active"><a href="#update" aria-controls="update" role="tab" data-toggle="tab"><s:text name="tab.update"/></a></li>
	            <li role="presentation"><a href="#change" aria-controls="change" role="tab" data-toggle="tab"><s:text name="tab.changePassword"/></a></li>
	          </ul>
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane" id="show">
		            <dl class="dl-horizontal">
		                <dt><s:text name="user.code"/></dt>
		                <dd>${user.code}</dd>
		                <dt><s:text name="user.name"/></dt>
		                <dd>${user.name}</dd>
		                <dt><s:text name="gender"/></dt>
		                <dd>${user.gender}</dd>
		                <dt><s:text name="department"/></dt>
		                <dd>${user.department}</dd>
		                <dt><s:text name="user.comment"/></dt>
		                <dd>${user.comment}</dd>
		            </dl>
		            <s:hidden name="user.code" />
                    <s:hidden name="user.name" />
                    <s:hidden name="user.gender" />
                    <s:hidden name="user.department" />
                    <s:hidden name="user.comment" />
                </div>
                <div role="tabpanel" class="tab-pane" id="change">
                </div>
			    <div role="tabpanel" class="tab-pane active" id="update">
				    <div style="color:red;">
				        <s:fielderror />
				        <s:actionerror />
				    </div>
			        <s:form action="update" namespace="/user">
			          <sec:csrfInput />
			          <div class="row">
			            <div class="col-md-6">
			              <div class="input-group form-group">
			                <span class="input-group-addon"><s:text name="user.code"/></span>
			                <s:textfield name="p.cd" cssClass="form-control" placeholder="%{getText('placeholder.user.code')}" />
			              </div>
			            </div>
			            <div class="col-md-6">
			              <div class="input-group form-group">
			                <span class="input-group-addon"><s:text name="user.name"/></span>
			                <s:textfield name="p.name" cssClass="form-control" placeholder="%{getText('placeholder.user.name')}" />
			              </div>
			            </div>
			            <div class="col-md-6">
			              <div class="input-group form-group">
			                <span class="input-group-addon"><s:text name="department"/></span>
			                  <s:select name="p.department" class="form-control"
			                     emptyOption="true"
			                     list="getCodeList('01')"
			                     listKey="fCode" listValue="fValue"
			                  />
			              </div>
			            </div>
			            <div class="col-md-6">
			              <div class="input-group form-group">
			                <span class="input-group-addon"><s:text name="gender"/></span>
			                <div class="form-control">
                                <s:radio name="p.gender" list="getCodeList('01')" listKey="fCode" listValue="fValue" class="radio-inline"/>
                            
					        <label class="radio-inline">
							  <input type="radio" name="p.gender" value="1"> 男
							</label>
							<label class="radio-inline">
							  <input type="radio" name="p.gender" value="0"> 女
							</label>
							</div>
			              </div>
			            </div>
			            <div class="col-md-6">
			              <div class="input-group form-group">
			                <span class="input-group-addon"><s:text name="user.enabled"/></span>
			                <div class="form-control btn-group" data-toggle="buttons">
			                  <label class="btn btn-primary active">
			                    <input type="radio" name="options" id="option1" autocomplete="off" checked> 有效
			                  </label>
			                  <label class="btn btn-primary">
			                    <input type="radio" name="options" id="option2" autocomplete="off"> 无效
			                  </label>
			                </div>
			              </div>
			            </div>
			            <div class="col-md-6">
			              <div class="input-group form-group">
			                <span class="input-group-addon"><s:text name="user.comment"/></span>
			                  <textarea class="form-control" rows="3"></textarea>
			                </div>
			              </div>
			            </div>
			            <button type="submit" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-edit"></span><s:text name="edit"/></button>
			          </s:form>
                </div>
			  </div>
          </div>
        </div>
      </div>
    </div>
</body>
</html>