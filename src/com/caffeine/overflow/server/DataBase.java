package com.caffeine.overflow.server;

import java.io.File;

import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class DataBase {

	public static final String CATEGORIES = "Categories";

	public static final String QUESTIONS = "Questions";

	public static final String USERS = "Users";

	public static final String ANSWERS = "Answers";

	public static final String DATABASENAME = "db";

	private static DB getDB() {
		return DBMaker.newFileDB(new File(DATABASENAME)).make();
	}

    public static boolean createCategory(Category category, String padre) {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Category> categories = dataBase.getTreeMap(CATEGORIES);

		if (isValid(category.getName()) || (category.getName().length() == 0)) {
			return false;
		} else {
				final int id = (categories.size() + 1);
				boolean catPadreCheck = false;
				Category cPadre = null;
				category.setIdCategory(id);

				if (padre != null) {
					if (!categories.isEmpty()) {
						for (Iterator<Entry<Integer, Category>> iterator = categories.entrySet().iterator(); iterator
								.hasNext();) {
							final Map.Entry<Integer, Category> categorie = iterator.next();
							if (categorie.getValue().getName().equals(padre)) {
								catPadreCheck = true;
								cPadre = categorie.getValue();
							}
						}
					}
				} else {
					cPadre = new Category("null");
					catPadreCheck = true;
				}
				if (!catPadreCheck) {
					dataBase.commit();
					return false;
				} else {
					category.setFather(cPadre);
					categories.put(id, category);
					if (cPadre != null) {
						for (Iterator<Entry<Integer, Category>> iterator = categories.entrySet().iterator(); iterator
								.hasNext();) {
							final Map.Entry<Integer, Category> categorie = iterator.next();
							if (categorie.getValue().getName().equals(padre)) {
								final Category p = categorie.getValue();
								p.setSubCategories(category);
								categories.remove(p.getId());
								categories.put(p.getId(), p);
							}
						}
					}
				}
				dataBase.commit();
				return true;
		}
	}
	
	public static List<Category> readCategories() {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Category> categories = dataBase.getTreeMap(CATEGORIES);

		final List<Category> categorie = new ArrayList<>();
		if (!categories.isEmpty()) {
			for (Iterator<Entry<Integer, Category>> iterator = categories.entrySet().iterator(); iterator.hasNext();) {
				final Map.Entry<Integer, Category> c = iterator.next();
				categorie.add(c.getValue());
			}
		}
		return categorie;
	}
	
	private DataBase() {
		throw new IllegalStateException("Utility class");
	}

}