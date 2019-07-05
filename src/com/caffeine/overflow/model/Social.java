package com.caffeine.overflow.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Social implements Serializable {

	private String userName;
	private String site;

	/**
	 * Creates an empty Social object.
	 * @since     1.0
	 */
	public Social() {

	}

	
	/**
	 * @param userName
	 * @param site
	 * @since     1.0
	 */
	public Social(String userName, String site) {
		this.userName = userName;
		this.site = site;
	}

	
	/**
	 * @return
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Social [userName=" + userName + ", site=" + site + "]";
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return Objects.hash(site, userName);
	}

	/**
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Social other = (Social) obj;
		return Objects.equals(site, other.site) && Objects.equals(userName, other.userName);
	}

	
}
