package cmu.axis.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;

import cmu.axis.model.Model;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	Model model;

	public void init() throws ServletException {
        model = new Model(getServletConfig());

        Action.add(new AddAction(model));
        Action.add(new DeleteAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new ToDoListAction(model));
        Action.add(new AddProductAction(model));
        Action.add(new DashboardLoginAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        String      servletPath = request.getServletPath();
        String      action      = getActionName(servletPath);
        User        user        = model.getUserDAO().getCurrentUser();
        
        HttpSession session     = request.getSession(true);
        String userName = (String)session.getAttribute("userName");
        String password = (String)session.getAttribute("password");
        
        if (user == null) {
        	// If the user hasn't logged in, so login is the only option
			return Action.perform("login.do",request);
        }
        
        if (action.equals("start")) {
            // If he's logged in but back at the /start page, send him to manage his pics
            if (userName != null && userName.equals("manager") && password !=null && password.equals("axis")) {
            	System.out.println(1);
                    return "helpRequest.jsp";          
            }
            if (userName != null && userName.equals("employee")&& password !=null && password.equals("axis")) {
            	System.out.println(2);   
            	return "helpRequest.jsp"; 
            }               
            System.out.println(3);       
            return Action.perform("dashboardLogin.do",request);
    }
        
      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
    }
    
    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	// If it doesn't end in .jsp extension, then redirect to the page provided
		response.sendRedirect(nextPage);
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
