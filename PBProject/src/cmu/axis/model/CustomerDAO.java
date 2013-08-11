package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.CustomerBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class CustomerDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("Customer", rootKey)
	    .addSort("customerName", Query.SortDirection.ASCENDING);

    public void addCustomer(CustomerBean customer) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Customer", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("customerName", customer.getCustomerName());
	    e.setProperty("IMEI", customer.getIMEI());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    customer.setCustomerID(e.getKey().getId());
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

    public CustomerBean[] getCustomers() throws DAOException {
	try {
	    List<CustomerBean> customers = runAscendingQuery();
	    return customers.toArray(new CustomerBean[customers.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public CustomerBean getCustomer(String userName) throws DAOException {
	try {
	    CustomerBean aCustomer = new CustomerBean();
	    List<CustomerBean> customers = runAscendingQuery();
	    for (CustomerBean customer : customers) {
		if (customer.getCustomerName().equals(userName))
		    aCustomer = customer;
	    }
	    return aCustomer;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public CustomerBean getCustomer(long customerId) throws DAOException,
	    EntityNotFoundException {
	Key cKey = KeyFactory.createKey(rootKey, "Customer", customerId);
	Entity customer = datastore.get(cKey);
	CustomerBean cBean = makeBean(customer);
	return cBean;
    }

    public void updateCustomer(long cID, CustomerBean aCustomer)
	    throws DAOException, EntityNotFoundException {
	Key cKey = KeyFactory.createKey(rootKey, "Request", cID);
	Entity customer = datastore.get(cKey);
	customer.setProperty("customerName", aCustomer.getCustomerName());
	customer.setProperty("IMEI", aCustomer.getIMEI());
	datastore.put(customer);

    }

    private List<CustomerBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<CustomerBean> customers = new ArrayList<CustomerBean>();
	for (Entity e : entities) {
	    customers.add(makeBean(e));
	}
	return customers;
    }

    private CustomerBean makeBean(Entity e) {
	CustomerBean cbean = new CustomerBean();
	cbean.setCustomerID(e.getKey().getId());
	cbean.setCustomerName((String) e.getProperty("userName"));
	cbean.setIMEI((String) e.getProperty("password"));
	return cbean;
    }

}
