/**
 * 
 */
package com.smoothstack.utopia.domain;

import java.util.Objects;

/**
 * @author joshu
 *
 */
public class Route {
	private Integer id = null;
	private String orig = null;
	private String dest = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dest, id, orig);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Objects.equals(dest, other.dest) && Objects.equals(id, other.id) && Objects.equals(orig, other.orig);
	}
}
