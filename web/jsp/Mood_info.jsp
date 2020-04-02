<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page import="day15.entity.Mood"%>
<%@page import="day15.service.CommentService"%>
<%@ page import="day15.service.MoodService" %>
<%@ page import="day15.util.BeanFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="day15.entity.Comment" %>
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
	String loginname=result.getMoodUserlogin();
	CommentService commentService = (CommentService)BeanFactory.getObject("commentService");
	List<Comment> comments = commentService.getAllComments(result);

	List<Comment> PageMoods = null;
	//集合获得了，如何让EL表达式可以访问
	pageContext.setAttribute("list", comments);
	String currPage=request.getParameter("currentPage");
	int size=2;
	//9条数据，显示为三页,+1是处理不满一页的情况
	int totalCount = comments.size()%size==0 ?  comments.size() /size: comments.size()/size+1;
	if(null	!= currPage){
		PageMoods = commentService.getCommentsPerPage(Integer.parseInt(currPage), size,Integer.parseInt(id));
		pageContext.setAttribute("currentPage", Integer.parseInt(currPage));
	}else{
		PageMoods = commentService.getCommentsPerPage(1, size,Integer.parseInt(id));
		pageContext.setAttribute("currentPage", "1");//如果是null代表当前页是第一页
	}
	pageContext.setAttribute("some", PageMoods);
	pageContext.setAttribute("moodId",id);
	pageContext.setAttribute("totalCount",totalCount);
	pageContext.setAttribute("total",comments.size());

	%>
  </head>
  
  <body>
  <h1>心情详情 评论页面</h1>
    	<table border="1" >
    		<tr><td>用户名</td><td><input type="text" id="moodUserlogin" name="moodUserlogin"
    		placeholder="用户名" value="<%=result.getMoodUserlogin() %>" readonly="readonly"/></td><td>验证信息</td></tr>
    		<tr><td>心情标题</td><td><input readonly="true" type="text" name="moodTitle"  id="moodTitle"  value="<%=result.getMoodTitle() %>"  /></td><td>验证信息</td></tr>
    		<tr><td>心情内容</td><td><input readonly="true" type="text" name="moodContent" id="moodContent" value="<%=result.getMoodContent() %>" />
    		</td><td>留白</td></tr>
    	</table>

		<table border="1"  class="">
			<thead>
			<tr><th>编号</th><th>评论时间</th><th>评论人</th><th>内容</th></tr>
			</thead>
			<tbody>
			<c:forEach var="mood" items="${some}">
				<tr><td>${mood.getcommentId() }</td><td> ${mood.getcommentDate() }</td>
					<td>${mood.getcommentUserlogin() }</td>
					<td>${mood.getcommentContent() }</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
		<a href="/jsp/Mood_info.jsp?moodId=${moodId}&currentPage=1">首页</a> &nbsp;
		<c:if test="${currentPage==1}" var="pre">
			<a href="/jsp/Mood_info.jsp?moodId=${moodId}&currentPage=1">上一页</a> &nbsp;
		</c:if>
		<c:if test="${ ! pre }">
			<a href="/jsp/Mood_info.jsp?moodId=${moodId}&currentPage=${currentPage-1 }">上一页</a> &nbsp;
		</c:if>
		<c:if test="${currentPage==totalCount}" var="next">
			<a href="/jsp/Mood_info.jsp?moodId=${moodId}&currentPage=${totalCount }">下一页</a>&nbsp;
		</c:if>
		<c:if test="${ ! next }">
			<a href="/jsp/Mood_info.jsp?moodId=${moodId}&currentPage=${currentPage+1 }">下一页</a>&nbsp;
		</c:if>
		<a href="/jsp/Mood_info.jsp?moodId=${moodId}&currentPage=${totalCount}">末 页</a><br />
		一共${totalCount }页,一共${total }条


  </body>
</html>
