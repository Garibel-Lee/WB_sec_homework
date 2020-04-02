package day20.dao.impl;

import day20.dao.CommentDao;
import day20.dao.mapper.CommentMapper;
import day20.entity.Comment;
import day20.entity.Mood;
import day20.util.JdbcTemplate;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*INSERT INTO `WBJ19092`.`t_Comment`(`Comment_id`, `Comment_title`, `Comment_content`, `Comment_create_time`, `Comment_user_login`) VALUES (1, '三月你好', '2月在相聚离别中匆匆走过,3月不期而遇,人间的3月春意盎然,是生机勃勃的未来,春满人间的3月,这世间欠你的温柔,他会加倍还你 2月再见3月你好 愿你的三月如同春风,一夜吹醒满山红', '2020-03-01 09:51:50', 'LCQJOYCE');
 */
public class CommentDaoImpl implements CommentDao {
	private static Logger logger = Logger.getLogger(CommentDaoImpl.class);
	//格式化日期
	private static SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
	public int insert(Comment comment)  throws SQLException {
		logger.debug("在CommentDaoImpl类中，调用insert方法");
		String sql="insert into t_comment(comm_content,comm_mood_id,comm_create_time,comm_user_login)values(?,?,?,?)";
		String[] objects = {comment.getcommentContent(),comment.getCommentMoodid(),format.format(new Date()),comment.getcommentUserlogin()};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行insert方法的返回结果是："+count);
		return count;
	}

	public int update(Comment comment)  throws SQLException{
		logger.debug("在CommentDaoImpl类中，调用UPDATE方法");
		String sql="UPDATE t_comment set comm_content=?,Comment_content=?,Comment_create_time=?,Comment_user_login=? where Comment_id=?";
		String[] objects = {comment.getcommentContent(),format.format(comment.getcommentDate()),comment.getcommentUserlogin(),comment.getcommentId()+""};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行update返回结果是："+count);
		return count;
	}

	public int delete(Comment comment)  throws SQLException {
		logger.debug("在UserDaoImpl类中，调用insert方法");
		String sql="delete from t_Comment where Comment_id=?";//所有的占位符必须是英文的问号
		String[] objects = {comment.getcommentId()+""};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行delete返回结果是："+count);
		return count;
	}

	public Comment getCommentById(int id) {
		logger.debug("在CommentDaoImpl类中，调用getCommentById方法");
		String sql = "select * from t_comment where comm_id=?";
		String[] objects={id+""};
		List<Comment> list =JdbcTemplate.executeQuery(sql, new CommentMapper(), objects);
		if(list.size()>0){
			return list.get(0); //查到了
		}
		return null;//没有查到
	}

	public List<Comment> getAllComments(Mood mood) {
		logger.debug("在CommentDaoImpl类中，调用getAllComments方法");
		String[] objects={mood.getMoodId()+""};
		String sql = "select * from t_comment where comm_mood_id=? ";
		return JdbcTemplate.executeQuery(sql, new CommentMapper(),objects);
	}

	public List<Comment> getCommentsPerPage(int currentPage, int size,int moodid) throws SQLException {
		logger.debug("在CommentDaoImpl类中，调用getCommentsPerPage方法");
		int begin = (currentPage-1)*size; //当前页-1 再乘以显示的大小
		//如果使用数据分页，只能使用字符串拼接的方式
		String sql="select * from t_comment where comm_mood_id=? LIMIT "+begin+" , "+size+"  ";
		//limit 不支持占位符
		//String sql="select * from t_Comment LIMIT ?,?";
		String[] objects={moodid+""};
		return JdbcTemplate.executeQuery(sql, new CommentMapper(), objects);
	}

}
