package cmu.axis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cmu.axis.databean.AmazonProducts;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.Transaction;
import com.google.gson.Gson;

public class AmazonProductsDAO {
    private final DatastoreService datastore = DatastoreServiceFactory
	    .getDatastoreService();
    private final Key rootKey = KeyFactory.createKey("Root", "root");
    private final Query ascendingQuery = new Query("AmazonProduct", rootKey)
	    .addSort("barcode", Query.SortDirection.ASCENDING);
    private final static Logger LOGGER = Logger
	    .getLogger(AmazonProductsDAO.class.getName());
    private final Gson gson = new Gson();

    public void addProduct(AmazonProducts product) throws DAOException {
	Transaction t = null;
	try {
	    t = datastore.beginTransaction();
	    Entity e = new Entity("AmazonProduct", rootKey);
	    e.setProperty("productName", product.getProductName());
	    e.setProperty("review", product.getReview());
	    e.setProperty("productDescription", product.getProductDescription());
	    e.setProperty("barcode", product.getBarCode());
	    e.setProperty("similarProducts", product.getSimilarProducts());
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

    public AmazonProducts[] getProducts() throws DAOException {
	try {
	    LOGGER.severe("in the getProducts()");
	    List<AmazonProducts> products = runAscendingQuery();
	    return products.toArray(new AmazonProducts[products.size()]);
	} catch (Exception e) {
	    LOGGER.severe(e.getMessage());
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public AmazonProducts getProduct(String barcode) throws DAOException,
	    EntityNotFoundException {
	try {
	    AmazonProducts aProduct = new AmazonProducts();
	    List<AmazonProducts> products = runAscendingQuery();
	    for (AmazonProducts product : products) {
		if (product.getBarCode().equals(barcode))
		    aProduct = product;
	    }
	    return aProduct;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DAOException(e);
	}
    }

    public boolean doesExist(String barcode) throws DAOException,
	    EntityNotFoundException {
	LOGGER.severe("in the doesExist: " + barcode);
	AmazonProducts[] allProducts = getProducts();
	for (int i = 0; i < allProducts.length; i++) {
	    if (allProducts[i].getBarCode().equalsIgnoreCase(barcode))
		return true;
	}
	return false;

    }

    public boolean alternateDoesExist(String barcode) throws DAOException,
	    EntityNotFoundException {
	AmazonProducts product = getProduct(barcode);
	if (product != null)
	    return true;
	else
	    return false;
    }

    public void updateProduct(long pID, AmazonProducts pBean)
	    throws DAOException, EntityNotFoundException {
	Key pKey = KeyFactory.createKey(rootKey, "AmazonProduct", pID);
	Entity product = datastore.get(pKey);
	product.setProperty("barcode", pBean.getBarCode());
	product.setProperty("productName", pBean.getProductName());
	product.setProperty("review", pBean.getReview());
	product.setProperty("productDescription", pBean.getProductDescription());
	product.setProperty("similarProducts", pBean.getSimilarProducts());
	datastore.put(product);
    }

    public AmazonProducts getProduct(long productID) throws DAOException,
	    EntityNotFoundException {
	Key pKey = KeyFactory.createKey(rootKey, "AmazonProduct", productID);
	Entity product = datastore.get(pKey);
	AmazonProducts pBean = makeBean(product);
	return pBean;
    }

    private List<AmazonProducts> runAscendingQuery() {
	LOGGER.severe("in runAscendingQuery()");
	List<Entity> entities = datastore.prepare(ascendingQuery).asList(
		FetchOptions.Builder.withLimit(100));
	List<AmazonProducts> products = new ArrayList<AmazonProducts>();
	LOGGER.severe("entities: " + gson.toJson(entities));
	for (Entity e : entities) {
	    LOGGER.severe("right before makeBean");
	    products.add(makeBean(e));
	}
	return products;
    }

    private AmazonProducts makeBean(Entity e) {
	LOGGER.severe("int the makeBean method");
	AmazonProducts pbean = new AmazonProducts();
	pbean.setProductID(e.getKey().getId());
	pbean.setProductName((String) e.getProperty("productName"));
	pbean.setReview((Text) e.getProperty("review"));
	pbean.setProductDescription((Text) e.getProperty("productDescription"));
	pbean.setBarCode((String) e.getProperty("barcode"));
	pbean.setSimilarProducts((Text) e.getProperty("similarProducts"));
	return pbean;
    }
}