package day20.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import day20.entity.User;
import day20.service.UserService;
import day20.util.BeanFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("=================这是私有的变量==================");
		System.out.println(config.getInitParameter("myParam"));
		System.out.println("=================这是共有的变量==================");
		System.out.println(config.getServletContext().getInitParameter("contextInit"));
		super.init(config);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理get请求
		logger.debug("调用LoginServlet类的doGet方法");
		//设置字符集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf8");
		//通过名称获得提交的数据
		String name=request.getParameter("userName");
		String pwd = request.getParameter("userPwd");
		User user = new User();
		UserService service = (UserService)BeanFactory.getObject("userService");//获得了业务逻辑的功能代码
		user.setUserLogin(name);
		user.setUserPwd(pwd);
		User result = service.login(user);
	
		// 请求转发
		if(null!=result){ //成功
			//保存用户的登录信息
			//在servlet不能直接获得Session
			//session.setAttribute("user", result);
			request.getSession().setAttribute("user", result);
			ServletContext application = request.getSession().getServletContext();
			Object obj = application.getAttribute("count");
			if(null == obj){
				//先经过监听器，才过Servlet
				application.setAttribute("count", 1);
				System.out.println("count:"+1);
			}else{
				int count = (Integer)obj;
				count++;
				application.setAttribute("count", count);
				System.out.println("count:"+count);
			}
			System.out.println("session添加成功");
			//request.getRequestDispatcher("success.jsp").forward(request, response);
			//response.sendRedirect("success.jsp");
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}else{ //失败
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
