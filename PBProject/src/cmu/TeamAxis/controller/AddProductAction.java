package cmu.TeamAxis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cmu.TeamAxis.databean.ProductBean;
import cmu.TeamAxis.formbean.AddProductForm;
import cmu.TeamAxis.model.DAOException;
import cmu.TeamAxis.model.Model;
import cmu.TeamAxis.model.ProductDAO;

public class AddProductAction extends Action {
	private FormBeanFactory<AddProductForm> formBeanFactory = FormBeanFactory.getInstance(AddProductForm.class);
	private ProductDAO productDAO;
	
	public AddProductAction(Model model) {
		productDAO = model.getProductDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "addProduct.do";
	}

	
	
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
	    
	    //Create a productBean
	    double price = Double.valueOf(form.getPrice());
	    ProductBean productBean = new ProductBean(form.getProductName(), form.getProductType(),
	    										form.getProductDescription(), form.getBarCode(),
	    										price);
	    
	    
	    productDAO.addProduct(productBean);
	    
		return "addProduct.jsp";
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
	}

}
