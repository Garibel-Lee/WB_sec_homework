package day15.service.impl;

import day15.dao.CommentDao;
import day15.dao.MoodDao;
import day15.entity.Comment;
import day15.entity.Mood;
import day15.service.CommentService;
import day15.transaction.Transaction;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
	//使用log4j的日志记录信息（需要有一个配置文件，确定日志记录的等级，debug，info，warn，error）
	private static Logger logger = Logger.getLogger(CommentServiceImpl.class);
	private CommentDao commentDao;
	private Transaction transaction;



	private MoodDao moodDao;
	
	public int insert(Comment comment) {
		//开始事务
		transaction.begin();
		logger.debug("在CommentServiceImpl类中，调用insert方法");
		int count=0;
		try {
			count = commentDao.insert(comment);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在CommentServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public int update(Comment comment) {
		// TODO Auto-generated method stub
		logger.debug("在CommentServiceImpl类中，调用update方法");
		int count=0;
		transaction.begin();
		try {
			count = commentDao.update(comment);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在CommentServiceImpl类中，update方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}

	public int delete(Comment comment) {
		logger.debug("在CommentServiceImpl类中，调用delete方法");
		transaction.begin();
		int count=0;
		try {
			count = commentDao.delete(comment);
			//一旦执行成功就提交
			transaction.commit();
		} catch (SQLException e) {
			logger.warn("在CommentServiceImpl类中，insert方法出现异常");
			//一旦异常就回滚
			transaction.rollback();
			e.printStackTrace();
		}
		return count ;
	}



	public Comment getCommentById(int id) {
		logger.debug("在CommentServiceImpl类中，调用getCommentById方法");
		return commentDao.getCommentById(id);
	}

	public List<Comment> getAllComments(Mood mood) {
		// TODO Auto-generated method stub
		logger.debug("在CommentServiceImpl类中，调用getAllComments方法");
		return commentDao.getAllComments(mood);
	}

	public void setCommentDao(CommentDao commentDao) {
		// TODO Auto-generated method stub
		logger.debug("在CommentServiceImpl类中，注入dao成功，调用了setDao方法");
		this.commentDao=commentDao;

	}

	public void setTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		logger.debug("在CommentServiceImpl类中，注入Transaction成功，调用了setTransaction方法");
		this.transaction=transaction;
	}

	public void setMoodDao(MoodDao moodDao) {
		this.moodDao = moodDao;
	}

	@Override
	public List<Comment> getCommentsPerPage(int currentPage, int size,int moodid) throws SQLException {
		return commentDao.getCommentsPerPage(currentPage,size,moodid);
	}

	/* * Comment from,--提问者
	 * Comment to,--应答者
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
