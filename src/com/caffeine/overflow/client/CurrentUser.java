package com.caffeine.overflow.client;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class CurrentUser {

	static String email = "";
	static int accountType = -1;
	
	private CurrentUser() {
		throw new IllegalStateException("Utility class");
	}

}