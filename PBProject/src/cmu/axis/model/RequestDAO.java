package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.RequestBean;
import cmu.axis.databean.RequestStats;

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
	    "day", Query.SortDirection.ASCENDING);

    public void addRequest(RequestBean request) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Request", rootKey);
	    e.setProperty("customerID", request.getCustomerID());
	    e.setProperty("employeeID", request.getEmployeeID());
	    e.setProperty("employeeName", request.getEmployeeName());
	    e.setProperty("customerName", request.getCustomerName());
	    e.setProperty("storeID", request.getStoreID());
	    e.setProperty("deviceId", request.getDeviceId());
	    e.setProperty("query", request.getQuery());
	    e.setProperty("barcode", request.getBarcode());
	    e.setProperty("helpRequestTime", request.getHelpRequestTime());
	    e.setProperty("helpReceivedTime", request.getHelpReceivedTime());
	    e.setProperty("day", request.getDay());
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

    public RequestBean[] getRequests(String status, String day)
	    throws DAOException, EntityNotFoundException {
	List<RequestBean> rBeans = new ArrayList<RequestBean>();
	List<RequestBean> reqBeans = runAscendingQuery();
	for (RequestBean reqBean : reqBeans) {
	    if (reqBean.getStatus().equals(status)
		    && reqBean.getDay().equals(day))
		rBeans.add(reqBean);
	}
	return rBeans.toArray(new RequestBean[rBeans.size()]);
    }

    public RequestStats[] getRequestStats() throws DAOException,
	    EntityNotFoundException {
	List<RequestStats> rqStats = new ArrayList<RequestStats>();
	RequestBean[] allRequests = getRequests();
	RequestStats aReqStat = new RequestStats();
	aReqStat.setDay(allRequests[0].getDay());
	aReqStat.setNumberOfServedRequests(numOfServedRequest(aReqStat.getDay()));
	rqStats.add(aReqStat);
	for (int i = 1; i < allRequests.length; i++) {
	    if (!allRequests[i].getDay().equals(aReqStat.getDay())) {
		aReqStat.setDay(allRequests[i].getDay());
		aReqStat.setNumberOfServedRequests(numOfServedRequest(aReqStat
			.getDay()));
		rqStats.add(aReqStat);
	    }
	}
	return rqStats.toArray(new RequestStats[rqStats.size()]);
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

    public RequestBean getRequestbyMachineId(String machineId)
	    throws DAOException, EntityNotFoundException {
	RequestBean[] servingRequests = getRequests("Serving");
	for (int i = 0; i < servingRequests.length; i++) {
	    if (servingRequests[i].getDeviceId() == machineId)
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

    public int numOfServedRequest() throws DAOException,
	    EntityNotFoundException {
	RequestBean[] allRequests = getRequests();
	return allRequests.length;

    }

    public int numOfServedRequest(String day) throws DAOException,
	    EntityNotFoundException {
	RequestBean[] allRequests = getRequests("Done", day);
	return allRequests.length;
    }

    public void updateRequest(long rID, String cFeedback) throws DAOException,
	    EntityNotFoundException {
	Key rKey = KeyFactory.createKey(rootKey, "Request", rID);
	Entity request = datastore.get(rKey);
	request.setProperty("customerFeedback", cFeedback);
	datastore.put(request);

    }

    public void updateRequest(long rID, RequestBean rBean) throws DAOException,
	    EntityNotFoundException {
	Key rKey = KeyFactory.createKey(rootKey, "Request", rID);
	Entity request = datastore.get(rKey);
	request.setProperty("customerID", rBean.getCustomerID());
	request.setProperty("employeeID", rBean.getEmployeeID());
	request.setProperty("employeeName", rBean.getEmployeeName());
	request.setProperty("customerName", rBean.getCustomerName());
	request.setProperty("storeID", rBean.getStoreID());
	request.setProperty("deviceId", rBean.getDeviceId());
	request.setProperty("query", rBean.getQuery());
	request.setProperty("barcode", rBean.getBarcode());
	request.setProperty("helpRequestTime", rBean.getHelpRequestTime());
	request.setProperty("helpReceivedTime", rBean.getHelpReceivedTime());
	request.setProperty("day", rBean.getDay());
	request.setProperty("status", rBean.getStatus());
	request.setProperty("customerFeedback", rBean.getCustomerFeedback());
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
	rbean.setCustomerName((String) e.getProperty("customerName"));
	rbean.setStoreID(Integer.parseInt(e.getProperty("storeID").toString()));
	rbean.setDeviceId((String) (e.getProperty("deviceId")));
	rbean.setQuery((String) (e.getProperty("query")));
	rbean.setBarcode((String) e.getProperty("barcode"));
	rbean.setHelpRequestTime((String) (e.getProperty("helpRequestTime")));
	rbean.setHelpReceivedTime((String) (e.getProperty("helpReceivedTime")));
	rbean.setDay((String) (e.getProperty("day")));
	rbean.setStatus((String) (e.getProperty("status")));
	rbean.setCustomerFeedback((String) (e.getProperty("customerFeedback")));
	return rbean;
    }

}
