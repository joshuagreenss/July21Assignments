package com.smoothstack.utopia.domain;

import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(id, refund, sid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id) && Objects.equals(refund, other.refund) && Objects.equals(sid, other.sid);
	}
}
