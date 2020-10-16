package com.hqs.dao;

import com.hqs.domain.Seller;

public interface SellerDao {
	/**
	 * 通过sid查找Seller对象
	 * @param sid
	 * @return
	 */
	Seller findSellerBySid(int sid);

}
