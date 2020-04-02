package day15.service.impl;

import day15.dao.MoodDao;
import day15.entity.Mood;
import day15.entity.User;
import day15.service.MoodService;
import day15.transaction.Transaction;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MoodServiceImpl implements MoodService {
	//使用log4j的日志记录信息（需要有一个配置文件，确定日志记录的等级，debug，info，warn，error）
	private static Logger logger = Logger.getLogger(MoodServiceImpl.class);
	private MoodDao moodDao = null;
	private Transaction transaction = null;
	
	public int insert(Mood mood) {
		//开始事务
		transaction.begin();
		logger.debug("在MoodServiceImpl类中，调用insert方法");
		int count=0;
		try {
			count = moodDao.insert(mood);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在MoodServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public int update(Mood mood) {
		// TODO Auto-generated method stub
		logger.debug("在MoodServiceImpl类中，调用update方法");
		int count=0;
		transaction.begin();
		try {
			count = moodDao.update(mood);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在MoodServiceImpl类中，update方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public int delete(Mood mood) {
		logger.debug("在MoodServiceImpl类中，调用delete方法");
		transaction.begin();
		int count=0;
		try {
			count = moodDao.delete(mood);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在MoodServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}



	public Mood getMoodById(int id) {
		logger.debug("在MoodServiceImpl类中，调用getMoodById方法");
		return moodDao.getMoodById(id);
	}

	public List<Mood> getAllMoods(User user) {
		// TODO Auto-generated method stub
		logger.debug("在MoodServiceImpl类中，调用getAllMoods方法");
		return moodDao.getAllMoods(user);
	}

	public void setMoodDao(MoodDao moodDao) {
		// TODO Auto-generated method stub
		logger.debug("在MoodServiceImpl类中，注入dao成功，调用了setDao方法");
		this.moodDao=moodDao;

	}

	public void setTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		logger.debug("在MoodServiceImpl类中，注入Transaction成功，调用了setTransaction方法");
		this.transaction=transaction;
	}

	@Override
	public List<Mood> getMoodsPerPage(int currentPage, int size,String login) throws SQLException {
		return moodDao.getMoodsPerPage(currentPage,size,login);
	}

	/* * Mood from,--提问者
	 * Mood to,--应答者
	 * int score--该题目积分

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
	}*/



}
