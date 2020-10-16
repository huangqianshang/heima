package com.hqs.dao;

import java.util.List;

import com.hqs.domain.RouteImg;

public interface RouteImgDao {
	/**
	 * 查找对应的图片集合
	 * @param rid
	 * @return
	 */
	List<RouteImg> findImg(int rid);

}
