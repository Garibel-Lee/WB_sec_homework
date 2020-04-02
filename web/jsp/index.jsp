<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    这是我的网站hello<br>
    <%
    	out.write("你好！");
    	sayHello("lisi");
    	String stuName="王五";
     %>
     <%!
	//这是声明，相当于在某一个java文件的类中写一个属性，或者是方法
	String name = "张三";
	public void sayHello(String personName){
		//打到控制台上
		System.out.println("你好："+personName);
		System.out.println(name);
		//System.out.println(stuName);
		//如果想打印到页面上,使用out对象，(jsp的隐式对象)
	}
 %>
 <%
 	System.out.println(stuName);
  %>
  <hr />
  <%
  	//for(int i=0;i<5;i++){
  	//	out.write("hello world<br />");
  //	}
   
   %>
   <%
    for(int i=0;i<5;i++){
    //减少一行的java代码，服务器就可以少编译一行，但是jsp易读性，与可维护行大大降低了，这也是为什么不使用jsp
    //处理用户请求的原因。
  	%>
  		hello world123<br />
 	<%
 	}
   %>
   <hr />
   金字塔水平线<br />
   <%--
   	for(int i = 5;i<100;i+=5){
   		out.write("<hr align='center' width='"+i+"%' />");
   	}   
    --%>
     <%
    	for(int i = 1;i<100;i+=5){
  	%>
  		<hr align='center' width='<%=i %>%' />
 	<%
 	}
   %>
  </body>
</html>
