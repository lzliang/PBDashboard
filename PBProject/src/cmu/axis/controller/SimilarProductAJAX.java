package cmu.axis.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.databean.ProductBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;

public class SimilarProductAJAX extends HttpServlet {
	private Model model;





	//	private RequestDAO requestDAO;
	private ProductDAO productDAO;


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		try {
			model = new Model(getServletConfig());
			productDAO = model.getProductDAO();

			ProductBean productBean = new ProductBean();

			productBean = productDAO.getProduct("test");

			int i=(int) (Math.random()*100);
			String testString = "test " + i;
			
			String[] string = {"hello","hello2"};
			
				req.setAttribute("test2", string);
			
			
			res.setContentType("text/html");
			
				res.getWriter().write("<div class=\"card_style\"> TESTSTRING '${test2[0]}'"+"</div>");
			




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
