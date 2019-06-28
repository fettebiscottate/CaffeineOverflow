package com.caffeine.overflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.google.gwt.i18n.shared.DateTimeFormat;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idAnswer;
	private int idQuestion;
	private String text;
	private String userName;
	private Date date;
	private String judgeEmail;
	private String rating;
	private List<String> linkList;

	/**
	 * Creates an empty Answer object.
	 * 
	 * @since 1.0
	 */
	public Answer() {

	}

	/**
	 * 
	 * @param idAnswer
	 * @param idQuestion
	 * @param text
	 * @param userName
	 * @param linkList
	 * @since 1.0
	 * @see List
	 */
	public Answer(int idAnswer, int idQuestion, String text, String userName, List<String> linkList) {
		this.idAnswer = idAnswer;
		this.idQuestion = idQuestion;
		this.text = text;
		this.userName = userName;
		this.linkList = linkList;
		this.date = new Date();
		this.judgeEmail = "";
		this.rating = "";
	}

	/**
	 * 
	 * @param idAnswer
	 * @param idQuestion
	 * @param text
	 * @param userName
	 * @param linkList
	 * @param judgeEmail
	 * @param rating
	 * @since 1.0
	 * @see List
	 */
	public Answer(int idAnswer, int idQuestion, String text, String userName, List<String> linkList, String judgeEmail,
			String rating) {
		this.idAnswer = idAnswer;
		this.idQuestion = idQuestion;
		this.text = text;
		this.userName = userName;
		this.linkList = linkList;
		this.date = new Date();
		this.judgeEmail = judgeEmail;
		this.rating = rating;
	}

	/**
	 * @return
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * @return
	 */
	public String getDay() {
		return DateTimeFormat.getFormat("dd-MM-yyyy").format(this.date);
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
	public int getIdQuestion() {
		return this.idQuestion;
	}

	/**
	 * @return
	 */
	public String getJudgeEmail() {
		return this.judgeEmail;
	}

	/**
	 * @return
	 */
	public List<String> getLinkList() {
		return this.linkList;
	}

	/**
	 * @return
	 */
	public String getRating() {
		return this.rating;
	}

	/**
	 * @return
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @return
	 */
	public String getTime() {
		return DateTimeFormat.getFormat("hh:mm:ss").format(this.date);
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param nId
	 */
	public void setIdAnswer(int nId) {
		this.idAnswer = nId;
	}

	/**
	 * @param idQuestion
	 */
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param judgeEmail
	 */
	public void setJudgeEmail(String judgeEmail) {
		this.judgeEmail = judgeEmail;
	}

	/**
	 * @param rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @param linkList
	 */
	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Answer [idAnswer=" + idAnswer + ", idQuestion=" + idQuestion + ", text=" + text + ", userName="
				+ userName + ", date=" + date + ", judgeEmail=" + judgeEmail + ", rating=" + rating + ", linkList="
				+ linkList + "]";
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return Objects.hash(date, idAnswer, idQuestion, judgeEmail, linkList, rating, text, userName);
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
		Answer other = (Answer) obj;
		return Objects.equals(date, other.date) && idAnswer == other.idAnswer && idQuestion == other.idQuestion
				&& Objects.equals(judgeEmail, other.judgeEmail) && Objects.equals(linkList, other.linkList)
				&& Objects.equals(rating, other.rating) && Objects.equals(text, other.text)
				&& Objects.equals(userName, other.userName);
	}

}