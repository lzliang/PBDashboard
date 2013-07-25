package cmu.TeamAxis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.TeamAxis.databean.ProductBean;
import cmu.TeamAxis.model.DAOException;
import cmu.TeamAxis.model.Model;
import cmu.TeamAxis.model.ProductDAO;
import cmu.TeamAxis.model.ProductLocationDAO;
import cmu.TeamAxis.model.RequestDAO;

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

			System.out.println("BARCODE:     "+productBean.getBarCode());
			
			
			res.setContentType("text/html");
			int i=(int) (Math.random()*100);
			if(i%2==0) {
				res.getWriter().write("<div class=\"content\"> TEST "+i+"</div>");
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
