package com.hqs.dao;

import java.util.List;

import com.hqs.domain.Favorite_three;
import com.hqs.domain.Route;

public interface FavoriteDao {
	/**
	 * 通过uid,rid查询个数
	 * @param uid
	 * @param rid TODO
	 * @return
	 */
	int findFavoriteByUidAndRid(int uid, int rid);

	/**
	 * 添加进数据库
	 * @param uid
	 * @param rid
	 */
	int addFavoriteByUidAndRid(int uid, int rid);

    /**
     * 取消收藏
     * 从数据库删除
     * @param uid
     * @param rid
     */
    int deleteFavoriteByUidAndRid(int uid, int rid);

	/**
	 * 查找rid相同的所有个数
	 * @param rid
	 * @return
	 */
	int findAllByRid(int rid);

	/**查找uid相同的部分Favorite_three对象
	 * 查找
	 * @param uid
	 * @param start TODO
	 * @param pageSize TODO
	 * @return
	 */
	List<Favorite_three> findNeedByUid(int uid, int start, int pageSize);

	/**
	 * 查找uid相同的所有数量
	 * @param uid
	 * @return
	 */
	int findAllByUid(int uid);

	/**
	 * 查询排好序的route对象
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<Route> findOrderedFavorite(int start, int pageSize);

	/**
	 * 查询价格区间的route对象
	 * @param name
	 * @param lowPrice
	 * @param heighPrice
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<Route> findQueryPrice(String name, int lowPrice, int heighPrice, int start, int pageSize);

	
}
