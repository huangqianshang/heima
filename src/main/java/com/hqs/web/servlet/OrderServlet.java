package com.hqs.web.servlet;

import com.hqs.domain.PageBean;
import com.hqs.domain.Route;
import com.hqs.domain.User;
import com.hqs.service.OrderService;
import com.hqs.serviceImp.OrderServiceImp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * @author: hqs
 * @date: 21:21 2021/5/27
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet{
    private OrderService service = new OrderServiceImp();

    public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int uid = 0;
        User user = (User)request.getSession().getAttribute("user");
        if(user != null) {
            uid = user.getUid();
        }


        String id = request.getParameter("id");
      String name = request.getParameter("name");
      String sex = request.getParameter("sex");
      String phoneNum = request.getParameter("phoneNum");
      String credentialsType = request.getParameter("credentialsType");
      String credentialsNum = request.getParameter("credentialsNum");
      String travellerType = request.getParameter("travellerType");

      String payType = request.getParameter("payType");
      String orderDesc = request.getParameter("orderDesc");
      String peopleContent = request.getParameter("peopleContent");
      String productId = request.getParameter("rid");

      String travellerId="";
      try{
          travellerId =  service.saveOrUpdate(id,name,sex,phoneNum,credentialsType,credentialsNum,travellerType);
      }catch(NumberFormatException e){
          writeValue("有未填写项",response);
      }
      //保存订单
        service.saveOrder(peopleContent,orderDesc,payType,productId,uid,travellerId);

      writeValue("下单完成",response);
    }

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int uid = 0;
        User user = (User)request.getSession().getAttribute("user");
        if(user != null) {
            uid = user.getUid();
        }
        String scid = request.getParameter("cid");
        String scurrentPage = request.getParameter("currentPage");
        String spageSize = request.getParameter("pageSize");
        String srname = request.getParameter("rname");
        String rname = URLDecoder.decode(srname,"utf-8");
        int cid = 0;
        int currentPage = 0;//当前页数
        int pageSize = 0;

        if(scid != null && scid.length() > 0 && !"null".equals(scid)) {
            cid = Integer.parseInt(scid);
        }
        if(scurrentPage != null && scurrentPage.length() > 0) {
            currentPage = Integer.parseInt(scurrentPage);
        }else {
            currentPage = 1;
        }
        if(spageSize != null && spageSize.length() > 0) {
            pageSize = Integer.parseInt(spageSize);
        }else {
            pageSize = 8;
        }
        if("undefined".equals(rname)||"null".equals(rname)) {
            rname = null;
        }
        PageBean<Route> page = service.pageQuery(uid,currentPage, pageSize);

//        PageBean<Route> page = service.pageQuery(cid,currentPage, pageSize, rname);
//
        writeValue(page, response);
    }

    public void findStatus(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String rid = request.getParameter("rid");
        int uid = 0;
        User user = (User)request.getSession().getAttribute("user");
        if(user != null) {
            uid = user.getUid();
        }
        Map<String,Object> map = service.findStatus(rid,uid);

        writeValue(map,response);
    }

    public void refund(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int orderNum =Integer.parseInt(request.getParameter("orderNum")) ;
        String orderDesc = request.getParameter("orderDesc");
        try{
            service.refund(orderNum,orderDesc);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
