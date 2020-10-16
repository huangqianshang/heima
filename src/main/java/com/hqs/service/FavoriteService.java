package com.hqs.service;

import com.hqs.domain.Favorite;
import com.hqs.domain.PageBean;

public interface FavoriteService {
	/**
	 * 查询用户是否收藏
	 * @param uid
	 * @param rid TODO
	 * @return
	 */
	boolean isFavorite(int uid, String rid);

	/**
	 * 添加收藏
	 * @param rid
	 * @param uid
	 */
	void addFavorite(String rid, int uid);

	/**
	 * 查找收藏数
	 * @param rid
	 * @return
	 */
	int findCollect( String rid);

	/**
	 * 分页查找用户已收藏的Favorite对象
	 * @param uid
	 * @param currentPage TODO
	 * @param pageSize TODO
	 * @return
	 */
	PageBean<Favorite> findFavorite(int uid, int currentPage, int pageSize);

	/**
	 * 分页查询收藏排行榜Favorite对象
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<Favorite> listFavorite(int currentPage, int pageSize);

	/**
	 * 分页查询用户输入的路线、多少钱~多少钱
	 * @param name
	 * @param price1
	 * @param price2
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<Favorite> queryByPrice(String name, String price1, String price2, int currentPage, int pageSize);

}
