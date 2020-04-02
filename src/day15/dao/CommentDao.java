package day15.dao;

import day15.entity.Comment;
import day15.entity.Mood;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
	//简单的五个方法，对t_Comment表的增删改查（查询一个，查询全部)，查询部分--根据条件）
	public int insert(Comment comment) throws SQLException;
	public int update(Comment comment) throws SQLException;
	public int delete(Comment comment) throws SQLException;
	public Comment getCommentById(int id);
	public List<Comment> getAllComments(Mood mood );
	public List<Comment> getCommentsPerPage(int currentPage, int size,int moodid) throws SQLException ;
	//用户心情记录功能一个用户有多个心情记录可以进行修改
	/*

	*/

	
}
