<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/md5.js"></script>
	<script type="text/javascript">
	</script>
  </head>
  <body>
    <h1>注册页面</h1>
    <form action="/jsp_day20/regist.do" method="post" id="form1">
    	<table border="1" >
    		<tr><td>用户名</td><td><input type="text" id="userName" name="userName" placeholder="用户名"/></td><td>验证信息</td></tr>
    		<tr><td>密码</td><td><input type="password" name="userPwd"  id="userPwd" /></td><td>验证信息</td></tr>
    		<tr><td>确认密码</td><td><input type="password" name="userPwd2" id="userPwd2" /></td><td>验证信息</td></tr>
    		<tr><td>爱好</td><td>
    		<label><input type="checkbox" name="hobbys" id="hobby1" value="足球" />足球</label>
    		<label><input type="checkbox" name="hobbys" id="hobby2" value="篮球" />篮球</label>
    		<label><input type="checkbox" name="hobbys" id="hobby3" value="网球" />网球</label>
    		<label><input type="checkbox" name="hobbys" id="hobby4" value="排球" />排球</label>
    		</td><td>验证信息</td></tr>
    		<tr><td colspan="3"><input type="submit"  id="btnSubmit" value="注册" /><input type="reset" value="重填" /> </td></tr>
    	</table>
    </form>
  </body>
</html>
