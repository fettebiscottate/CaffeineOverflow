package com.caffeine.overflow.client;

import java.util.List;

import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
@RemoteServiceRelativePath("CaffeineOverflow")
public interface CaffeineOverflowService extends RemoteService {

	
	/**
	 * @return
	 */
	List<Category> getCategories();

	/**
	 * @param idCategory
	 * @return
	 */
	Category getCategory(int idCategory);

	/**
	 * @param email
	 * @return
	 */
	String getUser(String email);

	/**
	 * @return
	 */
	List<Question> getQuestions();

	/**
	 * @param idQuestion
	 * @return
	 */
	List<Answer> getAnswers(int idQuestion);

	/**
	 * @return
	 */
	List<Answer> getAllAnswers();

	/**
	 * @return
	 */
	List<String> getUsers();

	/**
	 * 
	 */
	void init();

	/**
	 * @param question
	 * @return
	 */
	boolean addQuestion(Question question);

	/**
	 * @param answer
	 * @return
	 */
	boolean addAnswer(Answer answer);

	/**
	 * @param answer
	 * @param email
	 * @param rating
	 * @return
	 */
	boolean createRating(Answer answer, String email, String rating);

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	int logIn(String userName, String password);

	/**
	 * @param email
	 * @return
	 */
	String appoint(String email); 

	/**
	 * @param category
	 * @param root
	 * @return
	 */
	boolean addCategory(Category category, String root);

	/**
	 * @param idQuestion
	 * @return
	 */
	List<Answer> orderAnswers(int idQuestion);

	/**
	 * @param idQuestion
	 * @return
	 */
	boolean removeQuestion(int idQuestion);

	/**
	 * @param idAnswer
	 * @return
	 */
	boolean removeAnswer(int idAnswer);

	/**
	 * @param categoryName
	 * @param newCategoryName
	 * @return
	 */
	boolean renameCategory(String categoryName, String newCategoryName);

	/**
	 * @param userData
	 * @param userSocial
	 * @return
	 */
	String signUp(List<String> userData, List<String> userSocial);

}
