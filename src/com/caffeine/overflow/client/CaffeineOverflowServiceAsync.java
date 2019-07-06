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

	void getCategories(AsyncCallback<List<Category>> callback);

	void getCategory(int id, AsyncCallback<Category> callback);

	void getUser(String email, AsyncCallback<String> callback);

	void getQuestions(AsyncCallback<List<Question>> callback);

	void getAnswers(int idQuestion, AsyncCallback<List<Answer>> callback);

	void getAllAnswers(AsyncCallback<List<Answer>> callback);

	void getUsers(AsyncCallback<List<String>> callback);

	void init(AsyncCallback<Void> callback);

	void addQuestion(Question question, AsyncCallback<Boolean> callback);
	
	void addAnswer(Answer answer, AsyncCallback<Boolean> callback);

	void createRating(Answer answer, String email, String rating, AsyncCallback<Boolean> callback);
	
	void logIn(String userName, String password, AsyncCallback<Integer> callback);

	void appoint(String email, AsyncCallback<String> callback);

	void addCategory(Category category, String root, AsyncCallback<Boolean> callback);

	void orderAnswers(int idQuestion, AsyncCallback<List<Answer>> callback);

	void removeQuestion(int id, AsyncCallback<Boolean> callback);

	void removeAnswer(int id, AsyncCallback<Boolean> callback);

	void renameCategory(String categoryName, String newCategoryName, AsyncCallback<Boolean> callback);

	void signUp(List<String> userData, List<String> userSocial, AsyncCallback<String> callback);

}
