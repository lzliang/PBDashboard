package cmu.TeamAxis.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;

import cmu.TeamAxis.model.DAOException;
import cmu.TeamAxis.model.Model;
import cmu.TeamAxis.model.UserDAO;

public class LoginAction extends Action {
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	User user = userDAO.getCurrentUser();
        	
        	if (user == null) {
        		// Redirect to Google's login page
        		return userDAO.getLoginURL();
        	}
	        
	        // Redirect to the "todolist" action
			return "todolist.do";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
