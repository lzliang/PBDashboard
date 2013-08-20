package cmu.axis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cmu.axis.databean.ProductBean;
import cmu.axis.formbean.AddProductForm;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;

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
		
		String[] string = {"hello","hello2"};
		try {
			ProductBean[] productBeans = productDAO.getProducts();
			request.setAttribute("test2", string);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    System.out.println("XXXX "+string.length);
		
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
	    
	    
//	    String productName = request.getParameter("productName");
//	    System.out.println("productName "+ productName);
	    
	    //Create a productBean
	    double price = Double.valueOf(form.getPrice());
	    ProductBean productBean = new ProductBean(form.getProductName(), form.getProductType(),
	    										form.getProductDescription(), form.getBarCode(),
	    										price);
//	    ProductBean productBean = new ProductBean(form.getProductName(), "from url",
//				"from url", "from url",
//				0);
//	    System.out.println(productDAO.getProducts().length);
	    
	    productDAO.addProduct(productBean);
//	    HttpSession session = request.getSession();
//		System.out.println("name "+form.getUserName()+"  password: "+form.getPassword());
//		session.setAttribute("test","333");
//	    request.setAttribute("test", "3333");
	    
	    
	    
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
