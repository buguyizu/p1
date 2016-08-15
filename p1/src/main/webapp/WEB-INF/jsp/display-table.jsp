<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>login</title>
</head>
<body>
	<s:form action="login" method="get">
	<s:text name="ME001" ></s:text>
	<s:textfield name="user" label="用户名" key="user" />
	<s:password  name="passord" label="密码" key="password" />
	<s:submit key="login" value="提交"></s:submit>
	</s:form>
	<hr/>
	<ul>
	<s:iterator value="list">
    	<li><s:property value="codeCd"/></li>
    </s:iterator>
	</ul>
    
	<s:select label="Months"
       name="months"
       headerKey="-1" headerValue="Select Month"
       list="#{'01':'Jan', '02':'Feb'}"
       value="selectedMonth"
       required="true"
/>
    
<%--
    DisplayTag
    http://www.cnblogs.com/chinafine/articles/1801375.html 
    http://www.tuicool.com/articles/rE7Zve
    http://www.cnblogs.com/chinafine/articles/1801338.html
    http://www.cnblogs.com/chinafine/articles/1801376.html
    http://www.cnblogs.com/chinafine/articles/1801344.html
--%>
    <c:if test="${not empty requestScope.pageHelper}">
		<display:table name="pageHelper" cellspacing="0" cellpadding="0"
		   requestURI="" defaultsort="0" id="item" pagesize="25" class="table"
		   export="false" decorator="cn.sccl.um.web.UserTableWrapper">
		   <display:column property="loginId" title="登录名" style="width: 10%"
		    escapeXml="true" sortable="false" url="/user/edit.do" paramId="id"
		       paramProperty="id" />
		   <display:column property="name" title="昵称" style="width: 10%"
		    escapeXml="true" sortable="false" />
		   <display:column property="email" title="邮箱" style="width: 10%"
		    escapeXml="true" sortable="false" />
		   <display:column property="company.name" title="所属公司名"
		    style="width: 10%" escapeXml="true" sortable="false" />
		   <display:column property="status" title="状态" style="width: 10%"
		    escapeXml="true" sortable="false" />
		   <display:column property="phone" title="移动电话" style="width: 10%"
		    escapeXml="true" sortable="false" />
		   <display:column title="操作" style="width: 10%" sortable="false" property="operateLink" />
		</display:table>
	</c:if>
</body>
</html>