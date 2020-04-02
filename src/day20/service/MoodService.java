package day20.service;

import day20.dao.MoodDao;
import day20.entity.Mood;
import day20.entity.User;
import day20.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface MoodService {
	public int insert(Mood mood);
	public int update(Mood mood);
	public int delete(Mood mood);
	/*对某一条记录进行修改*/
	public Mood getMoodById(int id);
	/*对所有该用户的心情做调整*/
	public List<Mood> getAllMoods(User user);
	public void setMoodDao(MoodDao dao);//这是重点，通过xml解析获得dao，然后注入到service中
	public void setTransaction(Transaction transaction);//这是重点，通过xml解析获得transaction，然后注入到service中 
	List<Mood> getMoodsPerPage(int currentPage, int size,String login) throws SQLException;
}
