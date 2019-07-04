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

	private static DB getAnswersDb() {
		return DBMaker.newFileDB(new File(ANSWERS)).make();
	}

	private static DB getCategoriesDb() {
		return DBMaker.newFileDB(new File(CATEGORIES)).make();
	}

	private static DB getQuestionsDb() {
		return DBMaker.newFileDB(new File(QUESTIONS)).make();
	}

	private static DB getUsersDb() {
		return DBMaker.newFileDB(new File(USERS)).make();

	}

	private DataBase() {
		throw new IllegalStateException("Utility class");
	}

}