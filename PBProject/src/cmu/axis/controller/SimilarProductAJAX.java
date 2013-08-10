package cmu.axis.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.amazonapi.ProductInfo;
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

			String productID = req.getParameter("id");
			ProductInfo p = new ProductInfo();
			Map<String, Map<String, String>> productMap = p.getSimilarities(productID);
			Set<String> similarProducts = productMap.keySet();
			
			
			
			String result = "";
			
			for(int i=0; i<similarProducts.size(); i++) {
				result += "<div id=\"similar_item\" class=\"similar_item\">"
						+ "<div id=\"similar_pic\">"
						+ "<img height=\"100\" width=\"77\" src=\""+ productMap.get(similarProducts.toArray()[i]).get("Picture") +"\">"
						+ "</div>"
						+ "<div id=\"similar_text\" >"
						+ "<p>"+ productMap.get(similarProducts.toArray()[i]).get("Name") +"</p>"
						+ "<p>"+ productMap.get(similarProducts.toArray()[i]).get("Price") +"</p>"
						+ "</div>"
						+ "</div>";
				if((i+1)%3==0) {
					result += "<div class=\"clear\"></div>";
				}
			}
			
			
			res.setContentType("text/html");
			res.getWriter().write(result);
			




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
