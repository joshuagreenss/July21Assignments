package com.smoothstack.utopia.domain;

import java.util.Objects;

public class BookingAgent {
	private Integer bid = null;
	private Integer aid = null;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aid, bid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingAgent other = (BookingAgent) obj;
		return Objects.equals(aid, other.aid) && Objects.equals(bid, other.bid);
	}
}
