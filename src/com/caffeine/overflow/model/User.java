package com.caffeine.overflow.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String sex;
	private String birthPlace;
	private String birthDate;
	private String livingPlace;
	private List<Social> socialNetworkList;

	/**
	 * Creates an empty User object.
	 * @since     1.0
	 */
	public User() {

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
	 * @since     1.0
	 * @see       List
	 * @see       Social
	 */
	public User(String nomeUtente, String password, String email, String nome, String cognome, String sesso,
			String dataNascita, String luogoNascita, String luogoDomRes, List<Social> listaSocialNetwork) {
		this.userName = nomeUtente;
		this.password = password;
		this.email = email;
		this.name = nome;
		this.surname = cognome;
		this.sex = sesso;
		this.birthDate = dataNascita;
		this.birthPlace = luogoNascita;
		this.livingPlace = luogoDomRes;
		this.socialNetworkList = listaSocialNetwork;
	}

	/**
	 * @return
	 */
	public String getBirthDate() {
		return this.birthDate;
	}

	/**
	 * @return
	 */
	public String getBirthPlace() {
		return this.birthPlace;
	}

	
	/**
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}

	
	/**
	 * @return
	 */
	public String getLivingPlace() {
		return this.livingPlace;
	}

	
	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	
	/**
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}

	
	/**
	 * @return
	 */
	public String getSex() {
		return this.sex;
	}

	
	/**
	 * @return
	 */
	public List<Social> getSocial() {
		return this.socialNetworkList;
	}

	
	/**
	 * @return
	 */
	public String getSurname() {
		return this.surname;
	}

	
	/**
	 * @return
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @return the socialNetworkList
	 */
	public List<Social> getSocialNetworkList() {
		return socialNetworkList;
	}

	/**
	 * @param socialNetworkList the socialNetworkList to set
	 */
	public void setSocialNetworkList(List<Social> socialNetworkList) {
		this.socialNetworkList = socialNetworkList;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param livingPlace
	 */
	public void setLivingPlace(String livingPlace) {
		this.livingPlace = livingPlace;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", surname=" + surname + ", sex=" + sex + ", birthPlace=" + birthPlace + ", birthDate=" + birthDate
				+ ", livingPlace=" + livingPlace + ", socialNetworkList=" + socialNetworkList + "]";
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return Objects.hash(birthDate, birthPlace, email, livingPlace, name, password, sex, socialNetworkList, surname,
				userName);
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
		User other = (User) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(birthPlace, other.birthPlace)
				&& Objects.equals(email, other.email) && Objects.equals(livingPlace, other.livingPlace)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(sex, other.sex) && Objects.equals(socialNetworkList, other.socialNetworkList)
				&& Objects.equals(surname, other.surname) && Objects.equals(userName, other.userName);
	}
	
}