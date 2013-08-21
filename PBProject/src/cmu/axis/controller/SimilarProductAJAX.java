package cmu.axis.controller;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.model.Model;
import cmu.axis.model.ProductDAO;
import edu.cmu.axis.api.Feedback;

public class SimilarProductAJAX extends HttpServlet {
//	private Model model;
//
//	private final static Logger LOGGER = Logger.getLogger(Feedback.class
//			.getName());

	// private RequestDAO requestDAO;
//	private ProductDAO productDAO;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

//		try {
//			model = new Model(getServletConfig());
//			productDAO = model.getProductDAO();

			String productID = req.getParameter("id");
			ProductInfo p = new ProductInfo();
			Map<String, Map<String, String>> productMap = p
					.getSimilarities(productID);
			Set<String> simprodKeys = productMap.keySet();

			StringBuilder rt = new StringBuilder();

			for (String key : simprodKeys) {
				if (productMap.get(key) != null) {
					rt.append("<div id=\"similar_item\" class=\"similar_item\">");
					rt.append("<div id=\"similar_pic\" style=\"text-align:center\">");
					rt.append("<img width=\"80\" src=\"");
					rt.append(productMap.get(key).get(
							"Picture") + "\"/>" + "</div>");
					rt.append("<p>");
					rt.append(productMap.get(key).get("Name"));
					rt.append("</p>" + "<p>");
					rt.append(productMap.get(key).get("Price"));
					rt.append("</p>" + "</div>" + "</div>");	
				}

			}


			res.setContentType("text/html");
			res.getWriter().write(rt.toString());

//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

}
