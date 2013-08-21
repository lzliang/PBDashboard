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





	//	private RequestDAO requestDAO;
	private RequestDAO requestDAO;
	
	private AmazonProductsDAO amazonDAO;

	private StringBuilder rt = new StringBuilder();
//	private String latestTime = "0";

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		//		String result = new String();

		try {
			model = new Model(getServletConfig());
			requestDAO = model.getRequestDAO();
//			amazonDAO = model.getAmazonProductDAO();
//			
//			AmazonProducts amazonBean = new AmazonProducts();
//			amazonBean.setBarCode("745883596720");
//			amazonBean.setProductDescription("test description");
//			amazonBean.setProductID(123);
//			amazonBean.setProductName("test Name");
//			amazonBean.setReview("test reviews");
//			amazonBean.setSimilarProducts("test similarProducts");
//			
//			amazonDAO.addProduct(amazonBean);
			
//			log.severe("ready to use amazonDAO");
//			
//			if(amazonDAO.doesExist("745883596720")) {
//				log.severe("barcode 745... exists   ");
//				System.out.println("barcode 745... exists   ");
//			}else{
//				log.severe("barcode 745... NOT exists   ");
//				System.out.println("barcode 745...NOT exists   ");
//			}
//			
//			AmazonProducts[] amazonProducts = amazonDAO.getProducts();
//			log.severe("amazon products length" + amazonProducts.length);
//			System.out.println("amazon products length" + amazonProducts.length);
//			if(latestTime.equals("0")) {
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

//			RequestBean[] requestList = requestDAO.getRequests("Done");
			RequestBean[] requestList = requestDAO.getRequests("Need Help");
//				System.out.println("00000    ");
			long currentMillis = System.currentTimeMillis();
			String currentDate = Long.toString(Util.trimTimeStampToDay(currentMillis));
//			
//			
//			RequestBean[] requestList = requestDAO.getRequestsAfterThisPoint(currentDate);
				log.severe("00000    ");
		
				rt = new StringBuilder();
				HttpSession session = req.getSession();
				if(requestList == null || requestList.length ==0) {
//					System.out.println("00000    return");
					log.severe("00000    return");
//					log.severe("00000    listLength "+requestList.length);
					session.setAttribute("latestTime", currentDate);
					return;
				}
//				System.out.println("00000    no return");
				log.severe("00000    no return");
				int length = requestList.length;
//				System.out.println("00000    length" +length);
				log.severe("00000    length "+length);
			    String latestTime = requestList[length-1].getHelpRequestTime();
			    session.setAttribute("latestTime", latestTime);

			    
//			    System.out.println("00000   set session ");
			    log.severe("00000    set session");
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
//			} else {
//				RequestBean[] requestList = requestDAO.getRequestsAfterThisPoint(latestTime);
//				System.out.println("1111    "+latestTime + "length "+requestList.length);
//				setRequests(requestList);
//
//			}
		} catch (Exception e) {
			log.severe("0000    exception ");
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
