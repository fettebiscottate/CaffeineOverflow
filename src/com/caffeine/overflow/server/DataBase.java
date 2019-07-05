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


	private DataBase() {
		throw new IllegalStateException("Utility class");
	}

}