package com.caffeine.overflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.caffeine.overflow.server.CaffeineOverflowServiceImpl;

/**
 * Test cases for Caffeine Overflow
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
class TestCases {

	static CaffeineOverflowServiceImpl dummyImplementation;

	/**
	 * Setup
	 */
	@BeforeAll
	public static void setup() {
		dummyImplementation = new CaffeineOverflowServiceImpl();
	}

	@Test
	@DisplayName("Inizializzazione")
	public void testInit() {
		dummyImplementation.init();
	}

	@Test
	@DisplayName("Login")
	public void testLogin() {
		dummyImplementation.init();
		assertEquals(0, dummyImplementation.logIn("test", "test"));
		assertEquals(2, dummyImplementation.logIn("admin@admin.com", "admin"));
	}

}
