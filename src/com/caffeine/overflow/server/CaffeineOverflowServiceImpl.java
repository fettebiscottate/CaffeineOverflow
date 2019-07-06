package com.caffeine.overflow.server;

import java.util.List;

import com.caffeine.overflow.client.CaffeineOverflowService;
import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 *
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CaffeineOverflowServiceImpl extends RemoteServiceServlet implements CaffeineOverflowService {

	public static final String AMBIENTE = "Ambiente";

	public static final String ANIMALI = "Animali";

	public static final String ARTECULTURA = "Arte e cultura";

	public static final String ELETTRONICATECNOLOGIA = "Elettronica e tecnologia";

	public static final String SPORT = "Sport";

	public static final String SVAGO = "Svago";

	@Override
	public List<Category> getCategories() {
		return DataBase.readCategories();
	}

	@Override
	public Category getCategory(int id) {
		return DataBase.readCategory(id);
	}

	@Override
	public String getUser(String email) {
		return DataBase.readUserData(email);
	}

	@Override
	public List<Question> getQuestions() {
		return DataBase.readQuestions();
	}

	@Override
	public List<Answer> getAnswers(int idQuestion) {
		return DataBase.readAnswer(idQuestion);
	}

	@Override
	public List<Answer> getAllAnswers() {
		return DataBase.readAnswers();
	}

	@Override
	public List<String> getUsers() {
		return DataBase.readUsers();
	}

	@Override
	public void init() {
		DataBase.createAdmin();
		DataBase.createCategory(new Category(AMBIENTE), null);
		DataBase.createCategory(new Category(ANIMALI), null);
		DataBase.createCategory(new Category(ARTECULTURA), null);
		DataBase.createCategory(new Category(ELETTRONICATECNOLOGIA), null);
		DataBase.createCategory(new Category(SPORT), null);
		DataBase.createCategory(new Category(SVAGO), null);
	}

	@Override
	public boolean addQuestion(Question question) {
		return DataBase.createQuestion(question);
	}

	@Override
	public boolean addAnswer(Answer answer) {
		return DataBase.createAnswer(answer);
	}

	@Override
	public boolean createRating(Answer answer, String email, String rating) {
		return DataBase.createRating(answer, email, rating);
	}

	@Override
	public int logIn(String userName, String password) {
		return DataBase.logIn(userName, password);
	}

	@Override
	public String appoint(String email) {
		return DataBase.appointJudge(email);
	}

	@Override
	public boolean addCategory(Category category, String root) {
		return DataBase.createCategory(category, root);
	}

	@Override
	public List<Answer> orderAnswers(int idQuestion) {
		return DataBase.sortAnswers(idQuestion);
	}

	@Override
	public boolean removeQuestion(int id) {
		return DataBase.removeQuestion(id);
	}

	@Override
	public boolean removeAnswer(int id) {
		return DataBase.deleteAnswer(id);
	}

	@Override
	public boolean renameCategory(String categoryName, String newCategoryName) {
		return DataBase.updateCategory(categoryName, newCategoryName);
	}

	@Override
	public String signUp(List<String> userData, List<String> userSocial) {
		return DataBase.createUser(userData, userSocial);
	}

}
