package cmu.axis.amazonapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cmu.axis.databean.AmazonProducts;
import cmu.axis.model.AmazonProductsDAO;
import cmu.axis.model.DAOException;

import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;

import edu.cmu.axis.api.Feedback;

public class ProductInfo {
	private static final String AWS_ACCESS_KEY_ID_1 = "AKIAI3NCEPGAPX2DE7SQ";
	private static final String AWS_SECRET_KEY_1 = "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";
	private static final String AWS_ACCESS_KEY_ID_2 = "AKIAIXJ55O4DOO7NQG2A";
	private static final String AWS_SECRET_KEY_2 = "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";
	private static final String ENDPOINT = "ecs.amazonaws.com";
	private final static Logger LOGGER = Logger.getLogger(Feedback.class
			.getName());
	private AmazonProductsDAO apd = new AmazonProductsDAO();
	private Gson gson = new Gson();

	public Map<String, String> getProductInfoByBarcode(String barcode) {
		LOGGER.info("line 41");
		try {
			LOGGER.info("line 42: ");
			if (apd.doesExist(barcode)) {
				LOGGER.info("line 43: ");
				AmazonProducts ap = apd.getProduct(barcode);
				LOGGER.info("line 44: " + gson.toJson(ap));
				String productJson = ap.getProductDescription().getValue();
				LOGGER.info("line 46: " + productJson);
				if (productJson != null && !productJson.equals("")) {
					Map<String, String> product = gson.fromJson(productJson,
							Map.class);
					return product;
				}
			}
		} catch (Exception e) {
			// do nothing, wait for the API to do it again
			LOGGER.severe("right at line 53");
			LOGGER.severe(e.getMessage());
		}
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> product = new HashMap<String, String>();

		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID_1, AWS_SECRET_KEY_1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String requestUrl = null;
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2009-03-31");
		params.put("Operation", "ItemLookup");
		params.put("IdType", "UPC");
		params.put("SearchIndex", "All");

		params.put("ItemId", barcode);
		params.put("AssociateTag", "th0426-20");
		params.put("ResponseGroup", "Medium");

		requestUrl = helper.sign(params);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			Node titleNode = doc.getElementsByTagName("Title").item(0);
			product.put("Name", titleNode.getTextContent());
			Node descNode = doc.getElementsByTagName("Content").item(0);
			product.put("Description", descNode.getTextContent());
			Node priceNode = doc.getElementsByTagName("FormattedPrice").item(0);
			product.put("Price", priceNode.getTextContent());
			Node picNode = doc.getElementsByTagName("LargeImage").item(0)
					.getFirstChild();
			product.put("Picture", picNode.getTextContent());
			Node reviewsNode = doc.getElementsByTagName("ItemLink").item(5)
					.getLastChild();
			product.put("Reviews", reviewsNode.getTextContent());
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			LOGGER.info("Can not get product info from amazon, returning null");
			return null;
		}

		AmazonProducts ap = new AmazonProducts();
		ap.setProductDescription(new Text(gson.toJson(product)));
		ap.setBarCode(barcode);
		try {
			apd.addProduct(ap);
		} catch (DAOException e) {
			LOGGER.severe(e.getLocalizedMessage());
			LOGGER.severe(e.getMessage());
			LOGGER.severe("DAO error, can not save product info to datastore");
			e.printStackTrace();
		}
		return product;
	}

	private Map<String, String> getProductInfoByASIN(String ASIN) {
		Map<String, String> params = new HashMap<String, String>();

		Map<String, String> product = new HashMap<String, String>();
		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID_2, AWS_SECRET_KEY_2);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String requestUrl = null;

		params.put("Service", "AWSECommerceService");
		params.put("Version", "2009-03-31");
		params.put("Operation", "ItemLookup");
		params.put("IdType", "ASIN");
		params.put("ItemId", ASIN);
		params.put("AssociateTag", "th0426-20");
		params.put("ResponseGroup", "Medium");

		requestUrl = helper.sign(params);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			Node titleNode = doc.getElementsByTagName("Title").item(0);
			product.put("Name", titleNode.getTextContent());
			Node descNode = doc.getElementsByTagName("Content").item(0);
			product.put("Description", descNode.getTextContent());
			Node priceNode = doc.getElementsByTagName("FormattedPrice").item(0);
			product.put("Price", priceNode.getTextContent());
			Node picNode = doc.getElementsByTagName("LargeImage").item(0)
					.getFirstChild();
			product.put("Picture", picNode.getTextContent());
			Node reviewsNode = doc.getElementsByTagName("ItemLink").item(5)
					.getLastChild();
			product.put("Reviews", reviewsNode.getTextContent());

		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			LOGGER.info("Can not get product info from amazon, returning null");
			return null;
		}
		return product;
	}

	// this method returns similar products for the given barcode
	public Map<String, Map<String, String>> getSimilarities(String barcode) {
		try {
			if (apd.doesExist(barcode)) {
				AmazonProducts ap = apd.getProduct(barcode);
				String similarProductsJson = ap.getSimilarProducts().getValue();
				// TODO test
				return gson.fromJson(similarProductsJson, Map.class);
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
			// doing nothing, go to amazon to get it again
		}
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Map<String, String>> similarities = new HashMap<String, Map<String, String>>();
		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID_1, AWS_SECRET_KEY_1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String requestUrl = null;
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2009-03-31");
		params.put("Operation", "ItemLookup");
		params.put("IdType", "UPC");
		params.put("SearchIndex", "All");

		params.put("ItemId", barcode);
		params.put("AssociateTag", "th0426-20");
		params.put("ResponseGroup", "Similarities");

		requestUrl = helper.sign(params);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			NodeList ASINNodeList = doc.getElementsByTagName("SimilarProduct");
			for (int i = 0; i < ASINNodeList.getLength(); i++) {
				Node ASINNode = ASINNodeList.item(i);
				String ASIN = ASINNode.getFirstChild().getTextContent();
				System.out.println(ASIN);
				similarities.put(ASIN, getProductInfoByASIN(ASIN));
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try { // TODO test
			AmazonProducts ap = apd.getProduct(barcode);
			if (ap == null) {
				ap = new AmazonProducts();
				ap.setBarCode(barcode);
				ap.setSimilarProducts(new Text(gson.toJson(similarities)));
				apd.addProduct(ap);
			} else {
				ap.setSimilarProducts(new Text(gson.toJson(similarities)));
				apd.updateProduct(ap.getProductID(), ap);
			}
		} catch (Exception e) {
			LOGGER.severe("Can not save similar products into datastore");
			// do nothing else;
		}
		return similarities;
	}
}
