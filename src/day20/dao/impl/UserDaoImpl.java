package day20.dao.impl;

import day20.dao.UserDao;
import day20.dao.mapper.UserMapper;
import day20.entity.User;
import day20.util.JdbcTemplate;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	//格式化日期
	private static SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
	public int insert(User user)  throws SQLException {
		logger.debug("在UserDaoImpl类中，调用insert方法");
		String sql="insert into t_user(user_id,user_login,user_pwd,user_date,user_score)values(null,?,?,now(),10)";
		String[] objects = {user.getUserLogin(),user.getUserPwd()};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行insert方法的返回结果是："+count);
		return count;
	}

	public int update(User user)  throws SQLException{
		logger.debug("在UserDaoImpl类中，调用UPDATE方法");
		String sql="UPDATE t_user set user_login=?,user_pwd=?,user_date=?,user_score=? where user_id=?";
		String[] objects = {user.getUserLogin(),user.getUserPwd(),format.format(user.getUserDate()),user.getUserScore()+"",user.getUserId()+""};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行update返回结果是："+count);
		return count;
	}

	public int delete(User user)  throws SQLException {
		logger.debug("在UserDaoImpl类中，调用insert方法");
		String sql="delete from t_user where user_id=?";//所有的占位符必须是英文的问号
		String[] objects = {user.getUserId()+""};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行delete返回结果是："+count);
		return count;
	}

	public User getUserById(int id) {
		logger.debug("在UserDaoImpl类中，调用getUserById方法");
		String sql = "select user_id,user_login,user_pwd,user_date,user_score from t_user where user_id=?";
		String[] objects={id+""};
		List<User> list =JdbcTemplate.executeQuery(sql, new UserMapper(), objects);
		if(list.size()>0){
			return list.get(0); //查到了
		}
		return null;//没有查到
	}

	@Override
	public User getUserByName(String name) {
		logger.debug("在UserDaoImpl类中，调用getUserById方法");
		String sql = "select user_id,user_login,user_pwd,user_date,user_score from t_user where user_login=?";
		String[] objects={name};
		List<User> list =JdbcTemplate.executeQuery(sql, new UserMapper(), objects);
		if(list.size()>0){
			return list.get(0); //查到了
		}
		return null;//没有查到
	}

	public List<User> getAllUsers() {
		logger.debug("在UserDaoImpl类中，调用getAllUsers方法");
		String sql = "select * from t_user";
		return JdbcTemplate.executeQuery(sql, new UserMapper());
	}

	//登录功能
	public User login(User user) {
		User userResult = null;
		logger.debug("在UserDaoImpl类中，调用login方法");
		String sql = "select user_id,user_login,user_pwd,user_date,user_score from t_user where user_login=? and user_pwd=?";
		Object[] objects = {user.getUserLogin(),user.getUserPwd()};
		List<User> users= JdbcTemplate.executeQuery(sql, new UserMapper(), objects);
		if(null!=users && users.size()==1){
			userResult = users.get(0);
		}
		return userResult;
	}

	public int addScore(String userLogin, int score) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("在UserDaoImpl类中，调用addScore方法");
		String sql="UPDATE t_user set user_score=user_score+? where user_login=?";
		String[] objects = {score+"",userLogin};
		int count = JdbcTemplate.executeUpdate(sql, objects);
		logger.info("执行addScore返回结果是："+count);
		return count;
	}
}
