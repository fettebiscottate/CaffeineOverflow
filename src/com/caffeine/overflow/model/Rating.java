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
public class Rating implements Serializable {

	private String userName;
	private int idAnswer;
	private int ratingVote;

	/**
	 * Creates an empty Rating object.
	 * 
	 * @since 1.0
	 */
	public Rating() {
	}

	/**
	 * @param nomeUtente
	 * @param id
	 * @param voto
	 * @since 1.0
	 */
	public Rating(String nomeUtente, int id, int voto) {
		this.userName = nomeUtente;
		this.idAnswer = id;
		this.ratingVote = voto;
	}

	/**
	 * @return
	 */
	public int getIdAnswer() {
		return this.idAnswer;
	}

	/**
	 * @return
	 */
	public int getRatingVote() {
		return this.ratingVote;
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
	 * @param idAnswer the idAnswer to set
	 */
	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	/**
	 * @param ratingVote the ratingVote to set
	 */
	public void setRatingVote(int ratingVote) {
		this.ratingVote = ratingVote;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Rating [userName=" + userName + ", idAnswer=" + idAnswer + ", ratingVote=" + ratingVote + "]";
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idAnswer, ratingVote, userName);
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
		Rating other = (Rating) obj;
		return idAnswer == other.idAnswer && ratingVote == other.ratingVote && Objects.equals(userName, other.userName);
	}

}
