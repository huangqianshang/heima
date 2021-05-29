package com.hqs.daoImp;

import com.hqs.dao.OrderDao;
import com.hqs.domain.Route;
import com.hqs.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hqs
 * @date: 21:23 2021/5/27
 */
public class OrderDaoImp implements OrderDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
    String sql;

    @Override
    public int saveTraveller(String uuid, String name, String sex, String phoneNum, int credentialsType, String credentialsNum, int travellerType){
        sql = "insert into ssm.traveller values (?,?,?,?,?,?,?)";
        return template.update(sql,uuid,name,sex,phoneNum,credentialsType,credentialsNum,travellerType);
    }

    @Override
    public int updateTraveller(String id, String name, String sex, String phoneNum, int credentialsType, String credentialsNum, int travellerType){
        sql = " update ssm.traveller set NAME = ?,sex = ?, " +
                " phoneNum=?,credentialsType=?,credentialsNum=?,travellerType=? " +
                " where id =? ";
        return template.update(sql,name,sex,phoneNum,credentialsType,credentialsNum,travellerType,id);
    }

    @Override
    public int saveOrder(String uuid, Date date, String peopleContent, String orderDesc, String payType, int orderStatus, String productId, String uid){
        sql = "insert into ssm.orders values (?,?,?,?,?,?,?,?,?)";
        return template.update(sql,uuid,null,date,peopleContent,orderDesc,
                payType,orderStatus,productId,uid);
    }

    @Override
    public int collectOrderAndTraveller(String orderId, String travellerId){
        sql = "insert into ssm.order_traveller values (?,?)";
        return template.update(sql,orderId,travellerId);
    }

    @Override
    public List<Route> pageQuery(int uid,int start,int numOfPage){
        sql = "select productId from ssm.orders where memberId = ?";
        List<String> strings = template.queryForList(sql, String.class, uid);
        String str = "";
        for(String id:
            strings){
            str+=id;
            str+=",";
        }
        str = str.substring(0,str.length()-1);
        sql = "select * from tab_route where rid in ("+str+") limit ?,?";
        List<Route> list =  template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),start,numOfPage);
        return list;
    }

    @Override
    public int findAllOrder(int uid){
        sql = "select count(*) from ssm.orders where memberId = ?";
        return template.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public int findStatus(String rid, int uid){
        sql = "select orderStatus from ssm.orders where memberId = ? and productId = ?";
        return template.queryForObject(sql,Integer.class,uid,rid);
    }

    @Override
    public String findOrderNum(String rid, int uid){
        sql = "select orderNum from ssm.orders where memberId = ? and productId = ?";
        return template.queryForObject(sql,String.class,uid,rid);
    }

    @Override
    public void refund(int orderNum, String orderDesc){
        sql = "update ssm.orders set orderDesc = ?,orderStatus = 0 where orderNum = ?";
        template.update(sql,orderDesc,orderNum);
    }
}
