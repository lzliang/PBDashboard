package cmu.axis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.EntityNotFoundException;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.databean.ProductBean;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;
import cmu.axis.model.ProductLocationDAO;
import cmu.axis.model.RequestDAO;

@SuppressWarnings("serial")
public class HelpRequestAction extends HttpServlet {

	private Model model;





	//	private RequestDAO requestDAO;
	private RequestDAO requestDAO;
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
		String result = new String();

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();
			
			
			
//			RequestBean requestBean = new RequestBean();
//			requestBean.setBarcode("018208254965");
//			requestBean.setEmployeeName("Lynn");
//			requestBean.setStatus("Need Help");
//			requestBean.setQuery("x");
//			requestBean.setDay("Sat Aug 12 23:56:10 EDT 2013");
//			requestBean.setCustomerID(0);
//			requestBean.setStoreID(0);
//			requestBean.setEmployeeID(0);
////			
//			requestDAO.addRequest(requestBean);
//			Date date = new Date();
//			RequestBean[] test = requestDAO.getRequests();
//			System.out.println("test  " + test.length);

			RequestBean[] requestList = requestDAO.getRequests("Need Help");
			
			for(RequestBean bean:requestList) {
				System.out.println("REQUEST ID:   " + bean.getRequestID());
				
			}

			ProductInfo p = new ProductInfo();
			Map<String, String> productMap = new HashMap<String, String>();
			String barcode = new String();
			
			
			for(int i=0; i<requestList.length; i++) {
				barcode = requestList[i].getBarcode();

				productMap = p.getProductInfoByBarcode(barcode);
				if(productMap == null) {
					continue;
				}
				
				result += "<div class=\"card_style\" onclick=\"update(\'"+ barcode +"\')\">"
						+ "<div id=\"request_pic\" class=\"request_pic\">"
						+ "<img height=\"55\" width=\"42\" src=\""+ productMap.get("Picture") +"\">"
   						+ "</div>"  
						+ "<div id=\"request_text\" class=\"request_text\">"
						+ "<p>Name: Joe Doe </p>"
						+ "<p>Product Name:"+ productMap.get("Name") +"</p>"
   						+ "<p>Location: 01-E45</p>" 
   						+ "</div>"
   						+ "<div class=\"request_button\">"
   						+ "<button onclick=\"goHelp(this, \'"+requestList[i].getRequestID()+"\')\">Go Help</button>"
     				    + "</div>"
   						+ "<div class=\"clear\"></div>"
   						+ "</div>";
       			
                 
			}
			
			res.setContentType("text/html");
			res.getWriter().write(result);
			




		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			res.setContentType("text/html");
			res.getWriter().write(result);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

}
