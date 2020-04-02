package day20.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private int userId;//用户编号
	private String userLogin;//登录名
	private String userPwd;//密码
//	private String userDate;//创建时间
	private Date userDate;//创建时间
	private int userScore;//用户积分
	private List<Mood> moodList=new ArrayList<Mood>();
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userLogin=" + userLogin + ", userPwd=" + userPwd + ", userDate=" + userDate
				+ ", userScore=" + userScore + "]";
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
//	public String getUserDate() {
//		return userDate;
//	}
//	public void setUserDate(String userDate) {
//		this.userDate = userDate;
//	}
	
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	

}
