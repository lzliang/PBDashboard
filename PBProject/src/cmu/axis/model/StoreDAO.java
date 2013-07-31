package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.StoreBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class StoreDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("Store", rootKey).addSort(
	    "storeID", Query.SortDirection.ASCENDING);

    public void addStore(StoreBean store) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Store", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("longitude", store.getLongitude());
	    e.setProperty("latitude", store.getLatitude());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    store.setStoreID(e.getKey().getId());
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

    public StoreBean[] getStores() throws DAOException {
	try {
	    List<StoreBean> stores = runAscendingQuery();
	    return stores.toArray(new StoreBean[stores.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public StoreBean getStore(long storeID) throws DAOException,
	    EntityNotFoundException {
	Key sKey = KeyFactory.createKey(rootKey, "Store", storeID);
	Entity store = datastore.get(sKey);
	StoreBean sBean = makeBean(store);
	return sBean;
    }

    private List<StoreBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<StoreBean> stores = new ArrayList<StoreBean>();
	for (Entity e : entities) {
	    stores.add(makeBean(e));
	}
	return stores;
    }

    private StoreBean makeBean(Entity e) {
	StoreBean sbean = new StoreBean();
	sbean.setStoreID(e.getKey().getId());
	sbean.setLongitude((String) e.getProperty("longitude"));
	sbean.setLatitude((String) e.getProperty("latitude"));
	return sbean;
    }

}
