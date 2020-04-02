<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="day15.entity.Mood"%>
<%@page import="day15.entity.User"%>
<%@page import="day15.service.MoodService"%>
<%@ page language="java" import="day15.service.UserService" pageEncoding="UTF-8"%>
<%@ page import="day15.util.BeanFactory" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示用户心情</title>
	  <%@ page isELIgnored ="false" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%
	MoodService service = (MoodService)BeanFactory.getObject("moodService");//获得了业务逻辑的功能代码
	UserService service2 = (UserService)BeanFactory.getObject("userService");
	String id =session.getAttribute("id").toString();
	User user = service2.getUserById(Integer.parseInt(id));//查到相应的结果
	List<Mood> moods = service.getAllMoods(user);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



		  List<Mood> PageMoods = null;
		  //集合获得了，如何让EL表达式可以访问
		  pageContext.setAttribute("list", moods);
		  String currPage=request.getParameter("currentPage");
		  int size=2;
		  //9条数据，显示为三页,+1是处理不满一页的情况
		  int totalCount = moods.size()%size==0 ?  moods.size() /size: moods.size()/size+1;
		  if(null	!= currPage){
			  PageMoods = service.getMoodsPerPage(Integer.parseInt(currPage), size,user.getUserLogin());
			  pageContext.setAttribute("currentPage", Integer.parseInt(currPage));
		  }else{
			  PageMoods = service.getMoodsPerPage(1, size,user.getUserLogin());
			  pageContext.setAttribute("currentPage", "1");//如果是null代表当前页是第一页
		  }
		  pageContext.setAttribute("some", PageMoods);
		  pageContext.setAttribute("totalCount",totalCount);
		  pageContext.setAttribute("total",moods.size());
	  %>
	<script type="text/javascript">
		function doDelete(id){
			if(confirm("是否删除该条数据？")){
				location.href="<%=basePath%>/jsp/Mood_doDelete.jsp?moodId="+id;
			}
		}
	</script>
  </head>
  
  <body>
    <h1>显示用户<%=user.getUserLogin() %>所有心情</h1>
    <hr />
   <%--  <table border="1">
     	<tr><th>编号</th><th>标题</th><th>内容</th><th>创建日期</th><th>用户名</th><th>修改</th><th>删除</th></tr>
     	<!-- 使用循环打印 -->
     	<%
     		for(int i=0;i<moods.size();i++){
     			Mood u = moods.get(i);
     	%>
     			<tr><td><%=u.getMoodId() %></td><td><%=u.getMoodTitle() %></td><td><%=u.getMoodContent() %></td>
     			<td><%=format.format(u.getMoodDate()) %></td><td><%=u.getMoodUserlogin() %></td>
     			<td><a href="/jsp/Mood_modify.jsp?moodId=<%=u.getMoodId() %>">修改</a></td>
     			<!-- 直接删除风险太大，如果能有提示，相对就好很多 使用js的confirm 用来提示 
     				<a href="doDelete.jsp?id=<%=u.getMoodId() %>">删除</a>
     			-->
     			<td><a href="javascript:doDelete('<%=u.getMoodId() %>')">删除</a></td></tr>
     	<%
     		}
     	 %>
     </table>--%>
	<a href="/jsp/Mood_regist.jsp?userName=<%=user.getUserLogin() %>">添加心情记录</a>

	<table border="1"  class="">
		<thead>
		<tr><th>编号</th><th>标题</th><th>内容</th><th>日期</th><th>发布人</th><th>评论</th><th>***</th><th>***</th></tr>
		</thead>
		<tbody>
		<c:forEach var="mood" items="${some}">
			<tr><td>${mood.moodId }</td><td><a href="/jsp/Mood_info.jsp?moodId=${mood.moodId }">${mood.moodTitle }</a></td>
				<td>${mood.moodContent }</td>
				<td>${mood.moodDate }</td><td>${mood.moodUserlogin }</td>
				<td><a href="/jsp/Comment_regist.jsp?userName=<%=user.getUserLogin() %>&moodId=${mood.moodId }">添加评论</a></td>
				<td><a href="/jsp/Mood_modify.jsp?moodId=${mood.moodId}">修改</a></td>
				<td><a href="javascript:doDelete('${mood.moodId}')">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot></tfoot>
	</table>
	<a href="/jsp/showAllMoods.jsp?currentPage=1">首页</a> &nbsp;
	<c:if test="${currentPage==1}" var="pre">
		<a href="/jsp/showAllMoods.jsp?currentPage=1">上一页</a> &nbsp;
	</c:if>
	<c:if test="${ ! pre }">
		<a href="/jsp/showAllMoods.jsp?currentPage=${currentPage-1 }">上一页</a> &nbsp;
	</c:if>
	<c:if test="${currentPage==totalCount}" var="next">
		<a href="/jsp/showAllMoods.jsp?currentPage=${totalCount }">下一页</a>&nbsp;
	</c:if>
	<c:if test="${ ! next }">
		<a href="/jsp/showAllMoods.jsp?currentPage=${currentPage+1 }">下一页</a>&nbsp;
	</c:if>
	<a href="/jsp/showAllMoods.jsp?currentPage=${totalCount}">末 页</a><br />
	一共${totalCount }页,一共${total }条

  </body>
</html>
