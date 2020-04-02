<%@page import="java.text.SimpleDateFormat"%>
<%@page import="day15.entity.User"%>
<%@page import="day15.service.*"%>
<%@page import="day15.util.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示所有用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%
	UserService service = (UserService)BeanFactory.getObject("userService");//获得了业务逻辑的功能代码
	List<User> users = service.getAllUsers();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
	<script type="text/javascript">
		function doDelete(id){
			if(confirm("是否删除该条数据？")){
				location.href="<%=basePath%>/jsp/doDelete.jsp?id="+id;
			}
		}
	</script>
  </head>
  
  <body>
    <h1>显示所有用户(使用表格显示数据)</h1>
    <hr />
     <table border="1">
     	<tr><th>编号</th><th>登录名</th><th>密码</th><th>创建日期</th><th>积分</th><th>修改</th><th>删除</th></tr>
     	<!-- 使用循环打印 -->
     	<%
     		for(int i=0;i<users.size();i++){
     			User u = users.get(i);
     	%>
     			<tr><td><%=u.getUserId() %></td><td><%=u.getUserLogin() %></td><td><%=u.getUserPwd() %></td>
     			<td><%=format.format(u.getUserDate()) %></td><td><%=u.getUserScore() %></td>
     			<td><a href="/jsp/modify.jsp?id=<%=u.getUserId() %>">修改</a></td>
     			<!-- 直接删除风险太大，如果能有提示，相对就好很多 使用js的confirm 用来提示 
     				<a href="doDelete.jsp?id=<%=u.getUserId() %>">删除</a>
     			-->
     			<td><a href="javascript:doDelete('<%=u.getUserId() %>')">删除</a></td></tr>
     	<%
     		}
     	 %>
     </table>
  </body>
</html>
