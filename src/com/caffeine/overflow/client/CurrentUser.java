package com.caffeine.overflow.client;

/**
 * This class consists of a private constructor and two attributes to store the
 * current logged-in user's data
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class CurrentUser {

	static String email = "";
	static int accountType = -1;

	/**
	 * Private constructor will prevent the instantiation of this class
	 */
	private CurrentUser() {
		throw new IllegalStateException("Utility class");
	}

}