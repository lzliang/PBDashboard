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

	private StringBuilder rt = new StringBuilder();
	private String latestTime = "0";

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		//		String result = new String();

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();
			
			if(latestTime.equals("0")) {
//				RequestBean requestBean = new RequestBean();
//				requestBean.setBarcode("745883596720");
//				requestBean.setEmployeeName("Lynn");
//				requestBean.setStatus("Need Help");
//				requestBean.setQuery("x");
//				requestBean.setDay(String.valueOf(Date.UTC(2013, 8, 16, 3, 28, 30)));
//				requestBean.setHelpRequestTime(String.valueOf(System.currentTimeMillis()));
//				requestBean.setCustomerID(0);
//				requestBean.setStoreID(0);
//				requestBean.setEmployeeID(0);
//				requestBean.setDeviceId("xx");
//				requestDAO.addRequest(requestBean);

				
				RequestBean[] requestList = requestDAO.getRequests("Need Help");
//				System.out.println("00000    " + latestTime);
				setRequests(requestList);
			} else {
				RequestBean[] requestList = requestDAO.getRequestsAfterThisPoint(latestTime);
//				System.out.println("1111    "+latestTime + "length "+requestList.length);
				setRequests(requestList);

			}
		} catch (Exception e) {
			res.setContentType("text/html");
			res.getWriter().write(rt.toString());
		}
		res.setContentType("text/html");
		res.getWriter().write(rt.toString());
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

	private void setRequests(RequestBean[] requestList) {
		rt = new StringBuilder();
		if(requestList == null) {
			return;
		}
		int length = requestList.length;
		latestTime = requestList[length-1].getHelpRequestTime();
//		System.out.println("lastest    " + latestTime);

		ProductInfo p = new ProductInfo();
		Map<String, String> productMap = new HashMap<String, String>();
		String barcode = new String();


		for(RequestBean bean:requestList) {
			barcode = bean.getBarcode();

			productMap = p.getProductInfoByBarcode(barcode);
			if(productMap == null) {
				continue;
			}

			rt.append("<div class=\"card_style\" onclick=\"update(\'"+ barcode +"\')\">");
			rt.append("<div id=\"request_pic\" class=\"request_pic\">");
			rt.append("<img height=\"55\" width=\"42\" src=\""+ productMap.get("Picture") +"\" />");
			rt.append("</div>");
			rt.append("<div id=\"request_text\" class=\"request_text\">");
			rt.append("<p><b>Name: </b>"+bean.getCustomerName()+ "</p>");
			rt.append("<p><b>Product Name: </b>"+ productMap.get("Name") +"</p>");
			rt.append("<p><b>Location: </b>"+ bean.getQuery() +"</p>"); 
			rt.append("</div>");
			rt.append("<div class=\"request_button\">");
			rt.append("<button onclick=\"goHelp(this, \'"+bean.getRequestID()+"\')\">Go Help</button>");
			rt.append("</div>");
			rt.append("<div class=\"clear\"></div>");
			rt.append("</div>");
		}


	}

}
