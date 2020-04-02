<%@ page language="java" import="day20.entity.User" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    User user = (User) request.getSession().getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'success.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript">
        function loginOut() {
            if (confirm("是否退出本系统?")) {
                location.href = "/jsp_20/LoginOut.do";
            }
        }
    </script>
</head>

<body>
<%--<%@include file="./isLogin.jsp" %>--%>
用户<%=user.getUserLogin()%>空间
<br/>&nbsp;
<a href="/jsp_day20/showallMoods.do">显示心情</a><br/>
<a href="javascript:loginOut()">退出</a><br/>
</body>
</html>
