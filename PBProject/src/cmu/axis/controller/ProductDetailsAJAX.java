package cmu.axis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.amazonapi.ProductInfo;
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

			String productID = req.getParameter("id");
			ProductInfo p = new ProductInfo();
			Map<String, String> productMap = new HashMap<String, String>();
			productMap = p.getProductInfoByBarcode(productID);
			
			
			res.setContentType("text/html");
			
				res.getWriter().write("<div class=\"details_pic left\">"
						+ "<img height=\"85\" width=\"65\" src=\""+ productMap.get("Picture") +"\">"
						+ "</div>"
						+ "<div style=\"margin:15px 15px; float:left;\">"
						+ " <p>Product Name: "+ productMap.get("Name") +"</p>"
						+ " <p>Price: "+ productMap.get("Price") +"</p>"
						+ " <p>Location: 01-E45</p>"
						+ " </div>"
						+ "</div>"
						+ "<div class=\"clear\"></div>"
						+ "<div style=\"margin:15px 15px\"><h4>Price Compare:</h4>"
						+ "<div class=\"price_compare\">Amazon: $1.45</div>"
						+ "<div class=\"price_compare\">Target: $1.49</div>"
						+ "<div class=\"price_compare\">Walmart: $1.45</div>"
						+ "</div>"
						+ "<div class=\"clear\"></div>"
						+ "<div style=\"margin:35px 15px\"><h4>Product Description:</h4> "
						+ productMap.get("Description")
						+ "</div>");
			




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
