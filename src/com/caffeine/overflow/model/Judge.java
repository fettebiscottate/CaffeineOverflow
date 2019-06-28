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
public class Judge extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates an empty Judge object.
	 * @since 1.0
	 * @see User
	 */
	public Judge() {

	}

	/**
	 * @param nomeUtente
	 * @param password
	 * @param email
	 * @param nome
	 * @param cognome
	 * @param sesso
	 * @param dataNascita
	 * @param luogoNascita
	 * @param luogoDomRes
	 * @param listaSocialNetwork
	 * @since 1.0
	 * @see List
	 * @see Social
	 * @see User
	 */
	public Judge(String nomeUtente, String password, String email, String nome, String cognome, String sesso,
			String dataNascita, String luogoNascita, String luogoDomRes, List<Social> listaSocialNetwork) {
		super(nomeUtente, password, email, nome, cognome, sesso, dataNascita, luogoNascita, luogoDomRes,
				listaSocialNetwork);
	}

}