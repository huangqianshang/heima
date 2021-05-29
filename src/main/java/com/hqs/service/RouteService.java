package com.hqs.service;

import com.hqs.domain.PageBean;
import com.hqs.domain.Product;
import com.hqs.domain.Route;

public interface RouteService {

	PageBean<Route> findPageQuery(int cid, int start, int numOfPage, String rname);

	Route findOne(String rid);

    PageBean<Product> findAllProduct(int cid, int currentPage, int pageSize, String rname);
}
