package com.hqs.dao;

import com.hqs.domain.Route;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: hqs
 * @date: 21:23 2021/5/27
 */
public interface OrderDao {
    int saveTraveller(String uuid, String name, String sex, String phoneNum, int credentialsType, String credentialsNum, int travellerType);

    int updateTraveller(String id, String name, String sex, String phoneNum, int credentialsType, String credentialsNum, int travellerType);

    int saveOrder(String uuid, Date date, String peopleContent, String orderDesc, String payType, int orderStatus, String productId, String uid);

    int collectOrderAndTraveller(String orderId, String travellerId);

    List<Route> pageQuery(int uid,int start,int numOfPage);

    int findAllOrder(int uid);

    int findStatus(String rid, int uid);

    String findOrderNum(String rid, int uid);

    void refund(int orderNum, String orderDesc);
}
