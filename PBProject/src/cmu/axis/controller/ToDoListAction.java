package cmu.axis.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cmu.axis.model.DAOException;
import cmu.axis.model.ItemDAO;
import cmu.axis.model.Model;

public class ToDoListAction extends Action {
	private ItemDAO itemDAO;

	public ToDoListAction(Model model) {
		itemDAO = model.getItemDAO();
	}

	public String getName() { return "todolist.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		request.setAttribute("items", itemDAO.getItems());
       		return ("todolist.jsp");
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
