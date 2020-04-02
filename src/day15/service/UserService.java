package day15.service;

import day15.dao.UserDao;
import day15.entity.User;
import day15.transaction.Transaction;

import java.util.List;

public interface UserService {
	public int insert(User user);
	public int update(User user);
	public int delete(User user);
	//新增积分扣除（有奖提问，提问者对问题给与一定的积分，如果回答者回答的很好，提问者的积分转给回答者）
	public int getScore(User from,User to,int score);
	public User getUserById(int id);
	public User getUserByName(String name);
	public List<User> getAllUsers();
	public void setUserDao(UserDao dao);//这是重点，通过xml解析获得dao，然后注入到service中 
	public void setTransaction(Transaction transaction);//这是重点，通过xml解析获得transaction，然后注入到service中 
	public User login(User user);
	public int  addSorce(User user);
}
