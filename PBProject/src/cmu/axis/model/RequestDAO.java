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
	    e.setProperty("storeID", request.getStoreID());
	    e.setProperty("aisleNum", request.getAisleNum());
	    e.setProperty("queryType", request.getQueryType());
	    e.setProperty("query", request.getQuery());
	    e.setProperty("productID", request.getProductID());
	    e.setProperty("timeStamp", request.getTimeStamp());
	    e.setProperty("status", request.getStatus());

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
	rbean.setStoreID(Integer.parseInt(e.getProperty("storeID").toString()));
	rbean.setAisleNum(Integer
		.parseInt(e.getProperty("aisleNum").toString()));
	rbean.setQueryType((String) (e.getProperty("queryType")));
	rbean.setQuery((String) (e.getProperty("query")));
	rbean.setProductID((Long) (e.getProperty("productID")));
	rbean.setTimeStamp((String) (e.getProperty("timeStamp")));
	rbean.setStatus((String) (e.getProperty("status")));
	return rbean;
    }

}