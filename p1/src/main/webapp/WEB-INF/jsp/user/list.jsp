<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="user.manager"/></title>
	<link href="${base}/css/list.css" rel="stylesheet" type="text/css" />
	<link href="${base}/css/displaytag.css" rel="stylesheet" type="text/css" />
	<link href="${base}/css/alternative.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="container-fluid">
      <div class="row">
      	<%@ include file="../menu_side.jsp" %>
        <div class="col-sm-9 col-md-10 main">
          <h3 class="page-header"><s:text name="user.manager"/></h3>
          <s:form action="search">
            <div class="row">
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="username"/></span>
                  <s:textfield name="p.username" cssClass="form-control" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.code"/></span>
                  <s:textfield name="p.cd" cssClass="form-control" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.name"/></span>
                  <s:textfield name="p.name" cssClass="form-control" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="department"/></span>
                  	<s:select class="form-control"
				       name="p.department" emptyOption="true"
				       list="getCodeList('01')"
				       listKey="fCode" listValue="fValue"/>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="gender"/></span>
                  <div class="form-control btn-group" data-toggle="buttons">
                    <s:radio name="p.status" list="getCodeList('02')" listKey="fCode" listValue="fValue" />
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group">
                  <span class="input-group-addon"><s:text name="user.enabled"/></span>
                  <div class="form-control btn-group" data-toggle="buttons">
                    <s:radio name="p.status" list="getCodeList('03')" listKey="fCode" listValue="fValue" />
                  </div>
                </div>
              </div>
            </div>
          
            <div class="btn-group btn-group-justified" role="group">
              <div class="btn-group" role="group">
                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>清空</button>
              </div>
              <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
              </div>
              <div class="btn-group" role="group">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalCreate"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新建</button>
              </div>
            </div>
          </s:form>
          <h3 class="sub-header">信息一览</h3>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>姓名</th>
                  <th>性别</th>
                  <th>部门</th>
                  <th>登录状态</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td><a href="#modalEdit" data-toggle="modal">G0001</a></td>
                  <td>王红</td>
                  <td>女</td>
                  <td>账务</td>
                  <td>有效</td>
                </tr>
                <tr>
                  <td><a href="#modalEdit" data-toggle="modal">G0002</a></td>
                  <td>王红</td>
                  <td>女</td>
                  <td>账务</td>
                  <td>有效</td>
                </tr>
              </tbody>
            </table>
          </div>

          <nav>
            <ul class="pagination">
              <li class="disabled">
                <a href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
</body>
</html>