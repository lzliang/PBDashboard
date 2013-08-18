package cmu.axis.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.Model;
import cmu.axis.model.RequestDAO;

public class RequestRefreshAJAX extends HttpServlet {

	private Model model;
	private static final Logger log = Logger.getLogger(RequestRefreshAJAX.class.getName());




	//	private RequestDAO requestDAO;
	private RequestDAO requestDAO;

	private StringBuilder rt = new StringBuilder();
	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		//		String result = new String();

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();
			
			HttpSession session = req.getSession();
			String latestTime = (String)session.getAttribute("latestTime");
			
			RequestBean[] requestList = requestDAO.getRequestsAfterThisPoint(latestTime);
			System.out.println("1111    "+latestTime + "length "+requestList.length);
			log.severe("1111    "+latestTime + "length "+requestList.length);

			rt = new StringBuilder();
			if(requestList != null && requestList.length != 0) {
				int length = requestList.length;
				String newlatestTime = requestList[length-1].getHelpRequestTime();
				session.setAttribute("latestTime", newlatestTime);
//				System.out.println("lastest    " + latestTime);
				log.severe("1111    newlatestTime "+newlatestTime);

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
			
			
		} catch (Exception e) {
			log.severe("1111    exception ");
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
