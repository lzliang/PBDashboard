package cmu.axis.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cmu.axis.databean.ItemBean;
import cmu.axis.formbean.ItemForm;
import cmu.axis.model.DAOException;
import cmu.axis.model.ItemDAO;
import cmu.axis.model.Model;
import cmu.axis.model.UserDAO;

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
