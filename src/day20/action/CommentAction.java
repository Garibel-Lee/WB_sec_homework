package day20.action;

import day20.service.CommentService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentAction {
    private static Logger logger = Logger.getLogger(CommentAction.class);
    CommentService commentService = null;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    //展示评论
    public String showallComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultString = "";
        logger.debug("执行CommentAction类的login方法");
        //通过名称获得提交的数据
        int count = 1;
        if (count == 1) {
            resultString = "success";
        } else {
            resultString = "fail";
        }
        return resultString;
    }
}
