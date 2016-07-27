<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>
<!DOCTYPE html>
<html>
<head>
	<title>bom</title>
	<link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/alternative.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<s:fielderror />
	<s:actionerror />
	<s:actionmessage />
	<s:form action="search" cssStyle="margin-top: 80px;">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="input-group">
			<s:label key="cd.item" for="search_p_itemCd" cssClass="input-group-addon" />
			<s:textfield name="p.itemCd" cssClass="form-control" />
		</div>
		<div class="input-group">
			<s:label key="cd.item.parent" for="search_p_parentItemCd" cssClass="input-group-addon" />
			<s:textfield name="p.parentItemCd" cssClass="form-control" />
		</div>
		<div class="input-group">
			<s:label key="level" for="search_p_level" cssClass="input-group-addon" />
			<s:textfield name="p.level" cssClass="form-control" />
		</div>
		<s:submit key="transform" action="transform"></s:submit>
		<s:submit key="search" action="search"></s:submit>
	</s:form>
	
    <%--http://www.cnblogs.com/chinafine/articles/1801375.html 
    http://www.tuicool.com/articles/rE7Zve
    http://www.cnblogs.com/chinafine/articles/1801338.html
    http://www.cnblogs.com/chinafine/articles/1801376.html
    http://www.cnblogs.com/chinafine/articles/1801344.html
    
If the default values of these properties are used, the href of the sort links will look like this:
  http://foo.bar.com/context/requestUri?sort=name&dir=asc&originalParameters=originalValues.
The href of the pagination links will look like this:
  http://foo.bar.com/context/requestUri?sort=name&dir=asc&page=5&originalParameters=originalValues
    
    
    --%>
    <div id="tableDiv">
		<display:table name="p.list" id ="${tableId}" sort="external" defaultsort="1"
			pagesize="${p.objectsPerPage}" size="${p.fullListSize}" partialList="true"
			requestURI="./list" export="false">
	        <display:column property="itemCd" title='${action.getText("cd.item")}' sortable="true" sortName="f2" /> 
	        <display:column property="parentItemCd" title='${action.getText("cd.item.parent")}' sortable="true" sortName="f1" /> 
		</display:table>
	</div>
</body>
</html>