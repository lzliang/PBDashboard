package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.axis.databean.OtherPricesBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class OtherPricesDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("OtherPrices", rootKey)
	    .addSort("productID", Query.SortDirection.ASCENDING);

    public void addOtherPrices(OtherPricesBean otherPrices) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Product", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("productID", otherPrices.getProductID());
	    e.setProperty("retailerName", otherPrices.getRetailerName());
	    e.setProperty("price", otherPrices.getPrice());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    otherPrices.setOpID(e.getKey().getId());
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

    public OtherPricesBean[] getOtherPrices() throws DAOException {
	try {
	    List<OtherPricesBean> otherPrices = runAscendingQuery();
	    return otherPrices.toArray(new OtherPricesBean[otherPrices.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    /*
     * public ProductBean getProduct(String productName) throws DAOException {
     * try { ProductBean aProduct = new ProductBean(); List<ProductBean>
     * products = runAscendingQuery(); for (ProductBean product : products) { if
     * (product.getProductName().equals(productName)) aProduct = product; }
     * return aProduct; } catch (Exception e) { e.printStackTrace(); throw new
     * DAOException(e); } }
     */

    public OtherPricesBean getOtherPrice(long opID) throws DAOException,
	    EntityNotFoundException {
	Key opKey = KeyFactory.createKey(rootKey, "OtherPrices", opID);
	Entity otherPrices = datastore.get(opKey);
	OtherPricesBean opBean = makeBean(otherPrices);
	return opBean;
    }

    private List<OtherPricesBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<OtherPricesBean> otherPrices = new ArrayList<OtherPricesBean>();
	for (Entity e : entities) {
	    otherPrices.add(makeBean(e));
	}
	return otherPrices;
    }

    private OtherPricesBean makeBean(Entity e) {
	OtherPricesBean opbean = new OtherPricesBean();
	opbean.setOpID(e.getKey().getId());
	opbean.setProductID((Long) e.getProperty("productID"));
	opbean.setRetailerName((String) e.getProperty("retailerName"));
	opbean.setPrice((Double) (e.getProperty("price")));
	return opbean;
    }

}
