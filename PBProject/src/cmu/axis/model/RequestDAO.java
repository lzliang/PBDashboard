package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.RequestBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class RequestDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("Request", rootKey).addSort(
	    "query", Query.SortDirection.ASCENDING);

    public void addRequest(RequestBean request) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Request", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("customerID", request.getCustomerID());
	    e.setProperty("employeeID", request.getEmployeeID());
	    e.setProperty("employeeName", request.getEmployeeName());
	    e.setProperty("storeID", request.getStoreID());
	    // e.setProperty("aisleNum", request.getAisleNum());
	    e.setProperty("queryType", request.getQueryType());
	    e.setProperty("query", request.getQuery());
	    e.setProperty("productID", request.getProductID());
	    e.setProperty("barcode", request.getBarcode());
	    e.setProperty("timeStamp", request.getTimeStamp());
	    e.setProperty("status", request.getStatus());
	    e.setProperty("customerFeedback", request.getCustomerFeedback());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    request.setRequestID(e.getKey().getId());
	    // item.setId(e.getKey().getId()); id stuff, add later

	    t.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	} finally {
	    if (t != null && t.isActive())
		t.rollback();
	}
    }

    public RequestBean[] getRequests() throws DAOException {
	try {
	    List<RequestBean> requests = runAscendingQuery();
	    return requests.toArray(new RequestBean[requests.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public RequestBean[] getRequests(String status) throws DAOException,
	    EntityNotFoundException {
	List<RequestBean> rBeans = new ArrayList<RequestBean>();
	List<RequestBean> reqBeans = runAscendingQuery();
	for (RequestBean reqBean : reqBeans) {
	    if (reqBean.getStatus().equals(status))
		rBeans.add(reqBean);
	}
	return rBeans.toArray(new RequestBean[rBeans.size()]);
    }

    /*
     * public RequestBean getRequest(String request) throws DAOException { try {
     * RequestBean aProduct = new RequestBean(); List<RequestBean> products =
     * runAscendingQuery(); for (RequestBean product : products) { if
     * (product.getProductName().equals(productName)) aProduct = product; }
     * return aProduct; } catch (Exception e) { e.printStackTrace(); throw new
     * DAOException(e); } }
     */

    public RequestBean getRequest(long requestID) throws DAOException,
	    EntityNotFoundException {
	Key rKey = KeyFactory.createKey(rootKey, "Request", requestID);
	Entity request = datastore.get(rKey);
	RequestBean rBean = makeBean(request);
	return rBean;
    }

    public RequestBean getRequest(String empName) throws DAOException,
	    EntityNotFoundException {
	RequestBean[] servingRequests = getRequests("Serving");
	for (int i = 0; i < servingRequests.length; i++) {
	    if (servingRequests[i].getEmployeeName().equals(empName))
		return servingRequests[i];
	}
	return null;
    }

    public RequestBean getRequestbyEmpID(long empID) throws DAOException,
	    EntityNotFoundException {
	RequestBean[] servingRequests = getRequests("Serving");
	for (int i = 0; i < servingRequests.length; i++) {
	    if (servingRequests[i].getEmployeeID() == empID)
		return servingRequests[i];
	}
	return null;
    }

    public boolean isRequestBeingServed(long reqId) throws DAOException,
	    EntityNotFoundException {
	RequestBean rBean = getRequest(reqId);
	if (rBean.getStatus().equals("Serving"))
	    return true;
	return false;
    }

    public void updateRequest(long rID, String cFeedback) throws DAOException,
	    EntityNotFoundException {
	Key rKey = KeyFactory.createKey(rootKey, "Request", rID);
	Entity request = datastore.get(rKey);
	request.setProperty("customerFeedback", cFeedback);
	datastore.put(request);

    }

    private List<RequestBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<RequestBean> requests = new ArrayList<RequestBean>();
	for (Entity e : entities) {
	    requests.add(makeBean(e));
	}
	return requests;
    }

    private RequestBean makeBean(Entity e) {
	RequestBean rbean = new RequestBean();
	rbean.setRequestID(e.getKey().getId());
	rbean.setCustomerID((Long) e.getProperty("customerID"));
	rbean.setEmployeeID((Long) e.getProperty("employeeID"));
	rbean.setEmployeeName((String) e.getProperty("employeeName"));
	rbean.setStoreID(Integer.parseInt(e.getProperty("storeID").toString()));
	rbean.setQuery((String) (e.getProperty("query")));
	rbean.setProductID((Long) (e.getProperty("productID")));
	rbean.setBarcode((String) e.getProperty("barcode"));
	rbean.setTimeStamp((String) (e.getProperty("timeStamp")));
	rbean.setStatus((String) (e.getProperty("status")));
	rbean.setCustomerFeedback((String) (e.getProperty("customerFeedback")));
	return rbean;
    }

}
