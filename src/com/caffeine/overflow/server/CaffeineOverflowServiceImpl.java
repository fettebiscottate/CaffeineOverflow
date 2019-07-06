package com.caffeine.overflow.server;

import java.util.List;

import com.caffeine.overflow.client.CaffeineOverflowService;
import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * {@value #AMBIENTE} categoria Ambiente
 * {@value #ANIMALI} categoria Animali
 * {@value #ARTECULTURA} categoria Arte e cultura
 * {@value #ELETTRONICATECNOLOGIA} categoria Elettronica e tecnologia
 * {@value #SPORT} categoria Sport
 * {@value #SVAGO} categoria Svago
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
	
	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public List<Category> getCategories() {
		return DataBase.readCategories();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public Category getCategory(int id) {
		return DataBase.readCategory(id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public String getUser(String email) {
		return DataBase.readUserData(email);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public List<Question> getQuestions() {
		return DataBase.readQuestions();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public List<Answer> getAnswers(int idQuestion) {
		return DataBase.readAnswer(idQuestion);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public List<Answer> getAllAnswers() {
		return DataBase.readAnswers();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public List<String> getUsers() {
		return DataBase.readUsers();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
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

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean addQuestion(Question question) {
		return DataBase.createQuestion(question);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean addAnswer(Answer answer) {
		return DataBase.createAnswer(answer);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean createRating(Answer answer, String email, String rating) {
		return DataBase.createRating(answer, email, rating);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public int logIn(String userName, String password) {
		return DataBase.logIn(userName, password);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public String appoint(String email) {
		return DataBase.appointJudge(email);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean addCategory(Category category, String root) {
		return DataBase.createCategory(category, root);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public List<Answer> orderAnswers(int idQuestion) {
		return DataBase.sortAnswers(idQuestion);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean removeQuestion(int idQuestion) {
		return DataBase.removeQuestion(idQuestion);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean removeAnswer(int idAnswer) {
		return DataBase.deleteAnswer(idAnswer);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean renameCategory(String categoryName, String newCategoryName) {
		return DataBase.updateCategory(categoryName, newCategoryName);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @since 1.0
	 */
	@Override
	public String signUp(List<String> userData, List<String> userSocial) {
		return DataBase.createUser(userData, userSocial);
	}

}
