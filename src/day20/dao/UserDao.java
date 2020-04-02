package day20.dao;

import day20.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
	//简单的五个方法，对t_user表的增删改查（查询一个，查询全部)，查询部分--根据条件） 
	public int insert(User user) throws SQLException;
	public int update(User user) throws SQLException;
	public int delete(User user) throws SQLException;
	public User getUserById(int id);
	public User getUserByName(String name);
	public List<User> getAllUsers();
	public int addScore(String userLogin, int score) throws SQLException;
	//登录功能
	/*
	 * (1)传入用户名，获得密码，在程序比较密码是否正确，这个好一点防止sql脚本注入（一把出现在使用String去拼接sql语句），
	 * 因为我们已经是preparedStatement也就不存在注入的问题，
	 * (2)传入用户名与密码，通过数据库查找符合用户名和密码的行，返回查询结果
	 * */
	public User login(User user);
	
}
