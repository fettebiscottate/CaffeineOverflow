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
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idQuestion;
	private String text;
	private String userName;
	private String categoryName;
	private Date date;
	private List<String> linkList;

	/**
	 * Creates an empty Question object.
	 * 
	 * @since 1.0
	 */
	public Question() {

	}

	/**
	 * @param id
	 * @param testo
	 * @param nomeCategoria
	 * @param nomeUtente
	 * @param link
	 * @since 1.0
	 * @see List
	 */
	public Question(int id, String testo, String nomeCategoria, String nomeUtente, List<String> link) {
		this.idQuestion = id;
		this.text = testo;
		this.categoryName = nomeCategoria;
		this.userName = nomeUtente;
		this.date = new Date();
		this.linkList = link;
	}

	/**
	 * @return
	 */
	public String getCategoryName() {
		return this.categoryName;
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
	public int getIdQuestion() {
		return this.idQuestion;
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
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param nId
	 */
	public void setIdQuestion(int nId) {
		this.idQuestion = nId;
	}

	/**
	 * @param linkList the linkList to set
	 */
	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Question [idQuestion=" + idQuestion + ", text=" + text + ", userName=" + userName + ", categoryName="
				+ categoryName + ", date=" + date + ", linkList=" + linkList + "]";
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return Objects.hash(categoryName, date, idQuestion, linkList, text, userName);
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
		Question other = (Question) obj;
		return Objects.equals(categoryName, other.categoryName) && Objects.equals(date, other.date)
				&& idQuestion == other.idQuestion && Objects.equals(linkList, other.linkList)
				&& Objects.equals(text, other.text) && Objects.equals(userName, other.userName);
	}

}