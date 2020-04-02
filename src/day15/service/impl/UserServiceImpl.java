package day15.service.impl;

import day15.dao.UserDao;
import day15.entity.User;
import day15.service.UserService;
import day15.transaction.Transaction;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
	//使用log4j的日志记录信息（需要有一个配置文件，确定日志记录的等级，debug，info，warn，error）
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private UserDao userDao = null; 
	private Transaction transaction = null;
	
	public int insert(User user) {
		//开始事务
		transaction.begin();
		logger.debug("在UserServiceImpl类中，调用insert方法");
		int count=0;
		try {
			count = userDao.insert(user);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在UserServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public int update(User user) {
		// TODO Auto-generated method stub
		logger.debug("在UserServiceImpl类中，调用update方法");
		int count=0;
		transaction.begin();
		try {
			count = userDao.update(user);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在UserServiceImpl类中，update方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public int delete(User user) {
		logger.debug("在UserServiceImpl类中，调用delete方法");
		transaction.begin();
		int count=0;
		try {
			count = userDao.delete(user);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在UserServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public User getUserById(int id) {
		logger.debug("在UserServiceImpl类中，调用getUserById方法");
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByName(String name) {
		logger.debug("在UserServiceImpl类中，调用getUserByName方法");
		return userDao.getUserByName(name);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		logger.debug("在UserServiceImpl类中，调用getAllUsers方法");
		return userDao.getAllUsers();
	}

	public void setUserDao(UserDao userDao) {
		// TODO Auto-generated method stub
		logger.debug("在UserServiceImpl类中，注入dao成功，调用了setDao方法");
		this.userDao=userDao;

	}

	public void setTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		logger.debug("在UserServiceImpl类中，注入Transaction成功，调用了setTransaction方法");
		this.transaction=transaction;
	}

	/**
	 * User from,--提问者
	 * User to,--应答者
	 * int score--该题目积分
	 */
	public int getScore(User from, User to, int score) {
		// 实际上执行两次更新
		String str=null;
		from.setUserScore(from.getUserScore()-score);
		to.setUserScore(to.getUserScore()+score);
		//开始事务
		transaction.begin();
		logger.debug("在UserServiceImpl类中，调用insert方法");
		int count=0;
		try {
			count += userDao.update(to);
			str.length();//空指针
			count += userDao.update(from);
			
			//一旦执行成功就提交
			if(count==2){
				transaction.commit();
			}else{
				//至少有一条sql语句执行失败
				transaction.rollback();
			}
		} catch (SQLException e) {
			logger.warn("在UserServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public User login(User user) {
		logger.debug("在UserServiceImpl类中，调用login方法");
		return userDao.login(user);
	}

	@Override
	public int addSorce(User user)  {
		try {
			return userDao.addScore(user.getUserLogin(), 3);
		}catch (Exception e){
			return 0;
		}
	}

}
