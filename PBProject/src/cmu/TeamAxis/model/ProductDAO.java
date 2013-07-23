package cmu.TeamAxis.model;

import java.util.ArrayList;
import java.util.List;

import cmu.TeamAxis.databean.ProductBean;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class ProductDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("Product", rootKey).addSort(
	    "productName", Query.SortDirection.ASCENDING);

    public void addProduct(ProductBean product) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();

	    Entity e = new Entity("Product", rootKey);
	    // e.setProperty("customerId", customer.getCustomerId());
	    e.setProperty("productName", product.getProductName());
	    e.setProperty("productType", product.getProductType());
	    e.setProperty("productDescription", product.getProductDescription());
	    e.setProperty("barcode", product.getBarCode());
	    e.setProperty("price", product.getPrice());

	    // Create a new entity in the datastore. Id will be automatically
	    // set.
	    datastore.put(e);
	    product.setProductID(e.getKey().getId());
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

    public ProductBean[] getProducts() throws DAOException {
	try {
	    List<ProductBean> products = runAscendingQuery();
	    return products.toArray(new ProductBean[products.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public ProductBean getProduct(String productName) throws DAOException {
	try {
	    ProductBean aProduct = new ProductBean();
	    List<ProductBean> products = runAscendingQuery();
	    for (ProductBean product : products) {
		if (product.getProductName().equals(productName))
		    aProduct = product;
	    }
	    return aProduct;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public ProductBean getProduct(long productID) throws DAOException,
	    EntityNotFoundException {
	Key pKey = KeyFactory.createKey(rootKey, "Product", productID);
	Entity product = datastore.get(pKey);
	ProductBean pBean = makeBean(product);
	return pBean;
    }

    private List<ProductBean> runAscendingQuery() {
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<ProductBean> products = new ArrayList<ProductBean>();
	for (Entity e : entities) {
	    products.add(makeBean(e));
	}
	return products;
    }

    private ProductBean makeBean(Entity e) {
	ProductBean pbean = new ProductBean();
	pbean.setProductID(e.getKey().getId());
	pbean.setProductName((String) e.getProperty("productName"));
	pbean.setProductType((String) e.getProperty("productType"));
	pbean.setProductDescription((String) e
		.getProperty("productDescription"));
	pbean.setBarCode((String) e.getProperty("barcode"));
	pbean.setPrice((Double) (e.getProperty("price")));
	return pbean;
    }

}
