<%@page import="day15.entity.Mood"%>
<%@page import="day15.service.CommentService"%>
<%@ page import="day15.service.MoodService" %>
<%@ page import="day15.util.BeanFactory" %>
<%@ page language="java" pageEncoding="UTF-8"%>
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
	String id = request.getParameter("moodId");
	MoodService service = (MoodService)BeanFactory.getObject("moodService");
	Mood result = service.getMoodById(Integer.parseInt(id));//查到相应的结果

	%>
  </head>
  
  <body>
     <form action="/jsp/Mood_doUpdate.jsp" method="post">
    	<table border="1" >
    		<tr><td>用户名</td><td><input type="text" id="moodUserlogin" name="moodUserlogin"
    		placeholder="用户名" value="<%=result.getMoodUserlogin() %>" readonly="readonly"/></td><td>验证信息</td></tr>
    		<tr><td>心情标题</td><td><input type="text" name="moodTitle"  id="moodTitle"  /></td><td>验证信息</td></tr>
    		<tr><td>心情内容</td><td><input type="text" name="moodContent" id="moodContent" value="<%=result.getMoodContent() %>" />
    		</td><td>留白</td></tr>
    		<tr><input type="hidden" name="moodId" value="<%=result.getMoodId() %>"/><td colspan="3"><input type="submit"  value="更新" />
    		<input type="reset" value="重填" /> </td></tr>
    	</table>
    </form>
  </body>
</html>
