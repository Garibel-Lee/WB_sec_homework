package day20.action;

import day20.entity.User;
import day20.service.MoodService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MoodAction {
    private static Logger logger = Logger.getLogger(MoodAction.class);
    MoodService moodService = null;

    public void setMoodService(MoodService moodService) {
        this.moodService = moodService;
    }

    //登录代码
    public String showallMoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultString = "";
        logger.debug("执行MoodAction类的get方法");
        User user =(User)request.getSession().getAttribute("user");
        logger.debug("查看当前登陆用户"+user.toString());
        // 请求转发
        if (null != user.getUserLogin()) { //成功
            resultString = "success";
            logger.debug(resultString);
        } else {
            resultString = "fail";
            logger.debug(resultString);
        }
        System.out.println("申请查看心情并处理成功");
        return resultString;
    }

    public String Mood_doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultString = "";
        logger.debug("执行Mood_doRegist.do");
        User user =(User)request.getSession().getAttribute("user");
        logger.debug("查看当前登陆用户"+user.toString());
        // 请求转发
        if (null != user.getUserLogin()) { //成功
            resultString = "success";
            logger.debug(resultString);
        } else {
            resultString = "fail";
            logger.debug(resultString);
        }
        System.out.println("注册心情");
        return resultString;
    }
}
