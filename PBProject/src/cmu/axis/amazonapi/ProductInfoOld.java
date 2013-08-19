package cmu.axis.amazonapi;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@Deprecated
public class ProductInfoOld {
	private static final String AWS_ACCESS_KEY_ID = "AKIAI3NCEPGAPX2DE7SQ";
	private static final String AWS_SECRET_KEY = "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";
	private static final String ENDPOINT = "ecs.amazonaws.com";
	// private static final String ITEM_ID = "813810010424";
	static Map<String, String> params = new HashMap<String, String>();

	public Map<String, String> getProductInfo(String barcode) {
		Map<String, String> product = new HashMap<String, String>();

		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
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

	public static void main(String args[]) {
		ProductInfoOld p = new ProductInfoOld();
		Map<String, String> test = new HashMap<String, String>();
		test = p.getProductInfo("813810010424");

		System.out.println(test.get("Name"));
		System.out.println(test.get("Description"));
		System.out.println(test.get("Picture"));
		System.out.println(test.get("Price"));
	}
}
