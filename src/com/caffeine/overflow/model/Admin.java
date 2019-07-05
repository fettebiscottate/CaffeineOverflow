package com.caffeine.overflow.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Admin extends User implements Serializable {

	/**
	 * Creates an empty Admin object.
	 * 
	 * @since 1.0
	 * @see User
	 */
	public Admin() {

	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 * @param name
	 * @param surname
	 * @param sesso
	 * @param birthDate
	 * @param birthPlace
	 * @param livingPlace
	 * @param socialNetworkList
	 * @since 1.0
	 * @see List
	 * @see Social
	 * @see User
	 */
	public Admin(String userName, String password, String email, String name, String surname, String sesso,
			String birthDate, String birthPlace, String livingPlace, List<Social> socialNetworkList) {
		super(userName, password, email, name, surname, sesso, birthDate, birthPlace, livingPlace, socialNetworkList);
	}

}