package cmu.axis.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
    private final ItemDAO itemDAO = new ItemDAO();
    private final UserDAO userDAO = new UserDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final OtherPricesDAO otherPricesDAO = new OtherPricesDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final ProductLocationDAO productLocationDAO = new ProductLocationDAO();
    private final RequestDAO requestDAO = new RequestDAO();
    private final RatingHistoryDAO ratingHistoryDAO = new RatingHistoryDAO();
    private final EmployeeRatingDAO employeeRatingDAO = new EmployeeRatingDAO();
    private final StoreDAO storeDAO = new StoreDAO();

    public Model(ServletConfig config) throws ServletException {
    }

    public ItemDAO getItemDAO() {
	return itemDAO;
    }

    public UserDAO getUserDAO() {
	return userDAO;
    }

    public CustomerDAO getCustomerDAO() {
	return customerDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
	return employeeDAO;
    }

    public OtherPricesDAO getOtherPricesDAO() {
	return otherPricesDAO;
    }

    public ProductLocationDAO getProductLocationDAO() {
	return productLocationDAO;
    }

    public ProductDAO getProductDAO() {
	return productDAO;
    }

    public RequestDAO getRequestDAO() {
	return requestDAO;
    }

    public RatingHistoryDAO getRatingHistoryDAO() {
	return ratingHistoryDAO;
    }

    public EmployeeRatingDAO getEmployeeRatingDAO() {
	return employeeRatingDAO;
    }

    public StoreDAO getStoreDAO() {
	return storeDAO;
    }
}
