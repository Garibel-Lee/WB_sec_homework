<%@page import="day15.util.BeanFactory"%>
<%@page import="day15.service.UserService"%>
<%@page import="day15.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%
		//post获得数据前修改字符集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf8");
		//通过名称获得提交的数据
		String name=request.getParameter("userName");
		String pwd = request.getParameter("userPwd");
		User user = new User();
		UserService service = (UserService)BeanFactory.getObject("userService");//获得了业务逻辑的功能代码
		user.setUserLogin(name);
		user.setUserPwd(pwd);
		User result = service.login(user);
	
		// 请求转发
		if(null!=result){ //成功
			session.setAttribute("id",result.getUserId());
			request.getRequestDispatcher("/jsp/showAllMoods.jsp").forward(request, response);
		}else{ //失败
			request.getRequestDispatcher("/jsp/fail.jsp").forward(request, response);
		}		
	 %>
	