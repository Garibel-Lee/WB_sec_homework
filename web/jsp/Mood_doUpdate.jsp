<%@page import="day15.entity.Mood" %>
<%@page import="day15.entity.User" %>
<%@page import="day15.service.MoodService" %>
<%@ page language="java" import="day15.service.UserService" pageEncoding="UTF-8" %>
<%@ page import="day15.util.BeanFactory" %>
<%@ page import="java.util.Date" %>

<%
    //post获得数据前修改字符集
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf8");
    //通过名称获得提交的数据
    Integer moodId = Integer.parseInt(request.getParameter("moodId"));
    String moodTitle = request.getParameter("moodTitle");
    String moodUserlogin = request.getParameter("moodUserlogin");
    String moodContent = request.getParameter("moodContent");
    Date moodDate = new Date();
    Mood mood = new Mood();
    MoodService service = (MoodService) BeanFactory.getObject("moodService");//获得了业务逻辑的功能代码
    mood.setMoodId(moodId);
    mood.setMoodTitle(moodTitle);
    mood.setMoodContent(moodContent);
    mood.setMoodDate(moodDate);
    mood.setMoodUserlogin(moodUserlogin);
    int count = service.update(mood);
    User user=new User();
    UserService ser = (UserService) BeanFactory.getObject("userService");//获得了业务逻辑的功能代码
    user = ser.getUserByName(mood.getMoodUserlogin());
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
    if (count == 1) { //成功
        request.setAttribute("id", user.getUserId());
        request.getRequestDispatcher("/jsp/showAllMoods.jsp").forward(request, response);
    } else { //失败
        request.getRequestDispatcher("/jsp/fail.jsp").forward(request, response);
    }

%>
	