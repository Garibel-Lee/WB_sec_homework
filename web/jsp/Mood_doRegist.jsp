<%@page import="day15.entity.Mood" %>
<%@page import="day15.service.MoodService" %>
<%@page import="day15.service.UserService" %>
<%@ page import="day15.util.BeanFactory" %>
<%@ page import="java.util.Date" %>
<%@ page import="day15.entity.User" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<%
    //post获得数据前修改字符集
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf8");
    //通过名称获得提交的数据

    //通过名称获得提交的数据

    String moodTitle = request.getParameter("moodTitle");
    String moodUserlogin = request.getParameter("moodUserlogin");
    String moodContent = request.getParameter("moodContent");
    Mood mood = new Mood();
    MoodService service = (MoodService) BeanFactory.getObject("moodService");//获得了业务逻辑的功能代码
    mood.setMoodTitle(moodTitle);
    mood.setMoodContent(moodContent);
    mood.setMoodDate(new Date());
    mood.setMoodUserlogin(moodUserlogin);
    int count = service.insert(mood);
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
        ser.addSorce(user);
        request.setAttribute("id", user.getUserId());
        request.getRequestDispatcher("/jsp/showAllMoods.jsp").forward(request, response);
    } else { //失败
        request.getRequestDispatcher("/jsp/fail.jsp").forward(request, response);
    }

%>
	