package day20.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOut
 */
public class LoginOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);//销毁session中的变量
		session.invalidate();//销毁对应的session
		System.out.println("已经安全退出");//用户看不到控制台，如何让用户感知到自己已经退出了？？
		//response.sendRedirect("login.jsp");
		//可以打出js代码,首先设置响应的字符集，否则容易出现中文乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();//获得输出对象
		out.write("<script type=\"text/javascript\">");
		out.write("alert(\"已经成功退出账户\");");
		out.write("location.href=\"login.jsp\";");
		out.write("</script>");
		//清空缓冲区，一旦关闭了输出，系统会自动清空缓冲区，手动清空缓存，只是一个良好的编码习惯
		out.flush();
		out.close();//关闭输出
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
