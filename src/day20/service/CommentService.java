package day20.service;

import day20.dao.CommentDao;
import day20.entity.Comment;
import day20.entity.Mood;
import day20.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
	public int insert(Comment comment);
	public int update(Comment comment);
	public int delete(Comment comment);
	/*对某一条记录进行修改*/
	public Comment getCommentById(int id);
	/*对所有该用户的心情做调整*/
	public List<Comment> getAllComments(Mood mood);
	public void setCommentDao(CommentDao dao);//这是重点，通过xml解析获得dao，然后注入到service中
	public void setTransaction(Transaction transaction);//这是重点，通过xml解析获得transaction，然后注入到service中
	List<Comment> getCommentsPerPage(int currentPage, int size, int  moodid) throws SQLException;

}
