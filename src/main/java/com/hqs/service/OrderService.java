package com.hqs.service;

import com.hqs.domain.PageBean;
import com.hqs.domain.Route;

import java.util.List;
import java.util.Map;

/**
 * @author: hqs
 * @date: 21:22 2021/5/27
 */
public interface OrderService {
    //保存或更新游客信息
    String saveOrUpdate(String id, String name, String sex, String phoneNum, String credentialsType, String credentialsNum, String travellerType);

    //保存订单
    int saveOrder(String peopleContent, String orderDesc, String payType, String productId, int uid, String travellerId);

    PageBean<Route> pageQuery(int uid, int currentPage, int pageSize);

    Map<String,Object> findStatus(String rid, int uid);

    void refund(int orderNum,String orderDesc);
}
