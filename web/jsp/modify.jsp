<%@page import="day15.entity.User"%>
<%@page import="day15.util.BeanFactory"%>
<%@page import="day15.service.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%
	String id = request.getParameter("id");
	UserService service = (UserService)BeanFactory.getObject("userService");
	User result = service.getUserById(Integer.parseInt(id));//查到相应的结果
	
	%>
  </head>
  
  <body>
     <form action="/jsp/doUpdate.jsp" method="post">
    	<table border="1" >
 
    		<tr><td>用户名</td><td><input type="text" id="userName" name="userName" 
    		placeholder="用户名" value="<%=result.getUserLogin() %>" readonly="readonly"/></td><td>验证信息</td></tr>
    		<tr><td>密码</td><td><input type="password" name="userPwd"  id="userPwd"  /></td><td>验证信息</td></tr>
    		<tr><td>积分</td><td><input type="text" name="userScore" id="userScore" value="<%=result.getUserScore() %>" />
    		</td><td>验证信息</td></tr>
    		
    		<tr><input type="hidden" name="id" value="<%=result.getUserId() %>"/><td colspan="3"><input type="submit"  value="注册" />
    		<input type="reset" value="重填" /> </td></tr>
    	</table>
    </form>
  </body>
</html>
