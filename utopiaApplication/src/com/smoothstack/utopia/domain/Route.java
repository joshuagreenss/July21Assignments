/**
 * 
 */
package com.smoothstack.utopia.domain;

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
}
