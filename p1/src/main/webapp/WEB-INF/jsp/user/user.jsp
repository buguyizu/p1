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
      	
        <jsp:include page="../menu_side.jsp">
            <jsp:param name="index" value="x"/>
        </jsp:include>
        <div class="col-sm-9 col-md-10 main">
          <ol class="breadcrumb">
            <li><a href="${base}/home">主页</a></li>
            <li><a href="#">员工管理</a></li>
            <li class="active">员工查询</li>
          </ol>

          <h1 class="page-header">员工信息</h1>
          <div class="alert alert-success" role="alert">成功</div>
          <div class="alert alert-info" role="alert">信息</div>
          <div class="alert alert-warning" role="alert">警告</div>
          <s:form action="search">
            <div class="row">
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.cd"/></span>
                  <s:textfield name="p.cd" cssClass="form-control" placeholder="G0001" aria-describedby="员工编号" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="user.name"/></span>
                  <s:textfield name="p.name" cssClass="form-control" placeholder="王红" aria-describedby="员工姓名" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="department"/></span>
                  	<s:select label="department" class="form-control" placeholder="..." aria-describedby="部门"
				       name="p.department" emptyOption="true"
				       list="getCodeList('01')"
				       listKey="fCode" listValue="fValue"
				       value="p.department"
					/>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group form-group">
                  <span class="input-group-addon"><s:text name="gender"/></span>
                  <div class="form-control btn-group" data-toggle="buttons">
                    <label class="btn btn-primary active">
                      <input type="radio" name="options" id="option1" autocomplete="off" checked>男
                    </label>
                    <label class="btn btn-primary">
                      <input type="radio" name="options" id="option2" autocomplete="off">女
                    </label>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group">
                  <span class="input-group-addon"><s:text name="status.login"/></span>
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
            </div>
          
            <div class="btn-group btn-group-justified" role="group" aria-label="按钮">
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
          <h2 class="sub-header">信息一览</h2>
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