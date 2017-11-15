<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="col-sm-3 col-md-2 sidebar">
<%
String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<c:set var="base" value="<%=base %>" scope="request"></c:set>
<c:set var="index" value='<%=request.getParameter("index") %>' scope="request"></c:set>
<!-- 
  <ul class="nav nav-sidebar">
    <li class="active"><a href="./info?source=1">用户信息</a></li>
    <li><a href="./list">用户管理 </a></li>
    <li><a href="#">责任管理</a></li>
  </ul>
 -->
  <div class="panel-group nav-sidebar" role="tablist">
    <s:iterator value="getMenuList()" var="menuMap" status="st">
    <div class="panel panel-default">
      <div class="panel-heading panel-title" role="tab">
        <s:iterator value="#menuMap.h" var="menu">
          <span class="glyphicon glyphicon-menu-hamburger"></span>
          <a role="button" data-toggle="collapse" href="#g${st.index}">${menu.menuName}</a>
        </s:iterator>
      </div>
      <div id="g${st.index}" class="panel-collapse collapse in" role="tabpanel">
        <div class="list-group">
          <s:iterator value="#menuMap.b" var="menu">
	        <c:if test="${menu.menuCode eq index}">
		      <a class="list-group-item active" href="${base}<s:property value="menuUrl" />">${menu.menuName}</a>
		    </c:if>
		    <c:if test="${menu.menuCode ne index}">
		      <a class="list-group-item" href="${base}<s:property value="menuUrl" />">${menu.menuName}</a>
		    </c:if>
	      </s:iterator>
        </div>
      </div>
    </div>
    </s:iterator>
  </div>
</div>