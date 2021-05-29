package com.hqs.serviceImp;

import java.util.ArrayList;
import java.util.List;

import com.hqs.dao.FavoriteDao;
import com.hqs.dao.RouteDao;
import com.hqs.daoImp.FavoriteDaoImp;
import com.hqs.daoImp.RouteDaoImp;
import com.hqs.domain.Favorite;
import com.hqs.domain.Favorite_three;
import com.hqs.domain.PageBean;
import com.hqs.domain.Route;
import com.hqs.service.FavoriteService;

public class FavoriteServiceImp implements FavoriteService {
	private FavoriteDao favoriteDao= new FavoriteDaoImp();
	private RouteDao routeDao = new RouteDaoImp();
	
	@Override
	public boolean isFavorite(int uid, String rid) {
		// TODO Auto-generated method stub
		int num = favoriteDao.findFavoriteByUidAndRid(uid, Integer.parseInt(rid));
		
		if(num == 0) {
			//用户未收藏
			return false;
		}else {
			//用户已收藏
			return true;
		}
	}

	@Override
	public void addFavorite(String rid, int uid, boolean flag) {
		// TODO Auto-generated method stub
        if(flag){
            favoriteDao.addFavoriteByUidAndRid(uid, Integer.parseInt(rid));
        }else{
            favoriteDao.deleteFavoriteByUidAndRid(uid, Integer.parseInt(rid));
        }
	}

	@Override
	public int findCollect(String rid) {
		// TODO Auto-generated method stub
		return favoriteDao.findAllByRid(Integer.parseInt(rid));
	}

	@Override
	public PageBean<Favorite> findFavorite(int uid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		int start = (currentPage-1) * pageSize;
		List<Favorite_three> list_favorite_three = favoriteDao.findNeedByUid(uid, start, pageSize);
		int total = favoriteDao.findAllByUid(uid);
		
		PageBean<Favorite> page = new PageBean<Favorite>();
		
		List<Favorite> list_favorite = new ArrayList<Favorite>();
		for (Favorite_three favorite_three : list_favorite_three) {
			Favorite favorite = new Favorite();
			favorite.setRoute(routeDao.findOne(favorite_three.getRid()));
			favorite.setDate(favorite_three.getDate().toString());
			list_favorite.add(favorite);
		}
		
		page.setList(list_favorite);
		page.setNowPageNum(currentPage);
		page.setnPage(pageSize);
		page.setTotal(total);
		int totalPage = total%pageSize == 0? total/pageSize : (total/pageSize)+1;
		page.setTotalPage(totalPage);
		return page;
	}

	@Override
	public PageBean<Favorite> listFavorite(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		int start = (currentPage-1) * pageSize;
		List<Route> list_route = favoriteDao.findOrderedFavorite( start, pageSize);
		int total = favoriteDao.findOrderedFavorite(0,-1).size();
		
		PageBean<Favorite> page = new PageBean<Favorite>();
		
		List<Favorite> list_favorite = new ArrayList<Favorite>();
		for (Route route : list_route) {
			Favorite favorite = new Favorite();
			favorite.setRoute(routeDao.findOne(route.getRid()));
			favorite.setCount(favoriteDao.findAllByRid(route.getRid()));
			list_favorite.add(favorite);
		}
		
		page.setList(list_favorite);
		page.setNowPageNum(currentPage);
		page.setnPage(pageSize);
		page.setTotal(total);
		int totalPage = total%pageSize == 0? total/pageSize : (total/pageSize)+1;
		page.setTotalPage(totalPage);
		return page;
	}

	@Override
	public PageBean<Favorite> queryByPrice(String name, String price1, String price2, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		int start = (currentPage-1) * pageSize;
		int lowPrice =Integer.parseInt(price1);
		int heighPrice = Integer.parseInt(price2);
		if(lowPrice > heighPrice) {
			int a = lowPrice;
			lowPrice = heighPrice;
			heighPrice = a;
			
		}
		
		List<Route> list_route = favoriteDao.findQueryPrice(name,lowPrice,heighPrice,start, pageSize);
		int total = favoriteDao.findQueryPrice(name,lowPrice,heighPrice,0,-1).size();

		PageBean<Favorite> page = new PageBean<Favorite>();
		
		List<Favorite> list_favorite = new ArrayList<Favorite>();
		for (Route route : list_route) {
			Favorite favorite = new Favorite();
			favorite.setRoute(route);
			favorite.setCount(favoriteDao.findAllByRid(route.getRid()));
			list_favorite.add(favorite);
		}
		
		page.setList(list_favorite);
		page.setNowPageNum(currentPage);
		page.setnPage(pageSize);
		page.setTotal(total);
		int totalPage = total%pageSize == 0? total/pageSize : (total/pageSize)+1;
		page.setTotalPage(totalPage);
		return page;
	}

}
