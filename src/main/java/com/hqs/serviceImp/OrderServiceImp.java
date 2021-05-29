package com.hqs.serviceImp;

import com.hqs.dao.OrderDao;
import com.hqs.daoImp.OrderDaoImp;
import com.hqs.domain.PageBean;
import com.hqs.domain.Route;
import com.hqs.service.OrderService;
import com.hqs.util.UuidUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hqs
 * @date: 21:22 2021/5/27
 */
public class OrderServiceImp implements OrderService {
    private OrderDao dao = new OrderDaoImp();

    @Override
    public String saveOrUpdate(String id, String name, String sex, String phoneNum, String credentialsType, String credentialsNum, String travellerType){
        String travellerId;
        if( null == id ){
            travellerId = UuidUtil.getUuid();
            dao.saveTraveller(travellerId,name,sex,phoneNum,Integer.parseInt(credentialsType),credentialsNum,Integer.parseInt(travellerType));
            return travellerId;
        }else{
            travellerId = id;
            dao.updateTraveller(id,name,sex,phoneNum,Integer.parseInt(credentialsType),credentialsNum,Integer.parseInt(travellerType));
            return travellerId;
        }
    }

    @Override
    public int saveOrder(String peopleContent, String orderDesc, String payType, String productId, int uid, String travellerId){
        String orderId = UuidUtil.getUuid();
        dao.saveOrder(orderId,new Date(),peopleContent,orderDesc,payType,1,productId,String.valueOf(uid));
        return dao.collectOrderAndTraveller(orderId,travellerId);
    }

    @Override
    public PageBean<Route> pageQuery(int uid, int nowPage, int numOfPage){
        PageBean<Route> page = new PageBean<Route>();

        //设置数据总数
        int total = dao.findAllOrder(uid);
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
        page.setList(dao.pageQuery(uid,start,numOfPage));

        return page;
    }

    @Override
    public Map<String,Object> findStatus(String rid, int uid){
        Map<String,Object> map = new HashMap<>(5);
        int status = dao.findStatus(rid, uid);
        String orderStatus = "";
        switch( status ){
            case 0:
                orderStatus = "申请退款中";
                break;
            case 1:
                orderStatus = "已支付";
                break;
            case 2:
                orderStatus = "被退回";
                break;
            case 3:
                orderStatus = "已退款";
                break;
            default:
                orderStatus = "未知";
                break;
        }
        map.put("orderStatus",orderStatus);
        map.put("orderNum",dao.findOrderNum(rid,uid));
        return map;
    }

    @Override
    public void refund(int orderNum,String orderDesc){
        dao.refund(orderNum,orderDesc);
    }
}
