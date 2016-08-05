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
       .form-control input[type=radio] {
           margin-top: 5px;
           position: absolute;
       }
       .form-control label {
           font-weight: 400;
           padding-left: 20px;
           padding-right: 10px;
       }
    </style>
    <script type="text/javascript">
        function pageLoad() {
            switch ("${source}") {
            case "2":
            	$('#tabs li:eq(1) a').tab('show')
				break;
            case "3":
                $('#tabs li:eq(2) a').tab('show')
                break;
			default:
                $('#tabs li:eq(0) a').tab('show')
				break;
			}
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
              <ul id="tabs" class="nav nav-tabs nav-justified" role="tablist">
                <li role="presentation"><a href="#show" aria-controls="show" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span><s:text name="tab.info"/></a></li>
                <li role="presentation"><a href="#update" aria-controls="update" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-pencil"></span><s:text name="tab.update"/></a></li>
                <li role="presentation"><a href="#change" aria-controls="change" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-lock"></span><s:text name="tab.changePassword"/></a></li>
              </ul>
              <div class="tab-content">
                <div role="tabpanel" class="tab-pane" id="show">
                    <s:actionmessage/>
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
                    <div style="color:red;">
                        <s:fielderror />
                        <s:actionerror />
                    </div>
                    <s:form action="changePassword">
                      <sec:csrfInput />
                      <s:hidden name="source" />
                      <div class="row">
                          <div class="form-group">
                            <span><s:text name="user.password.origin"/></span>
                            <s:password name="p.passwordOrigin" maxlength="10" cssClass="form-control" autofocus="autofocus" />
                          </div>
                          <div class="form-group">
                            <span><s:text name="user.password.new"/></span>
                            <s:password name="p.passwordNew" maxlength="10" cssClass="form-control" />
                          </div>
                          <div class="form-group">
                            <span><s:text name="user.password2"/></span>
                            <s:password name="p.passwordNew2" maxlength="10" cssClass="form-control" />
                          </div>
                      </div>
                      <button type="submit" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-ok"></span><s:text name="ok"/></button>
                    </s:form>
                </div>
                <div role="tabpanel" class="tab-pane" id="update">
                    <div style="color:red;">
                        <s:fielderror />
                        <s:actionerror />
                    </div>
                    <s:form action="update">
                      <sec:csrfInput />
                      <s:hidden name="source" />
                      <div class="row">
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="user.code"/></span>
                            <s:textfield name="p.cd" cssClass="form-control" autofocus="autofocus" maxlength="20" />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="user.name"/></span>
                            <s:textfield name="p.name" cssClass="form-control" maxlength="20" />
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
                                <s:radio name="p.gender" list="getCodeList('02')" listKey="fCode" listValue="fValue" />
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="input-group form-group">
                            <span class="input-group-addon"><s:text name="user.enabled"/></span>
                            <div class="form-control">
                                <s:radio name="p.enabled" list="getCodeList('03')" listKey="fCode" listValue="fValue" />
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
                        <button type="submit" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-ok"></span><s:text name="ok"/></button>
                      </s:form>
                </div>
              </div>
          </div>
        </div>
      </div>
    </div>
</body>
</html>