package day20.dao.mapper;

import day20.entity.User;
import day20.util.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UserMapper implements RowMapper<User> {
	private static SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger logger = Logger.getLogger(UserMapper.class);
	public List<User> mapperList(ResultSet rs) throws SQLException {
		logger.debug("获得t_user表中数据，这是t_user表与User类的映射");
		List<User> list = new ArrayList<User>();
		//数据封装 rs是一个只读只进，只读--不能用于修改，只进--不能回头
		while(rs.next()){
			System.out.println("rs不为空");
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUserLogin(rs.getString("user_login"));
			user.setUserPwd(rs.getString("user_pwd"));

			try {
				user.setUserDate(format.parse(rs.getString("user_date")));
			}catch (ParseException e1){
				logger.debug("转化失败");
				e1.printStackTrace();
			}catch (NullPointerException e2){
				logger.debug("空指针失败");
				e2.printStackTrace();
			}
			System.out.println(rs.getString("user_date"));
			user.setUserScore(rs.getInt("user_score"));
			list.add(user);
		}
		logger.debug("UserMapper数据分装成功");
		return list;
	}

}
