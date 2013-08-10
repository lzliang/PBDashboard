package cmu.axis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.EntityNotFoundException;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.RequestDAO;

public class GoHelpAJAX extends HttpServlet {
	private Model model;
	private RequestDAO requestDAO;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();
			
			long requestID = Long.valueOf(req.getParameter("id"));
			RequestBean requestBean = requestDAO.getRequest(requestID);
			String barcode = requestBean.getBarcode();
		
			ProductInfo p = new ProductInfo();
			Map<String, String> productMap = new HashMap<String, String>();
			productMap = p.getProductInfoByBarcode(barcode);
			
			res.setContentType("text/html");
			res.getWriter().write("<div class=\"card_style\" onclick=\"update("+ barcode +")\">"
					+ "<div id=\"request_pic\" class=\"request_pic\">"
					+ "<img height=\"55\" width=\"42\" src=\""+ productMap.get("Picture") +"\">"
						+ "</div>"  
					+ "<div id=\"request_text\" class=\"request_text\">"
					+ "<p>Name: Joe Doe </p>"
					+ "<p>Product Name:"+ productMap.get("Name") +"</p>"
						+ "<p>Location: 01-E45</p>" 
						+ "</div>"
						+ "<div class=\"request_button\">"
						+ "<button style=\"background: #80C65A\" onclick=\"complete(this, "+ requestID +")\">Helping...</button>"
 				    + "</div>"
						+ "<div class=\"clear\"></div>"
						+ "</div>");
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}
	
	
}
