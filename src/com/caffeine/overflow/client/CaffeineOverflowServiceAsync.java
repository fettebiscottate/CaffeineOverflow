package com.caffeine.overflow.client;

import java.util.List;

import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public interface CaffeineOverflowServiceAsync {

	/**
	 * @param callback
	 */
	void getCategories(AsyncCallback<List<Category>> callback);

	/**
	 * @param idCategory
	 * @param callback
	 */
	void getCategory(int idCategory, AsyncCallback<Category> callback);

	/**
	 * @param email
	 * @param callback
	 */
	void getUser(String email, AsyncCallback<String> callback);

	/**
	 * @param callback
	 */
	void getQuestions(AsyncCallback<List<Question>> callback);

	/**
	 * @param idQuestion
	 * @param callback
	 */
	void getAnswers(int idQuestion, AsyncCallback<List<Answer>> callback);

	/**
	 * @param callback
	 */
	void getAllAnswers(AsyncCallback<List<Answer>> callback);

	/**
	 * @param callback
	 */
	void getUsers(AsyncCallback<List<String>> callback);

	/**
	 * @param callback
	 */
	void init(AsyncCallback<Void> callback);

	/**
	 * @param question
	 * @param callback
	 */
	void addQuestion(Question question, AsyncCallback<Boolean> callback);
	
	/**
	 * @param answer
	 * @param callback
	 */
	void addAnswer(Answer answer, AsyncCallback<Boolean> callback);

	/**
	 * @param answer
	 * @param email
	 * @param rating
	 * @param callback
	 */
	void createRating(Answer answer, String email, String rating, AsyncCallback<Boolean> callback);
	
	/**
	 * @param userName
	 * @param password
	 * @param callback
	 */
	void logIn(String userName, String password, AsyncCallback<Integer> callback);

	/**
	 * @param email
	 * @param callback
	 */
	void appoint(String email, AsyncCallback<String> callback);

	/**
	 * @param category
	 * @param root
	 * @param callback
	 */
	void addCategory(Category category, String root, AsyncCallback<Boolean> callback);

	/**
	 * @param idQuestion
	 * @param callback
	 */
	void orderAnswers(int idQuestion, AsyncCallback<List<Answer>> callback);

	/**
	 * @param idQuestion
	 * @param callback
	 */
	void removeQuestion(int idQuestion, AsyncCallback<Boolean> callback);

	/**
	 * @param idAnswer
	 * @param callback
	 */
	void removeAnswer(int idAnswer, AsyncCallback<Boolean> callback);

	/**
	 * @param categoryName
	 * @param newCategoryName
	 * @param callback
	 */
	void renameCategory(String categoryName, String newCategoryName, AsyncCallback<Boolean> callback);

	/**
	 * @param userData
	 * @param userSocial
	 * @param callback
	 */
	void signUp(List<String> userData, List<String> userSocial, AsyncCallback<String> callback);

}
