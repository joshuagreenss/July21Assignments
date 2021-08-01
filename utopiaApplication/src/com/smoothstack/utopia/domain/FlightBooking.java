package com.smoothstack.utopia.domain;

import java.util.Objects;

public class FlightBooking {
	private Integer flight = null;
	private Integer booking = null;

	public Integer getFlight() {
		return flight;
	}

	public void setFlight(Integer flight) {
		this.flight = flight;
	}

	public Integer getBooking() {
		return booking;
	}

	public void setBooking(Integer booking) {
		this.booking = booking;
	}

	@Override
	public int hashCode() {
		return Objects.hash(booking, flight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightBooking other = (FlightBooking) obj;
		return Objects.equals(booking, other.booking) && Objects.equals(flight, other.flight);
	}
}
