package cmu.axis.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.databean.ProductBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;
import edu.cmu.axis.api.Feedback;

public class SimilarProductAJAX extends HttpServlet {
	private Model model;

	private final static Logger LOGGER = Logger.getLogger(Feedback.class
			.getName());

	// private RequestDAO requestDAO;
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
			Map<String, Map<String, String>> productMap = p
					.getSimilarities(productID);
			Set<String> simprodKeys = productMap.keySet();

			StringBuilder rt = new StringBuilder();

			for (String key : simprodKeys) {
				LOGGER.info("The ASIN for current product is:" + key);
				if (productMap.get(key) != null) {
					rt.append("<div id=\"similar_item\" class=\"similar_item\">");
					rt.append("<div id=\"similar_pic\">");
					rt.append("<img width=\"80\" src=\"");
					rt.append(productMap.get(key).get(
							"Picture") + "\"/>" + "</div>");
					rt.append("<p>");
					LOGGER.info("The current product name is: " + productMap.get(key).get("Name"));
					rt.append(productMap.get(key).get("Name"));
					rt.append("</p>" + "<p>");
					LOGGER.info("The current product name is: " + productMap.get(key).get("Price"));
					rt.append(productMap.get(key).get("Price"));
					rt.append("</p>" + "</div>" + "</div>");	
				}

			}

//			LOGGER.severe("simprodKeys: " + simprodKeys);
//			String result = "";
//			for (int i = 0; i < simprodKeys.size(); i++) {
//				LOGGER.severe("simprodKeys.toArray()[i]: "
//						+ simprodKeys.toArray()[i]);
//
//				LOGGER.severe(" productMap.get(simprodKeys.toArray()[i]): "
//						+ productMap.get(simprodKeys.toArray()[i]));
//				LOGGER.severe("productMap.get(simprodKeys.toArray()[i]).get(\"Picture\"): "
//						+ productMap.get(simprodKeys.toArray()[i]).get(
//								"Picture"));
//				result += "<div id=\"similar_item\" class=\"similar_item\">"
//						+ "<div id=\"similar_pic\">"
//						+ "<img height=\"100\" width=\"77\" src=\""
//						+ productMap.get(simprodKeys.toArray()[i]).get(
//								"Picture") + "\">" + "</div>"
//						+ "<div id=\"similar_text\" >"
//
//						+ "<p>"
//						+ productMap.get(simprodKeys.toArray()[i]).get("Name")
//						+ "</p>" + "<p>"
//						+ productMap.get(simprodKeys.toArray()[i]).get("Price")
//						+ "</p>" + "</div>" + "</div>";
				// if((i+1)%3==0) {
				// result += "<div class=\"clear\"></div>";
				// }
		//	}

			// result += "<div class=\"clear\"></div>";

			res.setContentType("text/html");
			res.getWriter().write(rt.toString());

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
