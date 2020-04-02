package day20.action;

import day20.entity.User;
import day20.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction {
    private static Logger logger = Logger.getLogger(UserAction.class);
    UserService userService = null;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //登录代码
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultString = "";
        logger.debug("执行UserAction类的login方法");
        //通过名称获得提交的数据
        String name = request.getParameter("userName");
        String pwd = request.getParameter("userPwd");
        User user = new User();
        user.setUserLogin(name);
        user.setUserPwd(pwd);
        User result = userService.login(user);
        // 请求转发
        if (null != result) { //成功
            request.getSession().setAttribute("user", result);//放置session
            resultString = "success";
            logger.debug(resultString);
        } else {
            resultString = "fail";
            logger.debug(resultString);
        }
        System.out.println("自定义mvc解析并处理成功");
        return resultString;
    }

    //注册
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("自定义mvc的注册功能");
        String resultString = "";
        String name = request.getParameter("userName");
        String pwd = request.getParameter("userPwd");
        //获得一组爱好
        String[] hobbys = request.getParameterValues("hobbys");
        String hobby = "";
        for (int i = 0; i < hobbys.length; i++) {
            hobby += hobbys[i] + ",";
        }

        User user = new User();

        user.setUserLogin(name);
        user.setUserPwd(pwd);

        int count = userService.insert(user);
        if (count == 1) {
            resultString = "success";
        } else {
            resultString = "fail";
        }
        return resultString;
    }
}
