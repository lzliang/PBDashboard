package cmu.axis.controller;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.amazonapi.Reviews;
import cmu.axis.databean.ProductBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;

public class ProductDetailsAJAX extends HttpServlet {
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

			String barcode = req.getParameter("id");
			ProductInfo p = new ProductInfo();
			Map<String, String> productMap = new HashMap<String, String>();
			productMap = p.getProductInfoByBarcode(barcode);
			
			//for the tab of product details
			String result = ""
//					+"<div id=\"productDetails\">"
					+ "<div class=\"details_pic left\">"
					+ "<img height=\"85\" width=\"65\" src=\""+ productMap.get("Picture") +"\">"
					+ "</div>"
					+ "<div style=\"margin:15px 15px; float:left;\">"
					+ " <p>Product Name: "+ productMap.get("Name") +"</p>"
					+ " <p>Price: "+ productMap.get("Price") +"</p>"
					+ " <p>Location: 01-E45</p>"
					+ " </div>"
					+ "<div class=\"clear\"></div>"
					+ "<div style=\"margin:15px 15px\"><h4>Price Compare:</h4>"
					+ "<div class=\"price_compare\">Amazon: $1.45</div>"
					+ "<div class=\"price_compare\">Target: $1.49</div>"
					+ "<div class=\"price_compare\">Walmart: $1.45</div>"
					+ "</div>"
					+ "<div class=\"clear\"></div>"
					+ "<div style=\"margin:35px 15px\"><h4>Product Description:</h4> "
					+ productMap.get("Description")
//					+ "</div>"
					+ "</div>";
			
			
			//for the tab of reviews
//			result += "<div>"
//					+ "<h3>Most Useful Customer Reviews:</h3>"
//					+ "<div id=\"comment_list\" class=\"comment_list scroll_panel\">"
//					+ "<ol>";
//			
//			double time = System.currentTimeMillis();
//			
////			Reviews reviews = new Reviews();
////			reviews.getReviews(barcode);
//			
//			for(int i=0; i<10; i++) {
//				result += "<li>"
//						+ "<div class=\"comment\">"
//						+ "<div class=\"left\">"
//						+ "<img style=\"margin-left:15px\" height=\"50\" width=\"50\" src=\"img/user.png\" />"
//						+ "<div class=\"comment-meta\">"
//						+ "<p class=\"comment-author\"><span>admin</span></p>"
//						+ "<p class=\"comment-date\">Apr 8, 2013</p> "
//						+ "</div>"
//						+ "</div>"
//						+ "<div class=\"comment_right\">"
//						+ "<p>Hi,This is really amazing.</p>"
//						+ "<p><img src=\"img/1_Star.gif\" width=\"92\" height=\"20\" /> </p>"
//						+ "</div>"
//						+ "</div>"
//						+ "</li>";
//						
//			}
//    
//    
//    
//			result += "</ol>"
//					+ "</div>"
//					+ "</div>"
//					+ "<ul>"
//					+ "<li><a href=\"javascript:vd()\">Details</a></li>"
//					+ "<li><a href=\"javascript:vd()\">Reviews</a></li>"
//					+ "</ul>";
//            
//            
//			
//			double time2 = System.currentTimeMillis()-time;
//			
//			System.out.println("TIME :  "+time2);
			
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
