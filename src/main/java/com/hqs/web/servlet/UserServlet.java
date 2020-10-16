package com.hqs.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hqs.domain.ResultInfo;
import com.hqs.domain.User;
import com.hqs.service.UserService;
import com.hqs.serviceImp.UserServiceImp;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService service = new UserServiceImp();
	/**
	 * 检查用户注册信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void registUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//判断验证码是否正确
		String usercode = request.getParameter("check");
		HttpSession session = request.getSession();
		String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");

		if(!usercode.equalsIgnoreCase(checkCode)||checkCode == null) {//如果不一样
			ResultInfo info = new ResultInfo();
			info.setFlag(false);
			info.setErrorMsg("验证码错误");
			ObjectMapper mapper = new ObjectMapper();
			String msg = mapper.writeValueAsString(info);
			response.getWriter().write(msg);
			return;
		}
		
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//完成注册
		boolean flag = service.regist(user);
		ResultInfo info = new ResultInfo();
		//响应结果
		if(flag) {
			info.setFlag(flag);
			
		}else {
			info.setFlag(flag);
			info.setErrorMsg("注册失败");
			
		}
		
		//将info转换成json
		writeValue(info, response);
	}

	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		boolean flag = service.active(code);
		String msg = null;
		if(flag) {
			//激活成功
			msg = "激活成功,请<a href='login.html'>登录</a>";
		}else {
			//激活失败
			msg = ("激活失败,请联系管理员");
		}
		response.getWriter().write(msg);
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//根据账号密码查询详细用户
		User u = service.findUser(user);
		ResultInfo info = new ResultInfo();
		if(u == null) {
			//登录失败
			info.setFlag(false);
			info.setErrorMsg("账号或密码错误");
		}
		if(u != null && !"Y".equals(u.getStatus())){
			//账户未激活
			info.setFlag(false);
			info.setErrorMsg("账户未激活");
		}
		if(u != null && "Y".equals(u.getStatus())){
			//登录成功
			info.setFlag(true);
			request.getSession().setAttribute("user", u);
			request.getRequestDispatcher(request.getContextPath()+"/index.html");
		}
		
		writeValue(info, response);
		
	}
	
	/**
	 * 查找已登录的用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Object user = request.getSession().getAttribute("user");
		writeValue(user, response);
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/login.html");
	}
//	public void run(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//	}

}