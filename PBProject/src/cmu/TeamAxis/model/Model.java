package cmu.TeamAxis.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private ItemDAO itemDAO = new ItemDAO();
	private UserDAO userDAO = new UserDAO();
	private ProductDAO productDAO = new ProductDAO();

	public Model(ServletConfig config) throws ServletException {
	}
	
	public ItemDAO getItemDAO()  { return itemDAO; }
	public UserDAO getUserDAO()  { return userDAO; }
	public ProductDAO getProdutDAO()  { return productDAO; }
}
