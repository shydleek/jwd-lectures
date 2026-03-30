package com.epam.jwd.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.jwd.model.User;

public class Application{

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	private static final int NUM_USERS = 2;

	private static final String PROG_START = "Program start!";

	private static final String PROG_END = "Program end!";

	public static void main(String[] args){
		LOG.trace(PROG_START);
		User[] users = new User[NUM_USERS];

		users[0] = new User(0L, "Daniil", 23);
		users[1] = new User(1L, "Matvey", 24);

		for (User user : users) {
			LOG.info(user.toString());
		}
		LOG.trace(PROG_END);
	}	
}
