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
	
	private DataBase() {
		throw new IllegalStateException("Utility class");
	}

}