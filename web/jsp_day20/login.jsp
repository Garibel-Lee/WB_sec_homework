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
		$(function(){
			$("#btnSubmit").click(function(){
				var str = $("#userPwd").val();
				$("#userPwd").val(str);// ke加密
				//手动提交表单
				var  f = $("#form1");
				f.submit();
			});
		});
	</script>
  </head>
  <body>
    <h1>登录页面</h1>

    <form action="/jsp_day20/login.do" method="post" id="form1">
    	<table border="1" >
    		<tr><td>用户名</td><td><input type="text" id="userName" name="userName" placeholder="用户名" /></td><td>验证信息<div id="div"></div></td></tr>
    		<tr><td>密码</td><td><input type="password" name="userPwd"  id="userPwd" /></td><td>验证信息</td></tr>
    		<tr><td colspan="3"><input type="button" id="btnSubmit"  value="登录" /><input type="reset" value="重填" /> </td></tr>
    	</table>
    </form>
  </body>
</html>
