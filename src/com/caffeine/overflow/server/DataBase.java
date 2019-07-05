package com.caffeine.overflow.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.caffeine.overflow.model.Admin;
import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Judge;
import com.caffeine.overflow.model.Question;
import com.caffeine.overflow.model.Social;
import com.caffeine.overflow.model.User;

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



	public static void createAdmin() {
		final DB dataBase = getDB();
		final BTreeMap<String, User> users = dataBase.getTreeMap(USERS);
		List<Social> socialList = new ArrayList<>();
		socialList.add(new Social("admin", "localhost"));
		socialList.add(new Social("administrator", "192.168.0.1"));
		socialList.add(new Social("tttt", "www.w3c.org"));
		final Admin admin = new Admin("admin", "admin", "admin@admin.com", "admin", "admin", "F", "Domodossola",
				"01/01/1970", "Via degli Dei", socialList);
		users.put(admin.getEmail(), admin);
		dataBase.commit();
		dataBase.close();
	}

	public static boolean createAnswer(Answer answer) {
		final DB dataBase = getDB();
		final BTreeMap<Integer, Answer> questions = dataBase.getTreeMap(ANSWERS);
		switch (answer.getText().length()) {
		case 0:
			return false;
		default:
			answer.setIdAnswer(questions.isEmpty() ? 0 : (questions.lastKey() + 1));
			questions.put(answer.getIdAnswer(), answer);
			dataBase.commit();
			dataBase.close();
			return true;
		}
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

	public static boolean createQuestion(Question question) {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Question> questions = dataBase.getTreeMap(QUESTIONS);
		switch (question.getText().length()) {
		case 0:
			return false;
		default:
			final int id = setQuestionId();
			question.setIdQuestion(id);
			questions.put(question.getIdQuestion(), question);
			dataBase.commit();
			dataBase.close();
			return true;
		}

	}



	public static String createUser(List<String> userData, List<String> social) {

		final DB dataBase = getDB();
		BTreeMap<String, User> users;

		if (!isValid(userData)) {
			return "Missing data";
		} else {
			if (isRegistered(userData.get(2))) {
				return "utente già registrato";
			} else {
				users = dataBase.getTreeMap(USERS);

				final List<Social> socialList = new ArrayList<>();
				for (int i = 0; i < social.size(); i = i + 2) {
					socialList.add(new Social(social.get(i), social.get(i + 1)));
				}

				final User user = new User(userData.get(0), userData.get(1), userData.get(2), userData.get(3),
						userData.get(4), userData.get(5), userData.get(6), userData.get(7), userData.get(8),
						socialList);
				users.put(user.getEmail(), user);
				dataBase.commit();
				dataBase.close();
				return "Success";
			}
		}
	}

	public static boolean deleteAnswer(int id) {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Answer> questions = dataBase.getTreeMap(ANSWERS);
		questions.remove(id);
		dataBase.commit();
		dataBase.close();
		return true;
	}

	private static DB getDB() {
		return DBMaker.newFileDB(new File(DATABASENAME)).make();
	}

	private static boolean isRegistered(String email) {

		final DB dataBase = getDB();
		final BTreeMap<String, User> users = dataBase.getTreeMap(USERS);

		if (!email.equalsIgnoreCase("admin@admin.com")) {
			for (Iterator<Entry<String, User>> iterator = users.entrySet().iterator(); iterator.hasNext();) {
				final Entry<String, User> entry = iterator.next();
				if (entry.getValue().getEmail().equalsIgnoreCase(email)) {
					return true;
				}
			}
		} else {
			return true;
		}
		return false;
	}

	private static boolean isValid(int id) {
		final DB dataBase = getDB();
		final BTreeMap<Integer, Question> questions = dataBase.getTreeMap(QUESTIONS);
		return questions.containsKey(id);
	}

	private static boolean isValid(List<String> dati) {
		for (int i = 0; i < 3; i++) {
			if (dati.get(i).isEmpty() || (dati.get(i).length() <= 0)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isValid(String nome) {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Category> categories = dataBase.getTreeMap(CATEGORIES);

		if (!categories.isEmpty()) {
			for (Iterator<Entry<Integer, Category>> iterator = categories.entrySet().iterator(); iterator.hasNext();) {
				final Entry<Integer, Category> entry = iterator.next();
				if (entry.getValue().getName().equalsIgnoreCase(nome)) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public static int logIn(String email, String password) {

		final DB dataBase = getDB();
		final BTreeMap<String, User> users = dataBase.getTreeMap(USERS);

		if (isRegistered(email)) {
			final User user = users.get(email);
			if (!user.getPassword().equals(password)) {
				return -1;
			} else {
				if ((user.getClass() == Judge.class)) {
					return 1;
				} else if ((user.getClass() == Admin.class)) {
					return 2;
				} else if ((user.getClass() == User.class)) {
					return 3;
				}
			}
		}
		return 0;

	}

	public static List<Answer> readAnswer(int idQuestion) {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Answer> questions = dataBase.getTreeMap(ANSWERS);

		final List<Answer> questionsList = new ArrayList<>();

		for (Iterator<Entry<Integer, Answer>> iterator = questions.entrySet().iterator(); iterator.hasNext();) {
			final Map.Entry<Integer, Answer> answer = iterator.next();
			if (answer.getValue().getIdQuestion() == idQuestion) {
				questionsList.add(answer.getValue());
			}
		}
		dataBase.commit();
		dataBase.close();
		Collections.reverse(questionsList);
		return questionsList;
	}

	public static List<Answer> readAnswers() {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Answer> answers = dataBase.getTreeMap(ANSWERS);

		final List<Answer> answersList = new ArrayList<>();

		for (Iterator<Entry<Integer, Answer>> iterator = answers.entrySet().iterator(); iterator.hasNext();) {
			final Map.Entry<Integer, Answer> answer = iterator.next();
			answersList.add(answer.getValue());
		}
		dataBase.commit();
		dataBase.close();

		Collections.reverse(answersList);

		return answersList;
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

	public static Category readCategory(int id) {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Category> categories = dataBase.getTreeMap(CATEGORIES);
		return categories.getOrDefault(id, null);
	}

	public static List<Question> readQuestions() {

		final DB dataBase = getDB();
		final BTreeMap<Integer, Question> questions = dataBase.getTreeMap(QUESTIONS);

		final List<Question> domande = new ArrayList<>();

		if (!questions.isEmpty()) {
			for (Iterator<Entry<Integer, Question>> iterator = questions.entrySet().iterator(); iterator.hasNext();) {
				final Map.Entry<Integer, Question> d = iterator.next();
				domande.add(d.getValue());
			}
		}
		Collections.reverse(domande);

		return domande;
	}

	public static String readUserData(String email) {

		final DB dataBase = getDB();
		final BTreeMap<String, User> users = dataBase.getTreeMap(USERS);

		final User user = users.get(email);
		StringBuilder str = new StringBuilder();
		str.append("Username :");
		str.append(user.getUserName());
		str.append("Email : ");
		str.append(email);
		str.append("Nome : ");
		str.append(user.getName());
		str.append("Cognome : ");
		str.append(user.getSurname());
		str.append("\nSesso : ");
		str.append(user.getSex());
		str.append("\nData Nascita : ");
		str.append(user.getBirthDate());
		str.append("\nLuogo Nascita : ");
		str.append(user.getBirthPlace());
		str.append("\nIndirizzo : ");
		str.append(user.getLivingPlace());

		final List<Social> listaSocial = user.getSocial();
		Social accountSocial;

		for (int i = 0; i < listaSocial.size(); i++) {
			accountSocial = listaSocial.get(i);
			str.append("\nSocial: ");
			str.append(accountSocial.getSite());
			str.append("\nNickname: ");
			str.append(accountSocial.getUserName());
		}

		return str.toString();
	}

	public static List<String> readUsers() {

		final DB dataBase = getDB();
		final BTreeMap<String, User> users = dataBase.getTreeMap(USERS);

		final List<String> usersList = new ArrayList<>();
		final Set<String> keysU = users.keySet();

		for (Iterator<String> iterator = keysU.iterator(); iterator.hasNext();) {
			final String key = iterator.next();
			if (users.get(key).getClass() == User.class) {
				usersList.add(users.get(key).getEmail());
			}
		}

		return usersList;
	}

	public static boolean removeQuestion(int id) {

		if (!isValid(id)) {
			return false;
		} else {
			final DB dataBase = getDB();
			final BTreeMap<Integer, Question> questions = dataBase.getTreeMap(QUESTIONS);
			final BTreeMap<Integer, Answer> answers = dataBase.getTreeMap(ANSWERS);

			for (Iterator<Entry<Integer, Answer>> iterator = answers.entrySet().iterator(); iterator.hasNext();) {
				final Entry<Integer, Answer> entry = iterator.next();
				if (entry.getValue().getIdQuestion() == id) {
					answers.remove(entry.getKey());
				}
			}

			questions.remove(id);
			dataBase.commit();
			dataBase.close();
			return true;
		}
	}

	private static int setQuestionId() {
		final DB dataBase = getDB();
		final BTreeMap<Integer, Question> questions = dataBase.getTreeMap(QUESTIONS);
		return questions.isEmpty() ? 0 : (questions.lastKey() + 1);
	}

	public static boolean updateCategory(String oldNome, String newNome) {
		final DB dataBase = getDB();
		final BTreeMap<Integer, Category> categories = dataBase.getTreeMap(CATEGORIES);
		boolean result = false;

		if (newNome.length() == 0) {
			return result;
		}

		if (!categories.isEmpty()) {
			for (Iterator<Entry<Integer, Category>> iterator = categories.entrySet().iterator(); iterator.hasNext();) {
				final Map.Entry<Integer, Category> c = iterator.next();
				if (c.getValue().getName().contentEquals(oldNome)) {
					final Category temp = c.getValue();
					temp.setName(newNome);
					categories.remove(temp.getId());
					categories.put(temp.getId(), temp);
					result = true;
				}
			}
		}
		dataBase.commit();
		dataBase.close();
		return result;
	}

	private DataBase() {
		throw new IllegalStateException("Utility class");
	}

}
