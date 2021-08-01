/**
 * 
 */
package com.smoothstack.utopia.domain;

import java.util.Objects;

/**
 * @author joshu
 *
 */
public class Airport {
	private String code;
	private String city;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public int hashCode() {
		return Objects.hash(city, code);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		return Objects.equals(city, other.city) && Objects.equals(code, other.code);
	}
}
