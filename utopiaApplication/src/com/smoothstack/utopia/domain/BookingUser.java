package com.smoothstack.utopia.domain;

import java.util.Objects;

public class BookingUser {
	private Integer bid = null;
	private Integer uid = null;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bid, uid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingUser other = (BookingUser) obj;
		return Objects.equals(bid, other.bid) && Objects.equals(uid, other.uid);
	}
}
