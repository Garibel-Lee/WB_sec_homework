<%@page import="day20.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
		//判断用户是否登录
		User user = (User)request.getSession().getAttribute("user");
		if(null== user){
			//说明 没有登录，跳转到登录页面
			response.sendRedirect("/jsp_day20/login.jsp");
		}

 %>
