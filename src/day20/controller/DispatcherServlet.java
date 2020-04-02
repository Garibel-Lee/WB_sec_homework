package day20.controller;

import day20.mvc.Action;
import day20.mvc.MvcConfig;
import day20.mvc.MyPackage;
import day20.mvc.Result;
import day20.util.BeanFactory;
import day20.util.MvcConfigParse;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DispatcherServlet.class);
    private MvcConfig mvcConfig = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得请求的url  协议://主机:端口/应用名path路径/文件夹名（null）/文件
		String uriPath = request.getRequestURI();   //应用名/文件夹名/文件
		// /web001/login.do /-(网站名)    /-命名空间（相对路径）      login.do--请求的处理
		// /web001/nuser/login.do
		String contentString =request.getContextPath();//返回工程名部分，如果工程映射为/，此处返回则为空
		//两个字符串一减，就可获得命名空间,判断是否使用默认命名空间
		String namespace="/";
		uriPath = uriPath.replace(contentString, "");//删掉前部的网站字符串

		if(uriPath.indexOf("/") != uriPath.lastIndexOf("/")){
			//说明两个斜杠中间有内容
			namespace = uriPath.substring(uriPath.indexOf("/")+1,uriPath.lastIndexOf("/"));
	}
		logger.debug("namespace"+namespace);
		MyPackage myPackage = mvcConfig.getPackage("/");
		if(null==myPackage){
			logger.warn("没有找到对应的myPackage");
			throw new RuntimeException("没有找到对应的myPackage");
		}
		String actionName=uriPath.substring(uriPath.lastIndexOf("/")+1,uriPath.lastIndexOf("."));//最后一个斜杠后面的内容，点之前的内容
		Action action = myPackage.getAction(actionName);
		System.out.println("actionName:"+actionName);
		if(null==action){
			logger.warn("没有找到对应的action");
			throw new RuntimeException("没有找到对应的action");
		}
		//通过beanFatory获得对应处理对象
		Object obj =BeanFactory.getObject(action.getClassName());
		//反射获得对应的方法
		Class clazz = obj.getClass();
		String resultString="";
		try {
			//获得方法
			Method method = clazz.getDeclaredMethod(action.getMethod(), HttpServletRequest.class,HttpServletResponse.class);
			//调用方法
			resultString= method. invoke(obj, request,response).toString();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//获得action的result
		Result result = action.getResult(resultString);
		
		if(null!=result){
			//跳转
			String type = result.getType();
			if("dispatcher".equalsIgnoreCase(type)){
//				请求转发
				request.getRequestDispatcher(result.getContent()).forward(request, response);			
			}else if("redirect".equalsIgnoreCase(type)){
//				重定向
				response.sendRedirect(result.getContent());
			}else  if("stream".equalsIgnoreCase(type)){
//				输出流
				PrintWriter out = response.getWriter();
				out.write(result.getContent());
				out.flush();
				out.close();
			}
		}
		//System.out.println(contentString);	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}



	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//在初始化方法中，解析xml，获得mvc的配置
		mvcConfig = MvcConfigParse.getMvcConfig();
		logger.debug("在初始化方法中，解析xml，获得mvc的配置");
	}

}
