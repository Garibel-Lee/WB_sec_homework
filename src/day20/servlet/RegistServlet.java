package day20.servlet;

import day20.entity.User;
import day20.service.UserService;
import day20.util.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// post获得数据前修改字符集
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf8");
		// 通过名称获得提交的数据
		String name = request.getParameter("userName");
		String pwd = request.getParameter("userPwd");
		//获得一组爱好
		String[] hobbys = request.getParameterValues("hobbys");
		String hobby="";
		for (int i = 0; i < hobbys.length; i++) {
			hobby+=hobbys[i]+",";
		}
	
		User user = new User();
		UserService service = (UserService) BeanFactory.getObject("userService");// 获得了业务逻辑的功能代码
		user.setUserLogin(name);
		user.setUserPwd(pwd);
		int count = service.insert(user);
		// 请求转发
		if (count == 1) { // 成功
			//直接跳转，用户是否注册成功，没有明确的说明
			request.getRequestDispatcher("success.jsp").forward(request, response);
		} else { // 失败
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
