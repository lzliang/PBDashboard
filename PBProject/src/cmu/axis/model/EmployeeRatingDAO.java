package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.EmployeeRatingBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class EmployeeRatingDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("EmployeeRating", rootKey)
	    .addSort("employeeName", Query.SortDirection.ASCENDING);

    public void addEmployeeRating(EmployeeRatingBean employeeRating)
	    throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("EmployeeRating", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("employeeName", employeeRating.getEmployeeName());
	    e.setProperty("numOfRatings", employeeRating.getNumOfRatings());
	    e.setProperty("averageRating", employeeRating.getAverageRating());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    employeeRating.setEmployeeId(e.getKey().getId());
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

    public EmployeeRatingBean[] getEmployeeRatings() throws DAOException {
	try {
	    List<EmployeeRatingBean> employeeRatings = runAscendingQuery();
	    return employeeRatings
		    .toArray(new EmployeeRatingBean[employeeRatings.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public EmployeeRatingBean getEmployeeRating(String employeeName)
	    throws DAOException {
	try {
	    EmployeeRatingBean aRating = new EmployeeRatingBean();
	    List<EmployeeRatingBean> emRatings = runAscendingQuery();
	    for (EmployeeRatingBean emRating : emRatings) {
		if (emRating.getEmployeeName().equals(employeeName))
		    aRating = emRating;
	    }
	    return aRating;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    /*
     * public CustomerBean getCustomer(long customerId) throws DAOException,
     * EntityNotFoundException { Key cKey = KeyFactory.createKey(rootKey,
     * "Customer", customerId); Entity customer = datastore.get(cKey);
     * CustomerBean cBean = makeBean(customer); return cBean; }
     */

    private List<EmployeeRatingBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<EmployeeRatingBean> eRatings = new ArrayList<EmployeeRatingBean>();
	for (Entity e : entities) {
	    eRatings.add(makeBean(e));
	}
	return eRatings;
    }

    private EmployeeRatingBean makeBean(Entity e) {
	EmployeeRatingBean erbean = new EmployeeRatingBean();
	erbean.setEmployeeId(e.getKey().getId());
	erbean.setEmployeeName((String) e.getProperty("employeeName"));
	erbean.setNumOfRatings(Integer.parseInt(e.getProperty("numOfRatings")
		.toString()));
	erbean.setAverageRating(Double.parseDouble(e.getProperty(
		"averageRating").toString()));
	return erbean;
    }

}
