package com.smoothstack.utopia.domain;

public class Payment {
	private Integer id = null;
	private String sid = null;
	private Integer refund = null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Integer getRefund() {
		return refund;
	}
	public void setRefund(Integer refund) {
		this.refund = refund;
	}
}
