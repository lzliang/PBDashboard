package cmu.axis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.UserDAO;



/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
	private UserDAO userDAO;

	public LogoutAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	        // Now log him out from Google, too, otherwise, we'll just log him back in on next access
	        return userDAO.getLogoutURL();
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}
}
