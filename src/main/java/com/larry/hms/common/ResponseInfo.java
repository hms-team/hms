package com.larry.hms.common;

public class ResponseInfo<T> {
	private String rtnCode;//״̬��
	private String isPagination;//�Ƿ��ҳ
	private int totalCount;//����
	private int currentPage=1;//��ǰҳ��Ĭ��Ϊ��һҳ
	private int numPerPage=20;//ÿҳ��ʾ��Ŀ��Ĭ��Ϊ20
	private T data;//��������
	private String rtnMessage;//״̬��Ϣ��
	
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
