package day15.dao.impl;

import day15.dao.MoodDao;
import day15.dao.mapper.MoodMapper;
import day15.entity.Mood;
import day15.entity.User;
import day15.util.JdbcTemplate;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/*INSERT INTO `WBJ19092`.`t_mood`(`mood_id`, `mood_title`, `mood_content`, `mood_create_time`, `mood_user_login`) VALUES (1, '三月你好', '2月在相聚离别中匆匆走过,3月不期而遇,人间的3月春意盎然,是生机勃勃的未来,春满人间的3月,这世间欠你的温柔,他会加倍还你 2月再见3月你好 愿你的三月如同春风,一夜吹醒满山红', '2020-03-01 09:51:50', 'LCQJOYCE');
 */
public class MoodDaoImpl implements MoodDao {
	private static Logger logger = Logger.getLogger(MoodDaoImpl.class);
	//格式化日期
	private static SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
	public int insert(Mood mood)  throws SQLException {
		logger.debug("在MoodDaoImpl类中，调用insert方法");
		String sql="insert into t_mood(mood_title,mood_content,mood_create_time,mood_user_login)values(?,?,?,?)";
		String[] objects = {mood.getMoodTitle(),mood.getMoodContent(),format.format(new Date()),mood.getMoodUserlogin()};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行insert方法的返回结果是："+count);
		return count;
	}

	public int update(Mood mood)  throws SQLException{
		logger.debug("在MoodDaoImpl类中，调用UPDATE方法");
		String sql="UPDATE t_mood set mood_title=?,mood_content=?,mood_create_time=?,mood_user_login=? where mood_id=?";
		String[] objects = {mood.getMoodTitle(),mood.getMoodContent(),format.format(mood.getMoodDate()),mood.getMoodUserlogin(),mood.getMoodId()+""};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行update返回结果是："+count);
		return count;
	}

	public int delete(Mood mood)  throws SQLException {
		logger.debug("在UserDaoImpl类中，调用insert方法");
		String sql="delete from t_mood where mood_id=?";//所有的占位符必须是英文的问号
		String[] objects = {mood.getMoodId()+""};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行delete返回结果是："+count);
		return count;
	}

	public Mood getMoodById(int id) {
		logger.debug("在MoodDaoImpl类中，调用getMoodById方法");
		String sql = "select * from t_mood where mood_id=?";
		String[] objects={id+""};
		List<Mood> list =JdbcTemplate.executeQuery(sql, new MoodMapper(), objects);
		if(list.size()>0){
			return list.get(0); //查到了
		}
		return null;//没有查到
	}

	public List<Mood> getAllMoods(User user) {
		logger.debug("在MoodDaoImpl类中，调用getAllMoods方法");
		String[] objects={user.getUserLogin()};
		String sql = "select * from t_mood where mood_user_login=? ";
		return JdbcTemplate.executeQuery(sql, new MoodMapper(),objects);
	}

	public List<Mood> getMoodsPerPage(int currentPage, int size,String moodlogin) throws SQLException {
		logger.debug("在MoodDaoImpl类中，调用getMoodsPerPage方法");
		int begin = (currentPage-1)*size; //当前页-1 再乘以显示的大小
		//如果使用数据分页，只能使用字符串拼接的方式
		String sql="select * from t_mood where mood_user_login=? LIMIT "+begin+" , "+size+"  ";
		//limit 不支持占位符
		//String sql="select * from t_mood LIMIT ?,?";
		String[] objects={moodlogin};
		return JdbcTemplate.executeQuery(sql, new MoodMapper(), objects);
	}

}
