package day20.dao.mapper;

import day20.entity.Mood;
import day20.util.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MoodMapper implements RowMapper<Mood> {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getLogger(MoodMapper.class);

    public List<Mood> mapperList(ResultSet rs) throws SQLException {
        logger.debug("获得t_mood表中数据，这是t_mood表与Mood类的映射");
        List<Mood> list = new ArrayList<Mood>();

        /*INSERT INTO `WBJ19092`.`mood_mood`(`mood_id`, `mood_title`, `mood_content`, `mood_create_time`, `mood_user_login`) VALUES (1, '三月你好', '2月在相聚离别中匆匆走过,3月不期而遇,人间的3月春意盎然,是生机勃勃的未来,春满人间的3月,这世间欠你的温柔,他会加倍还你 2月再见3月你好 愿你的三月如同春风,一夜吹醒满山红', '2020-03-01 09:51:50', 'LCQJOYCE');
         */
        //数据封装 rs是一个只读只进，只读--不能用于修改，只进--不能回头
        while (rs.next()) {
            System.out.println("mood_rs不为空");
            Mood mood = new Mood();
            mood.setMoodId(rs.getInt("mood_id"));
            mood.setMoodTitle(rs.getString("mood_title"));
            mood.setMoodContent(rs.getString("mood_content"));
            try {
                mood.setMoodDate(rs.getDate("mood_create_time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mood.setMoodUserlogin(rs.getString("mood_user_login"));
            list.add(mood);
        }
        logger.debug("MoodMapper数据分装成功");
        return list;
    }

}
