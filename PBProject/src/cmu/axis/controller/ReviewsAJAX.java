package cmu.axis.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmu.axis.amazonapi.Reviews;

public class ReviewsAJAX extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		
		String result = "<ol>";
		double time = System.currentTimeMillis();
		
		Reviews reviews = new Reviews();
		String barcode = req.getParameter("id");
		int reviewsLength = reviews.getReviews(barcode).size();
		
		for(int i=0; i<reviewsLength; i++) {
			result += "<li>"
					+ "<div class=\"comment\">"
					+ "<div class=\"left\">"
					+ "<img style=\"margin-left:15px\" height=\"50\" width=\"50\" src=\"img/user.png\" />"
					+ "<div class=\"comment-meta\">"
					+ "<p class=\"comment-author\"><span>admin</span></p>"
					+ "<p class=\"comment-date\">Apr 8, 2013</p> "
					+ "</div>"
					+ "</div>"
					+ "<div class=\"comment_right\">"
					+ "<p>Hi,This is really amazing.</p>"
					+ "<p><img src=\"img/1_Star.gif\" width=\"92\" height=\"20\" /> </p>"
					+ "</div>"
					+ "</div>"
					+ "</li>";
					
		}



		result += "</ol>";
        
        
		
		double time2 = System.currentTimeMillis()-time;
		
		System.out.println("TIME :  "+time2);
		
		res.setContentType("text/html");
		
			res.getWriter().write(result);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

	
}
