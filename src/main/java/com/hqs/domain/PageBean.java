package com.hqs.domain;

import java.util.List;

public class PageBean<T> {
	private int total;//数据总数
	private int nPage;//每页个数
	private int nowPageNum;//当前页码
	private int totalPage;//总页码
	private List<T> list;//从数据库查询出来的数据
	
	public int getNowPageNum() {
		return nowPageNum;
	}
	public void setNowPageNum(int nowPageNum) {
		this.nowPageNum = nowPageNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getnPage() {
		return nPage;
	}
	public void setnPage(int nPage) {
		this.nPage = nPage;
	}

	
}
