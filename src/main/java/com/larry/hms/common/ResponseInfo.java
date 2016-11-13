package com.larry.hms.common;

public class ResponseInfo<T> {
	private String rtnCode;//状态码
	private String isPagination;//是否分页
	private int totalCount;//总数
	private int currentPage=1;//当前页，默认为第一页
	private int numPerPage=20;//每页显示数目，默认为20
	private T data;//具体数据
	private String rtnMessage;//状态信息；
	
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getIsPagination() {
		return isPagination;
	}
	public void setIsPagination(String isPagination) {
		this.isPagination = isPagination;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getRtnMessage() {
		return rtnMessage;
	}
	public void setRtnMessage(String rtnMessage) {
		this.rtnMessage = rtnMessage;
	}
	@Override
	public String toString() {
		return "ResponseInfo [rtnCode=" + rtnCode + ", isPagination=" + isPagination + ", totalCount=" + totalCount
				+ ", currentPage=" + currentPage + ", numPerPage=" + numPerPage + ", data=" + data + ", rtnMessage="
				+ rtnMessage + "]";
	}
	
	
	
}
