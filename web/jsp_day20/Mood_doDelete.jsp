<%@page import="day20.entity.Mood"%>
<%@page import="day20.entity.User"%>
<%@page import="day20.service.MoodService"%>
<%@ page import="day20.service.UserService" %>
<%@ page import="day20.util.BeanFactory" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String moodId = request.getParameter("moodId");
	MoodService service = (MoodService)BeanFactory.getObject("moodService");
	Mood mood =new Mood();
	mood=service.getMoodById(Integer.parseInt(moodId));
	int count = service.delete(mood);
	User user=new User();
	UserService ser = (UserService) BeanFactory.getObject("userService");//获得了业务逻辑的功能代码
	user = ser.getUserByName(mood.getMoodUserlogin());

	if(count==1){ //成功
		request.setAttribute("id", user.getUserId());
		request.getRequestDispatcher("/jsp_20/showAllMoods.jsp").forward(request, response);
	} else { //失败
		request.getRequestDispatcher("/jsp_20/fail.jsp").forward(request, response);
	}
 %>