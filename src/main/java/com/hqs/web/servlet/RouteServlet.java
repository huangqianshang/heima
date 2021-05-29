package com.hqs.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqs.domain.*;
import com.hqs.service.FavoriteService;
import com.hqs.service.RouteService;
import com.hqs.serviceImp.FavoriteServiceImp;
import com.hqs.serviceImp.RouteServiceImp;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private RouteService service = new RouteServiceImp();
	private FavoriteService favoriteService = new FavoriteServiceImp();
	/**
	 * 查找每页的数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scid = request.getParameter("cid");
		String scurrentPage = request.getParameter("currentPage");
		String spageSize = request.getParameter("pageSize");
		String srname = request.getParameter("rname");
		String rname = URLDecoder.decode(srname,"utf-8");
		int cid = 0;
		int currentPage = 0;//当前页数
		int pageSize = 0;

		if(scid != null && scid.length() > 0 && !"null".equals(scid)) {
			cid = Integer.parseInt(scid);
		}
		if(scurrentPage != null && scurrentPage.length() > 0) {
			currentPage = Integer.parseInt(scurrentPage);
		}else {
			currentPage = 1;
		}
		if(spageSize != null && spageSize.length() > 0) {
			pageSize = Integer.parseInt(spageSize);
		}else {
			pageSize = 8;
		}
		if("undefined".equals(rname)||"null".equals(rname)) {
			rname = null;
		}

		PageBean<Route> page = service.findPageQuery(cid,currentPage, pageSize, rname);
//		PageBean<Product> page = service.findAllProduct(cid,currentPage, pageSize, rname);

		writeValue(page, response);
	}
	
	public void findOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		Route route = service.findOne(rid);
	
		writeValue(route, response);
	}
	
	public void haveFavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		int uid = 0;
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			uid = user.getUid();
		}
	
		boolean flag = favoriteService.isFavorite(uid, rid);
		
		writeValue(flag, response);
	}
	
	public void addFavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		boolean flag = Boolean.valueOf(request.getParameter("flag"));
		int uid = 0;
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null) {
			uid = user.getUid();
		}else {
			return;
		}
		
		favoriteService.addFavorite(rid,uid,flag);
	}
	
	public void findCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		
		int collect = favoriteService.findCollect(rid);
		
		writeValue(collect, response);
	}
	
	public void findFavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scurrentPage = request.getParameter("currentPage");
		String spageSize = request.getParameter("pageSize");
		int currentPage = 0;//当前页数
		int pageSize = 0;
		
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			return;
		}
		
		if(scurrentPage != null && scurrentPage.length() > 0) {
			currentPage = Integer.parseInt(scurrentPage);
		}else {
			currentPage = 1;
		}
		if(spageSize != null && spageSize.length() > 0) {
			pageSize = Integer.parseInt(spageSize);
		}else {
			pageSize = 1;
		}
		
		PageBean<Favorite> page = favoriteService.findFavorite(user.getUid(),currentPage,pageSize);
		writeValue(page, response);
	}
	
	public void listFavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scurrentPage = request.getParameter("currentPage");
		String spageSize = request.getParameter("pageSize");
		int currentPage = 0;//当前页数
		int pageSize = 0;
		
		if(scurrentPage != null && scurrentPage.length() > 0) {
			currentPage = Integer.parseInt(scurrentPage);
		}else {
			currentPage = 1;
		}
		if(spageSize != null && spageSize.length() > 0) {
			pageSize = Integer.parseInt(spageSize);
		}else {
			pageSize = 1;
		}
		
		PageBean<Favorite> page = favoriteService.listFavorite(currentPage,pageSize);
		writeValue(page, response);
	}
	
	public void QueryByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scurrentPage = request.getParameter("currentPage");
		String spageSize = request.getParameter("pageSize");
		String price1 = request.getParameter("price1");
		String price2 = request.getParameter("price2");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name,"utf-8");
		int currentPage = 0;//当前页数
		int pageSize = 0;
		
		if(scurrentPage != null && scurrentPage.length() > 0) {
			currentPage = Integer.parseInt(scurrentPage);
		}else {
			currentPage = 1;
		}
		if(spageSize != null && spageSize.length() > 0) {
			pageSize = Integer.parseInt(spageSize);
		}else {
			pageSize = 1;
		}
		if("undefined".equals(name)||"null".equals(name)) {
			name = null;
		}
		
		PageBean<Favorite> page = favoriteService.queryByPrice(name,price1,price2,currentPage,pageSize);
		writeValue(page, response);
		
	}
}