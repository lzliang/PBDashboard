package cmu.axis.amazonapi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProductInfo {
	private static final String AWS_ACCESS_KEY_ID_1 = "AKIAI3NCEPGAPX2DE7SQ";
	private static final String AWS_SECRET_KEY_1 = "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";
	private static final String AWS_ACCESS_KEY_ID_2 = "AKIAIXJ55O4DOO7NQG2A";
	private static final String AWS_SECRET_KEY_2 = "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";
	private static final String ENDPOINT = "ecs.amazonaws.com";
	// private static final String ITEM_ID = "813810010424";
	
//	static Map<String, String> params = new HashMap<String, String>();
	public Map<String, String> getProductInfoByBarcode(String barcode) {
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
			

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}
	public Map<String, String> getProductInfoByASIN(String ASIN) {
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

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}
	public Map<String, Map<String, String>> getSimilarities(String barcode){
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
//			System.out.print(ASINNodeList.getLength());
			for(int i=0; i<ASINNodeList.getLength();i++){
				Node ASINNode = ASINNodeList.item(i);
				String ASIN = ASINNode.getFirstChild().getTextContent();
				System.out.println(ASIN);
				
				similarities.put(ASIN,getProductInfoByASIN(ASIN));
			}
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return similarities;
	}
	public static void main(String args[]) {
		ProductInfo p = new ProductInfo();
		Map<String, String> test3 = new HashMap<String, String>();
		test3 = p.getProductInfoByBarcode("813810010424");
		System.out.println(test3.get("Name"));

		Map<String, Map<String, String>> test1 = p.getSimilarities("813810010424");
		Set<String> test2 = test1.keySet();
		System.out.println(test2);
		System.out.println(test1.get(test2.toArray()[0]).get("Name"));
		System.out.println(test1.get(test2.toArray()[0]).get("Description"));
		System.out.println(test1.get(test2.toArray()[0]).get("Price"));
		System.out.println(test1.get(test2.toArray()[0]).get("Picture"));
		System.out.println(test1.get(test2.toArray()[0]).get("Reviews"));
		
		
	}
}
