package com.smoothstack.utopia.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
	private Integer id = null;
	private Integer route = null;
	private Integer plane = null;
	private LocalDateTime departure = null;
	private Integer reserved = null;
	private Float price = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoute() {
		return route;
	}

	public void setRoute(Integer route) {
		this.route = route;
	}

	public Integer getPlane() {
		return plane;
	}

	public void setPlane(Integer plane) {
		this.plane = plane;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public Integer getReserved() {
		return reserved;
	}

	public void setReserved(Integer reserved) {
		this.reserved = reserved;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departure, id, plane, price, reserved, route);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(departure, other.departure) && Objects.equals(id, other.id)
				&& Objects.equals(plane, other.plane) && Objects.equals(price, other.price)
				&& Objects.equals(reserved, other.reserved) && Objects.equals(route, other.route);
	}
}
