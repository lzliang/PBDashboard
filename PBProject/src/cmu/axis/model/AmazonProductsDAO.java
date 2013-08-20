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

import edu.cmu.axis.api.Feedback;

public class AmazonProductsDAO {
	private final DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	private final Key rootKey = KeyFactory.createKey("Root", "root");
	private final Query ascendingQuery = new Query("AmazonProduct", rootKey)
			.addSort("barcode", Query.SortDirection.ASCENDING);
	private final static Logger LOGGER = Logger.getLogger(Feedback.class
			.getName());

	public void addProduct(AmazonProducts product) throws DAOException {
		Transaction t = null;
		try {
			t = datastore.beginTransaction();

			Entity e = new Entity("AmazonProduct", rootKey);
			// e.setProperty("customerId", customer.getCustomerId());
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
			List<AmazonProducts> products = runAscendingQuery();
			return products.toArray(new AmazonProducts[products.size()]);
		} catch (Exception e) {
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
		AmazonProducts[] allProducts = getProducts();
		for (int i = 0; i < allProducts.length; i++) {
			if (allProducts[i].getBarCode().equalsIgnoreCase(barcode))
				return true;
		}
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
		List<Entity> entities = datastore.prepare(ascendingQuery).asList(
				FetchOptions.Builder.withLimit(100));
		List<AmazonProducts> products = new ArrayList<AmazonProducts>();
		for (Entity e : entities) {
			products.add(makeBean(e));
		}
		return products;
	}

	private AmazonProducts makeBean(Entity e) {
		AmazonProducts pbean = new AmazonProducts();
		pbean.setProductID(e.getKey().getId());
		pbean.setProductName((String) e.getProperty("productName"));
		pbean.setReview(new Text((String) e.getProperty("review")));
		pbean.setProductDescription(new Text((String)e.getProperty("productDescription")));
		pbean.setBarCode((String) e.getProperty("barcode"));
		pbean.setSimilarProducts(new Text((String) (e
				.getProperty("similarProducts"))));
		return pbean;
	}
}
