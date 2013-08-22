package cmu.axis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.RequestDAO;

import com.google.appengine.api.datastore.EntityNotFoundException;

public class GetHelpingAJAX extends HttpServlet {
	private Model model;
	private RequestDAO requestDAO;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();

			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			
			RequestBean requestBean = requestDAO.getRequest(userName);
			
			if(requestBean==null) {
				res.setContentType("text/html");
				res.getWriter().write("");
				return;
			}
			
			String barcode = requestBean.getBarcode();

		
			ProductInfo p = new ProductInfo();
			Map<String, String> productMap = new HashMap<String, String>();
			productMap = p.getProductInfoByBarcode(barcode);
			
			if(productMap == null || productMap.size()==0) {
				res.setContentType("text/html");
				res.getWriter().write("");
				return;
			}
			
			res.setContentType("text/html");
			res.getWriter().write("<div class=\"card_style\" onclick=\"update(\'"+ barcode +"\')\">"
					+ "<div id=\"request_pic\" class=\"request_pic\">"
					+ "<img width=\"50\" src=\""+ productMap.get("Picture") +"\">"
						+ "</div>"  
					+ "<div id=\"request_text\" class=\"request_text\">"
					+ "<p><b>Name: </b>"+requestBean.getCustomerName()+ "</p>"
					+ "<p><b>Product Name: </b>"+ productMap.get("Name") +"</p>"
						+ "<p><b>Location: </b>"+ requestBean.getQuery()+"</p>" 
						+ "</div>"
						+ "<div class=\"request_button\">"
						+ "<button style=\"background: #80C65A\" onclick=\"complete(\'"+ requestBean.getRequestID() +"\')\">Complete</button>"
						+ "<p class=\"helping_text\">In progress...</p>"
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
