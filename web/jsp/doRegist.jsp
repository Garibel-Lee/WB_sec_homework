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
		//get先获得数据然后转码
		//name = new String(name.getBytes("iso-8859-1"),"utf-8");
		String pwd = request.getParameter("userPwd");
		//out.println("用户名："+name+"<br />");
		//out.println("密码："+pwd+"<br />");
		User user = new User();
		UserService service = (UserService)BeanFactory.getObject("userService");//获得了业务逻辑的功能代码
		user.setUserLogin(name);
		user.setUserPwd(pwd);
		int count = service.insert(user);
		//out.println(count);
		//response 响应对象，服务器处理完毕后，给用户一个返回结果
		//重定向
		/*
		if(count==1){ //成功
			response.sendRedirect("success.jsp");
		}else{ //失败
			response.sendRedirect("fail.jsp");
		}
		*/
	
		// 请求转发
		if(count==1){ //成功
			request.getRequestDispatcher("/jsp/success.jsp").forward(request, response);
		}else{ //失败
			request.getRequestDispatcher("/jsp/fail.jsp").forward(request, response);
		}
		
	 %>
	