package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.EmployeeBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class EmployeeDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("Employee", rootKey)
	    .addSort("userName", Query.SortDirection.ASCENDING);

    public void addEmployee(EmployeeBean employee) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Employee", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("userName", employee.getUserName());
	    e.setProperty("password", employee.getPassword());
	    e.setProperty("designation", employee.getDesignation());
	    e.setProperty("picURL", employee.getPicURL());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    employee.setEmployeeID(e.getKey().getId());
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

    public EmployeeBean[] getEmployees() throws DAOException {
	try {
	    List<EmployeeBean> employees = runAscendingQuery();
	    return employees.toArray(new EmployeeBean[employees.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public EmployeeBean getEmployee(String userName) throws DAOException {
	try {
	    EmployeeBean anEmployee = new EmployeeBean();
	    List<EmployeeBean> employees = runAscendingQuery();
	    for (EmployeeBean employee : employees) {
		if (employee.getUserName().equals(userName))
		    anEmployee = employee;
	    }
	    return anEmployee;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public EmployeeBean getEmployee(long employeeID) throws DAOException,
	    EntityNotFoundException {
	Key eKey = KeyFactory.createKey(rootKey, "Employee", employeeID);
	Entity employee = datastore.get(eKey);
	EmployeeBean eBean = makeBean(employee);
	return eBean;
    }

    private List<EmployeeBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
	for (Entity e : entities) {
	    employees.add(makeBean(e));
	}
	return employees;
    }

    private EmployeeBean makeBean(Entity e) {
	EmployeeBean ebean = new EmployeeBean();
	ebean.setEmployeeID(e.getKey().getId());
	ebean.setUserName((String) e.getProperty("userName"));
	ebean.setPassword((String) e.getProperty("password"));
	ebean.setDesignation((String) e.getProperty("designation"));
	ebean.setPicURL((String) e.getProperty("picURL"));
	return ebean;
    }

}
