package cmu.TeamAxis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.TeamAxis.databean.ProductLocationBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class ProductLocationDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("ProductLocation", rootKey)
	    .addSort("productID", Query.SortDirection.ASCENDING);

    public void addProductLocation(ProductLocationBean productLocation)
	    throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Product", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("productID", productLocation.getProductID());
	    e.setProperty("storeID", productLocation.getStoreID());
	    e.setProperty("aisleNum", productLocation.getAisleNum());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    productLocation.setProdlocID(e.getKey().getId());
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

    public ProductLocationBean[] getProductLocations() throws DAOException {
	try {
	    List<ProductLocationBean> productLocations = runAscendingQuery();
	    return productLocations
		    .toArray(new ProductLocationBean[productLocations.size()]);
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

    public ProductLocationBean getProductLocation(long prodlocID)
	    throws DAOException, EntityNotFoundException {
	Key plKey = KeyFactory.createKey(rootKey, "ProductLocation", prodlocID);
	Entity productLocation = datastore.get(plKey);
	ProductLocationBean plBean = makeBean(productLocation);
	return plBean;
    }

    private List<ProductLocationBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<ProductLocationBean> productLocations = new ArrayList<ProductLocationBean>();
	for (Entity e : entities) {
	    productLocations.add(makeBean(e));
	}
	return productLocations;
    }

    private ProductLocationBean makeBean(Entity e) {
	ProductLocationBean plbean = new ProductLocationBean();
	plbean.setProdlocID(e.getKey().getId());
	plbean.setProductID((Long) e.getProperty("productID"));
	plbean.setStoreID(Integer.parseInt(e.getProperty("storeID").toString()));
	plbean.setAisleNum(Integer.parseInt(e.getProperty("aisleNum")
		.toString()));
	return plbean;
    }

}
