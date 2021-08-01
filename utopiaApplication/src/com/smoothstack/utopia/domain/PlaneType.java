package com.smoothstack.utopia.domain;

import java.util.Objects;

public class PlaneType {
	private Integer id = null;
	private Integer capacity = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaneType other = (PlaneType) obj;
		return Objects.equals(capacity, other.capacity) && Objects.equals(id, other.id);
	}
}
