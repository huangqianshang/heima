package com.hqs.serviceImp;

import java.util.List;

import com.hqs.dao.RouteDao;
import com.hqs.dao.RouteImgDao;
import com.hqs.dao.SellerDao;
import com.hqs.daoImp.RouteDaoImp;
import com.hqs.daoImp.RouteImgDaoImp;
import com.hqs.daoImp.SellerDaoImp;
import com.hqs.domain.PageBean;
import com.hqs.domain.Route;
import com.hqs.domain.RouteImg;
import com.hqs.domain.Seller;
import com.hqs.service.RouteService;

public class RouteServiceImp implements RouteService {
	private RouteDao dao = new RouteDaoImp();
	private SellerDao sellerDao = new SellerDaoImp();
	private RouteImgDao routeImgDao = new RouteImgDaoImp();
	
	@Override
	public PageBean<Route> findPageQuery(int cid, int nowPage, int numOfPage, String rname) {
		// TODO Auto-generated method stub
		PageBean<Route> page = new PageBean<Route>();
		
		//设置数据总数
		int total = dao.findAll(cid, rname);
		page.setTotal(total);
		//设置每页个数
		page.setnPage(numOfPage);
		//设置当前页码
		page.setNowPageNum(nowPage);
		
		//查询数据的位置
		int start = (nowPage-1)*numOfPage;
		
		//设置总页数
		int totalPage = total%numOfPage == 0? total/numOfPage: (total/numOfPage)+1;
		page.setTotalPage(totalPage);
		//设置显示数据集合
		page.setList(dao.findPageQuery(cid, start, numOfPage, rname));
		
		return page;
	}

	@Override
	public Route findOne(String rid) {
		// TODO Auto-generated method stub
		//通过rid查询准确的route对象
		Route route = dao.findOne(Integer.parseInt(rid));
		//查找对应的商家对象
		Seller seller = sellerDao.findSellerBySid(route.getSid());
		route.setSeller(seller);
		//查找对应的图片
		List<RouteImg> routeImgList = routeImgDao.findImg(route.getRid());
		route.setRouteImgList(routeImgList);
		
		return route;
	}

}
