package com.hqs.dao;

import java.util.List;

import com.hqs.domain.Route;

public interface RouteDao {
	int findAll(int cid, String rname);

	List<Route> findPageQuery(int cid,int start, int numOfPage, String rname);

	Route findOne(int rid);
}
