package cmu.TeamAxis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cmu.TeamAxis.model.Model;
import cmu.TeamAxis.model.ProductDAO;
import cmu.TeamAxis.model.ProductLocationDAO;
import cmu.TeamAxis.model.RequestDAO;

public class HelpRequestAction extends Action {

	private RequestDAO requestDAO;
	private ProductDAO productDAO;
	private ProductLocationDAO productLocationDAO;
	
	public HelpRequestAction(Model model) {
		requestDAO = model.getRequestDAO();
		productDAO = model.getProductDAO();
		productLocationDAO = model.getProductLocationDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "helpRequest.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		
		return "helpRequest.jsp";
	}

}
