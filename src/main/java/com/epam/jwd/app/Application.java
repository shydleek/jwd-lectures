package com.epam.jwd.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.jwd.model.User;

public class Application{

	private static final Logger LOG = LogManager.getLogger(Application.class);

	public static void main(String[] args){
		LOG.trace("Program start!");
		User[] users = new User[2];

		users[0] = new User((long) 0, "Daniil", 23);
		users[1] = new User((long) 1, "Matvey", 24);

		for (User user : users) {
			LOG.info(user.toString());
		}
		LOG.trace("Program end!");
	}	
}
