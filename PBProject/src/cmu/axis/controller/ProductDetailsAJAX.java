package cmu.axis.controller;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
import edu.cmu.axis.api.Feedback;

public class ProductDetailsAJAX extends HttpServlet {
//	private Model model;
	private final static Logger LOGGER = Logger.getLogger(Feedback.class
			.getName());

	// private RequestDAO requestDAO;
//	private ProductDAO productDAO;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		try {
//			model = new Model(getServletConfig());
//			productDAO = model.getProductDAO();

//			ProductBean productBean = new ProductBean();
//		
//			productBean = productDAO.getProduct("test");

			String barcode = req.getParameter("id");
			ProductInfo p = new ProductInfo();
			Map<String, String> productMap = new HashMap<String, String>();
			productMap = p.getProductInfoByBarcode(barcode);
//			LOGGER.severe("productMap is: " + productMap);
//
//			LOGGER.severe("amazon price: "
//					+ Double.valueOf(productMap.get("Price").substring(1)));
			double productPrice = Double.valueOf(productMap.get("Price")
					.substring(1));

			DecimalFormat df = new DecimalFormat("#,###,###.##");

			String amazonPrice = df.format(productPrice);
			String targetPrice = df.format(productPrice + priceDiff());
			String walmartPrice = df.format(productPrice + priceDiff());
			String couponPrice = df.format(productPrice*0.9);
			
			StringBuilder rt = new StringBuilder();
			rt.append("<div><div class=\"details_pic left\">");
			rt.append("<p><img height=\"65\" src=\""
					+ productMap.get("Picture") + "\"></p></div>");
			
			rt.append("<div><div class=\"coupon_btn\"> <button onclick=\"getCoupon(this, \'"+barcode+"\')\">Instant Offer</button></div></div>");
			
//			if(barcode.equals("027242842755")){
//				rt.append("<div class=\"coupon_pic\"> <img width=\"55\" src=\"img/coupon.png\" /></div>");
//				rt.append("<div class=\"coupon_text\"><b>$"+couponPrice+"</b></div>");
//				
//			}
			
			rt.append("</div>");
			rt.append("<div style=\"margin:15px 15px; float:left;\">");
			rt.append(" <p><b>Product Name: </b>" + productMap.get("Name") + "</p>");
			rt.append(" <p><b>Price: </b>$" + productPrice + "</p>");
			rt.append("</div>");
			rt.append("<div class=\"clear\"></div>");
			rt.append("<div style=\"margin:15px 15px\"><h4>Price Compare:</h4>");
			rt.append("<div class=\"price_compare\">Amazon: $" + amazonPrice
					+ "</div>");
			rt.append("<div class=\"price_compare\">Target: $" + targetPrice
					+ "</div>");
			rt.append("<div class=\"price_compare\">Walmart: $" + walmartPrice
					+ "</div></div>");
			rt.append("<div class=\"clear\"></div>");
			rt.append("<div style=\"margin:35px 15px\"><h4>Product Description:</h4> ");
			rt.append(productMap.get("Description"));
			rt.append("</div>");

			
			res.setContentType("text/html");
			res.getWriter().write(rt.toString());

//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}  catch (Exception e) {
			res.setContentType("text/html");
			StringBuilder sb = new StringBuilder();
			sb.append("<p style=\"color:red\">");
			sb.append("Product information can not be retrieved at this moment, try again later.");
			sb.append("</p>");
			res.getWriter().write(sb.toString());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

	private double priceDiff() {
		double random = 0;
		random = Math.random() * 6 - 2;
		return random;
	}

}
