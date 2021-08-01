package com.smoothstack.utopia.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Passenger {
	private Integer id = null;
	private Integer bid = null;
	private String gName = null;
	private String fName = null;
	private LocalDate dob = null;
	private String gender = null;
	private String address = null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, bid, dob, fName, gName, gender, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		return Objects.equals(address, other.address) && Objects.equals(bid, other.bid)
				&& Objects.equals(dob, other.dob) && Objects.equals(fName, other.fName)
				&& Objects.equals(gName, other.gName) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id);
	}
}
