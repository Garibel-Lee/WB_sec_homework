package day15.dao.mapper;

import day15.entity.Comment;
import day15.util.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CommentMapper implements RowMapper<Comment> {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getLogger(CommentMapper.class);

    public List<Comment> mapperList(ResultSet rs) throws SQLException {
        logger.debug("获得t_comment表中数据，这是t_comment表与Comment类的映射");
        List<Comment> list = new ArrayList<Comment>();

        /*INSERT INTO `WBJ19092`.`comment_comment`(`comment_id`, `comment_title`, `comment_content`, `comment_create_time`, `comment_user_login`) VALUES (1, '三月你好', '2月在相聚离别中匆匆走过,3月不期而遇,人间的3月春意盎然,是生机勃勃的未来,春满人间的3月,这世间欠你的温柔,他会加倍还你 2月再见3月你好 愿你的三月如同春风,一夜吹醒满山红', '2020-03-01 09:51:50', 'LCQJOYCE');
         */
        //数据封装 rs是一个只读只进，只读--不能用于修改，只进--不能回头
        while (rs.next()) {
            System.out.println("comment_rs不为空");
            Comment comment = new Comment();
            comment.setcommentId(rs.getInt("comm_id"));
            comment.setcommentContent(rs.getString("comm_content"));
            try {
                comment.setcommentDate(rs.getDate("comm_create_time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            comment.setcommentUserlogin(rs.getString("comm_user_login"));
            comment.setCommentMoodid(rs.getString("comm_mood_id"));
            list.add(comment);
        }
        logger.debug("CommentMapper数据分装成功");
        return list;
    }

}
