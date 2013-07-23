package cmu.TeamAxis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cmu.TeamAxis.formbean.AddProductForm;
import cmu.TeamAxis.model.Model;

public class AddProductAction extends Action {

	public AddProductAction(Model model) {
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "addProduct.do";
	}

	
	 private FormBeanFactory<AddProductForm> formBeanFactory = FormBeanFactory.getInstance(AddProductForm.class);
	
	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		
		AddProductForm form;
		try {
			form = formBeanFactory.create(request);
		request.setAttribute("form", form);
		
	    if(!form.isPresent()) {
	    	return "addProduct.jsp";
	    }
	    
	    errors.addAll(form.getValidationErrors());
	    if(errors.size()!=0) {
	    	return "addProduct.jsp";
	    }
	    
	    //DAO stuff
	    //form.getDescription();
	    
		
		return "addProduct.jsp";
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
	}

}
