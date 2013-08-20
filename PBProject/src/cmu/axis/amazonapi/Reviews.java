package cmu.axis.amazonapi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cmu.axis.databean.AmazonProducts;
import cmu.axis.model.AmazonProductsDAO;

import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;

import edu.cmu.axis.api.Feedback;

public class Reviews {
	String overallRating;
	List<review> reviewList;
	private Gson gson = new Gson();
	private AmazonProductsDAO apd = new AmazonProductsDAO();
	private final static Logger LOGGER = Logger.getLogger(Feedback.class.getName());
	public class review {
		String rating;
		String customerName;
		String title;
		String content;
		String date;

	}

	public List<Map<String,String>> getReviews(String barcode) {
		try {
			if(apd.doesExist(barcode)){
				AmazonProducts ap = apd.getProduct(barcode);
				String reviewJson = ap.getReview().getValue();
				if(reviewJson!= null && !reviewJson.equals("")){
					//List<Map<String,String>> obj = gson.fromJson(reviewJson, List.class);
					//List<Map<String,String>> reviews = new ArrayList<Map<String,String>>();
					return gson.fromJson(reviewJson, List.class);
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			// do nothing else
		}
		ProductInfo pi = new ProductInfo();
		List<Map<String,String>> tempList = new ArrayList<Map<String,String>>();
		
		String reviewUrl = pi.getProductInfoByBarcode(barcode).get("Reviews");
		String rawData = URLConnection.getData(reviewUrl);
		Document document = Jsoup.parse(rawData);
		// System.out.print(document);

		// get overallRating
		Elements ratings = document.getElementsByClass("asinReviewsSummary");
		this.overallRating = ratings.text().split(" ")[0];
		// System.out.print(reviews.overallRating );

		Elements proReview = document.getElementById("productReviews")
				.getElementsByTag("span");
		// System.out.print(proReview);

		// get date
		List<String> dates = new ArrayList<String>();
		int date_index = 0;
		for (Element e : proReview) {
			if (e.getElementsByTag("nobr").hasText()) {
				if (date_index % 4 == 0) {
					dates.add(e.getElementsByTag("nobr").text());
				}
				date_index++;
			}

		}

		// get title
		List<String> titles = new ArrayList<String>();
		int title_index = 0;
		for (Element e : proReview) {
			if (e.getElementsByTag("b").hasText()) {
				if (title_index % 2 == 0) {
					titles.add(e.getElementsByTag("b").text());
				}
				title_index++;
			}

		}
		// get individual rating
		List<String> indi_ratings = new ArrayList<String>();
		int rating_index = 0;
		for (Element e : proReview) {
			if (e.getElementsByAttribute("title").hasText()) {
				if (rating_index % 2 == 0) {
					indi_ratings.add(e.getElementsByAttribute("title").text());
				}
				rating_index++;
			}

		}

		// get customer name
		List<String> cust_names = new ArrayList<String>();

		for (Element e : document.getElementById("productReviews")
				.getElementsByTag("a")) {
			if (e.getElementsByAttribute("style").hasText())
				cust_names.add(e.getElementsByAttribute("style").text());

		}
		
		//get content
		List<String> contents = new ArrayList<String>();
		document.getElementById("productReviews").getElementsByTag("span")
				.remove();
		document.getElementById("productReviews").getElementsByTag("a")
				.remove();
		document.getElementById("productReviews").getElementsByTag("b")
				.remove();
		document.getElementById("productReviews")
				.getElementsByAttributeValue("style", "margin-bottom:0.5em;")
				.remove();

		for (Element e : document.getElementById("productReviews")
				.getElementsByAttributeValue("style", "margin-left:0.5em;")){
			contents.add(e.text().substring(0, e.text().length()-3));
		}
//		System.out.print(contents);
		
		for(int i=0; i<dates.size();i++){
			Map<String,String> currReview = new HashMap<String,String>();
			currReview.put("Date", dates.get(i));
			currReview.put("CustomerName", cust_names.get(i));
			currReview.put("Rating",indi_ratings.get(i));
			currReview.put("Title", titles.get(i));
			currReview.put("Content", contents.get(i));
			tempList.add(currReview);
		}
		
		try {
			AmazonProducts ap = apd.getProduct(barcode);
			if(ap == null){
				ap.setBarCode(barcode);
				ap.setReview(new Text(gson.toJson(tempList)));
				apd.addProduct(ap);
			}else{
				ap.setReview(new Text(gson.toJson(tempList)));
				apd.updateProduct(ap.getProductID(), ap);
			}
		} catch (Exception e){
			LOGGER.info("Can not store review into data store: " + e.getMessage());
		}
		
		
		return tempList;

	}

	public static void main(String[] args) {
		Reviews reviews = new Reviews();
		reviews.getReviews("813810010424");
		System.out.println(reviews.overallRating);
		System.out.println(reviews.reviewList.get(0).customerName);
		System.out.println(reviews.reviewList.get(0).content);
		System.out.println(reviews.reviewList.get(0).date);
		System.out.println(reviews.reviewList.get(0).rating);
		System.out.println(reviews.reviewList.get(0).title);

	}

}
