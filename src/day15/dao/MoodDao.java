package day15.dao;

import day15.entity.Mood;
import day15.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface MoodDao {
	//简单的五个方法，对t_Mood表的增删改查（查询一个，查询全部)，查询部分--根据条件）
	public int insert(Mood mood) throws SQLException;
	public int update(Mood mood) throws SQLException;
	public int delete(Mood mood) throws SQLException;
	public Mood getMoodById(int id);
	public List<Mood> getAllMoods(User user);
	public List<Mood> getMoodsPerPage(int currentPage, int size,String moodlogin) throws SQLException;
	//用户心情记录功能一个用户有多个心情记录可以进行修改
	/*

	*/

	
}
