package cmu.axis.controller;

import java.awt.List;
import java.text.DecimalFormat;
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
//			if(productMap.containsKey("Price"))
			System.out.println("PRICE    PRICE " +barcode);
			double productPrice = Double.valueOf(productMap.get("Price").substring(1));
			
			DecimalFormat df = new DecimalFormat("#,###,###.##");
            
            String amazonPrice = df.format(productPrice-3);
            String targetPrice = df.format(productPrice+3.5);
            String walmartPrice = df.format(productPrice-2);
			
			
			//for the tab of product details
			String result = ""
					+ "<div class=\"details_pic left\">"
					+ "<p><img height=\"85\" width=\"85\" src=\""+ productMap.get("Picture") +"\"></p>"
					+ "</div>"
					+ "<div style=\"margin:15px 15px; float:left;\">"
					+ " <p>Product Name: "+ productMap.get("Name") +"</p>"
					+ " <p>Price: $"+ productPrice +"</p>"
					+ " <p>Location: 01-E45</p>"
					+ " </div>"
					+ "<div class=\"clear\"></div>"
					+ "<div style=\"margin:15px 15px\"><h4>Price Compare:</h4>"
					+ "<div class=\"price_compare\">Amazon: $"+amazonPrice+"</div>"
					+ "<div class=\"price_compare\">Target: $"+targetPrice+"</div>"
					+ "<div class=\"price_compare\">Walmart: $"+walmartPrice+"</div>"
					+ "</div>"
					+ "<div class=\"clear\"></div>"
					+ "<div style=\"margin:35px 15px\"><h4>Product Description:</h4> "
					+ productMap.get("Description")
					+ "</div>";
			
			
			
			
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
