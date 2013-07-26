package cmu.axis.model;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserDAO {
	private UserService userService = UserServiceFactory.getUserService();

	public User getCurrentUser() {
		// userService.getCurrentUser() doesn't throw any exceptions (so the docs say)
		return userService.getCurrentUser();
	}
	
	public String getLoginURL() throws DAOException {
		try {
			// Just redirect back to the top of the website ("/")
			return userService.createLoginURL("/");
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public String getLogoutURL() throws DAOException {
		try {
			// Just redirect back to the top of the website ("/")
			return userService.createLogoutURL("/");
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
