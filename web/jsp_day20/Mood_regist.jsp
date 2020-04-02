<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName=request.getParameter("userName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>注册页面</h1>
    <!-- 表单默认的提交方式是get方式 
    
    -->
    <form action="/jsp_day20/Mood_doRegist.jsp" method="post">
		<input name="moodUserlogin" readonly="readonly" id="moodUserlogin" value="<%=userName%> ">
    	<table border="1" >
    		<tr><td>心情标题</td><td><input type="text" id="moodTitle" 	name="moodTitle"	/></td><td>验证信息</td></tr>
    		<tr><td>心情内容</td><td><input type="text" id="moodContent" 	name="moodContent"  /></td><td>验证信息</td></tr>
    		<tr><td colspan="3"><input type="submit"  value="添加心情记录" /><input type="reset" value="重填" /> </td></tr>
    	</table>

    </form>
  </body>
</html>
