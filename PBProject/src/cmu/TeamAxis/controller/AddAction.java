package cmu.TeamAxis.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cmu.TeamAxis.databean.ItemBean;
import cmu.TeamAxis.formbean.ItemForm;
import cmu.TeamAxis.model.DAOException;
import cmu.TeamAxis.model.ItemDAO;
import cmu.TeamAxis.model.Model;
import cmu.TeamAxis.model.UserDAO;

public class AddAction extends Action {
	private FormBeanFactory<ItemForm>  itemFormFactory  = FormBeanFactory.getInstance(ItemForm.class);
	
	private ItemDAO itemDAO;
	private UserDAO userDAO;

	public AddAction(Model model) {
		itemDAO = model.getItemDAO();
		userDAO = model.getUserDAO();
	}

	public String getName() { return "add.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the item list (and any errors)
       		request.setAttribute("items", itemDAO.getItems());
       		
	        ItemForm form = itemFormFactory.create(request);
        	request.setAttribute("form", form);

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	return "todolist.jsp";
	        }
	        
	        ItemBean item = new ItemBean();
	        item.setItem(form.getItem());
	        item.setUserName(userDAO.getCurrentUser().getEmail());

        	if (form.getAction().equals("Add to Top")) {
        		itemDAO.addToTop(item);
        	} else if (form.getAction().equals("Add to Bottom")) {
        		itemDAO.addToBottom(item);
        	} else {
        		errors.add("Invalid action: " + form.getAction());
        	}

       		// Fetch the items again, since we modified the list
       		request.setAttribute("items", itemDAO.getItems());
       		
       		return "todolist.jsp";

        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
