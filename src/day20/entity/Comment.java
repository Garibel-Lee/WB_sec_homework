package day20.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：LCQJOYCE
 * @date ：Created in 2020/3/1 9:40
 * @description：作为user类的一个包装类修饰其属性
 * @version: $
 */
public class Comment {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int commentId;//用户编号
    private String commentContent;//密码
    private String commentUserlogin;//登录用户名
    private String commentMoodid;
    private Date commentDate;//心情创建时间

    public String getCommentMoodid() {
        return commentMoodid;
    }

    public void setCommentMoodid(String commentMoodid) {
        this.commentMoodid = commentMoodid;
    }

    public int getcommentId() {
        return commentId;
    }

    public void setcommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getcommentContent() {
        return commentContent;
    }

    public void setcommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getcommentDate() {
        return commentDate;
    }

    public void setcommentDate(Date commentDate) throws ParseException {
        String str = sdf.format(commentDate);
        this.commentDate = sdf.parse(str);
    }

    public String getcommentUserlogin() {
        return commentUserlogin;
    }

    public void setcommentUserlogin(String commentUserlogin) {
        this.commentUserlogin = commentUserlogin;
    }


}
