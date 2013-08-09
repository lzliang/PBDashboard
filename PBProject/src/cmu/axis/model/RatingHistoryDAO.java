package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.RatingHistoryBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class RatingHistoryDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("RatingHistory", rootKey)
	    .addSort("timeStamp", Query.SortDirection.ASCENDING);

    public boolean saveRatingHistory(RatingHistoryBean ratingHistory)
	    throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("RatingHistory", rootKey);
	    e.setProperty("customerId", ratingHistory.getCustomerId());
	    e.setProperty("employeeId", ratingHistory.getEmployeeId());
	    e.setProperty("rating", ratingHistory.getRating());
	    e.setProperty("timeStamp", ratingHistory.getTimeStamp());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    ratingHistory.setRhid(e.getKey().getId());
	    // item.setId(e.getKey().getId()); id stuff, add later

	    t.commit();
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	} finally {
	    if (t != null && t.isActive())
		t.rollback();
	}
    }

    public RatingHistoryBean[] getRatingHistories() throws DAOException {
	try {
	    List<RatingHistoryBean> ratingHistories = runAscendingQuery();
	    return ratingHistories
		    .toArray(new RatingHistoryBean[ratingHistories.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public RatingHistoryBean[] getRatingHistories(long employeeId)
	    throws DAOException, EntityNotFoundException {
	List<RatingHistoryBean> rhBeans = new ArrayList<RatingHistoryBean>();
	List<RatingHistoryBean> rthBeans = runAscendingQuery();
	for (RatingHistoryBean rthBean : rthBeans) {
	    if (rthBean.getEmployeeId() == employeeId)
		rhBeans.add(rthBean);
	}
	return rhBeans.toArray(new RatingHistoryBean[rhBeans.size()]);

    }

    /*
     * public CustomerBean getCustomer(String userName) throws DAOException {
     * try { CustomerBean aCustomer = new CustomerBean(); List<CustomerBean>
     * customers = runAscendingQuery(); for (CustomerBean customer : customers)
     * { if (customer.getCustomerName().equals(userName)) aCustomer = customer;
     * } return aCustomer; } catch (Exception e) { e.printStackTrace(); throw
     * new DAOException(e); } }
     */

    /*
     * public CustomerBean getCustomer(long customerId) throws DAOException,
     * EntityNotFoundException { Key cKey = KeyFactory.createKey(rootKey,
     * "Customer", customerId); Entity customer = datastore.get(cKey);
     * CustomerBean cBean = makeBean(customer); return cBean; }
     */

    private List<RatingHistoryBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<RatingHistoryBean> ratingHistories = new ArrayList<RatingHistoryBean>();
	for (Entity e : entities) {
	    ratingHistories.add(makeBean(e));
	}
	return ratingHistories;
    }

    private RatingHistoryBean makeBean(Entity e) {
	RatingHistoryBean rhbean = new RatingHistoryBean();
	rhbean.setRhid(e.getKey().getId());
	rhbean.setCustomerId((Long) e.getProperty("customerId"));
	rhbean.setEmployeeId((Long) e.getProperty("employeeId"));
	rhbean.setRating(Double.parseDouble(e.getProperty("rating").toString()));
	// rhbean.setTimeStamp(Date.parse(e.getProperty("timeStamp").toString()));
	return rhbean;
    }

}
