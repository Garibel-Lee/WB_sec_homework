package day15.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
/**
 * @author ：LCQJOYCE
 * @date ：Created in 2020/3/1 9:40
 * @description：作为user类的一个包装类修饰其属性
 * @version: $
 */
public class Mood {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int moodId;//用户编号
    private String moodTitle;//登录名
    private String moodContent;//密码
    private Date moodDate;//心情创建时间
    private String moodUserlogin;//登录用户名

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public String getMoodTitle() {
        return moodTitle;
    }

    public void setMoodTitle(String moodTitle) {
        this.moodTitle = moodTitle;
    }

    public String getMoodContent() {
        return moodContent;
    }

    public void setMoodContent(String moodContent) {
        this.moodContent = moodContent;
    }

    public Date getMoodDate() {
        return moodDate;
    }

    public void setMoodDate(Date moodDate) throws ParseException {
        String str = sdf.format(moodDate);
        this.moodDate = sdf.parse(str);
    }

    public String getMoodUserlogin() {
        return moodUserlogin;
    }

    public void setMoodUserlogin(String moodUserlogin) {
        this.moodUserlogin = moodUserlogin;
    }


}
