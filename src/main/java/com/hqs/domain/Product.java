package com.hqs.domain;

import com.hqs.util.DateFormat;

import java.util.Date;

public class Product {
    private String id;              //无意义，主键uuid
    private String productNum;      //商品编号，唯一，不为空
    private String productName;     //产品名称（路线名称）
    private String cityName;        //出发城市
    private Date DepartureTime;     //出发时间
    private int productPrice;       //产品价格
    private String productDesc;     //产品描述
    private int productStatus;       //状态（0 关闭 1开启）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return DepartureTime;
    }

    public String getFormatDepartureTime() {
        return DateFormat.dateFormat(DepartureTime);
    }

    public void setDepartureTime(Date departureTime) {
        DepartureTime = departureTime;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getShowProductStatus() {
        if(productStatus == 1)
        return "开启";
        else
            return "关闭";
    }

    public int getProductStatus(int productStaus) {
        return (productStaus);
    }

    public void setProductStatus(int productStaus) {
        this.productStatus = productStaus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", DepartureTime=" + DepartureTime +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", ProductStaus=" + productStatus +
                '}';
    }
}
