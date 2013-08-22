package cmu.axis.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.databean.AmazonProducts;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.AmazonProductsDAO;
import cmu.axis.model.Model;
import cmu.axis.model.RequestDAO;
import edu.cmu.axis.api.Util;

@SuppressWarnings("serial")
public class HelpRequestAction extends HttpServlet {

	private Model model;
	private static final Logger log = Logger.getLogger(HelpRequestAction.class.getName());

	private RequestDAO requestDAO;

	private StringBuilder rt = new StringBuilder();

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();
		
//				RequestBean requestBean = new RequestBean();
//				int j = (int)(Math.random()*10);
//				if (j%3==0){
//					requestBean.setBarcode("745883596720");
//				}
//				if (j%3==1){
//					requestBean.setBarcode("700621033516");
//				}
//				if (j%3==2){
//					requestBean.setBarcode("060258352504");
//				}
//				
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
//			RequestBean[] serving = requestDAO.

			long currentMillis = System.currentTimeMillis();
			String currentDate = Long.toString(Util.trimTimeStampToDay(currentMillis));

		
				rt = new StringBuilder();
//				HttpSession session = req.getSession();
				if(requestList == null || requestList.length ==0) {
//					session.setAttribute("latestTime", currentDate);
					res.setContentType("text/html");
					res.getWriter().write("");
					return;
				}

				int length = requestList.length;

//			    String latestTime = requestList[length-1].getHelpRequestTime();
//			    session.setAttribute("latestTime", latestTime);


				ProductInfo p = new ProductInfo();
				Map<String, String> productMap = new HashMap<String, String>();
				String barcode = new String();

				long current = System.currentTimeMillis();
				DateFormat dfm = new SimpleDateFormat("mm");
				dfm.setTimeZone(TimeZone.getTimeZone("GMT-4"));
				DateFormat dfs = new SimpleDateFormat("ss");
				dfs.setTimeZone(TimeZone.getTimeZone("GMT-4"));
				
				for(RequestBean bean:requestList) {
					barcode = bean.getBarcode();

					productMap = p.getProductInfoByBarcode(barcode);
					if(productMap == null) {
						continue;
					}

					rt.append("<div class=\"card_style\" onclick=\"update(\'"+ barcode +"\')\">");
					rt.append("<div id=\"request_pic\" class=\"request_pic\">");
					rt.append("<img width=\"50\" src=\""+ productMap.get("Picture") +"\" />");
					rt.append("</div>");
					rt.append("<div id=\"request_text\" class=\"request_text\">");
					rt.append("<p><b>Name: </b>"+bean.getCustomerName()+ "</p>");
					rt.append("<p><b>Product Name: </b>"+ productMap.get("Name") +"</p>");
					rt.append("<p><b>Location: </b>"+ bean.getQuery() +"</p>"); 
					rt.append("</div>");
					rt.append("<div class=\"request_button\">");
					rt.append("<button onclick=\"goHelp(this, \'"+bean.getRequestID()+"\')\">Go Help</button>");
					
					long time = current-Long.valueOf(bean.getHelpRequestTime());
					if(time>=60000) {
						rt.append("<p class=\"waiting_text\">"+ dfm.format(new Date(time))+ " mins ago</p>");
					} else {
						rt.append("<p class=\"waiting_text\">"+ dfs.format(new Date(time))+ " secs ago</p>");
						
					}
					
					rt.append("</div>");
					rt.append("<div class=\"clear\"></div>");
					rt.append("</div>");
				}		
		} catch (Exception e) {
			e.printStackTrace();
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


}
