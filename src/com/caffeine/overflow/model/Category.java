package com.caffeine.overflow.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Category father;
	private List<Category> subCategories;
	private int idCategory;

	/**
	 * Creates an empty Category object.
	 * @since 1.0
	 */
	public Category() {

	}

	/**
	 * @param nome
	 * @since 1.0
	 * @see ArrayList
	 */
	public Category(String nome) {
		this.subCategories = new ArrayList<>();
		this.nome = nome;
	}

	/**
	 * @return
	 */
	public Category getFather() {
		return this.father;
	}

	/**
	 * @return
	 */
	public int getId() {
		return this.idCategory;
	}

	/**
	 * @return
	 */
	public String getName() {
		return this.nome;
	}

	/**
	 * @return
	 */
	public List<Category> getSubCategories() {
		return this.subCategories;
	}

	/**
	 * @param padre
	 */
	public void setFather(Category padre) {
		this.father = padre;
	}

	/**
	 * @param id
	 */
	public void setIdCategory(int id) {
		this.idCategory = id;
	}

	/**
	 * @param nuovo
	 */
	public void setName(String nuovo) {
		this.nome = nuovo;
	}

	/**
	 * @param categoria
	 */
	public void setSubCategories(Category categoria) {
		this.subCategories.add(categoria);
	}

	/**
	 * @param categorieFiglie
	 */
	public void setSubCategories(List<Category> categorieFiglie) {
		this.subCategories = categorieFiglie;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return idCategory;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Category [nome=" + nome + ", father=" + father + ", subCategories=" + subCategories + ", idCategory="
				+ idCategory + "]";
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return Objects.hash(father, idCategory, nome, subCategories);
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
		Category other = (Category) obj;
		return Objects.equals(father, other.father) && idCategory == other.idCategory
				&& Objects.equals(nome, other.nome) && Objects.equals(subCategories, other.subCategories);
	}

}
