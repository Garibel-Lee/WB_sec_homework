<%@page import="day15.entity.User"%>
<%@page import="day15.util.BeanFactory"%>
<%@page import="day15.service.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	UserService service = (UserService)BeanFactory.getObject("userService");
	User user =new User();
	user.setUserId(Integer.parseInt(id));
	int count = service.delete(user);
	if(count==1){ //成功
		response.sendRedirect("/jsp/success.jsp");
	}else{ //失败
		response.sendRedirect("/jsp/fail.jsp");
	}
 %>