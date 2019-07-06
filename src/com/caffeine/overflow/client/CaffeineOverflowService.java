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

	
	List<Category> getCategories();

	Category getCategory(int id);

	String getUser(String email);

	List<Question> getQuestions();

	List<Answer> getAnswers(int idQuestion);

	List<Answer> getAllAnswers();

	List<String> getUsers();

	void init();

	boolean addQuestion(Question question);

	boolean addAnswer(Answer answer);

	boolean createRating(Answer answer, String email, String rating);

	int logIn(String userName, String password);

	String appoint(String email); 

	boolean addCategory(Category category, String root);

	List<Answer> orderAnswers(int idQuestion);

	boolean removeQuestion(int id);

	boolean removeAnswer(int id);

	boolean renameCategory(String categoryName, String newCategoryName);

	String signUp(List<String> userData, List<String> userSocial);

}
