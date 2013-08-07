package cmu.axis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.databean.ProductBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;
import cmu.axis.model.ProductLocationDAO;
import cmu.axis.model.RequestDAO;

@SuppressWarnings("serial")
public class HelpRequestAction extends HttpServlet {

	private Model model;





	//	private RequestDAO requestDAO;
	private ProductDAO productDAO;
	//	private ProductLocationDAO productLocationDAO;
	//	
	//	public HelpRequestAction(Model model) {
	//		requestDAO = model.getRequestDAO();
	//		productDAO = model.getProductDAO();
	//		productLocationDAO = model.getProductLocationDAO();
	//	}

	//	public String getName() {
	//		// TODO Auto-generated method stub
	//		return "helpRequestAction.do";
	//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		try {
			model = new Model(getServletConfig());
			productDAO = model.getProductDAO();

			ProductBean productBean = new ProductBean();

			productBean = productDAO.getProduct("test");

			int i=(int) (Math.random()*100);

		
			String testString = req.getParameter("id");
			System.out.println("id:     "+testString);
			
			
			res.setContentType("text/html");
			if(i%2==0) {
				res.getWriter().write("<div class=\"card_style\"> TEST "+i+"</div>");
			}




		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

}
