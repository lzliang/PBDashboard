package cmu.axis.amazonapi;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class Reviews {
	String overallRating;
	List<review> reviewList;

	public class review {
		String rating;
		String customerName;
		String title;
		String content;
		String date;

	}

	public Reviews getReviews(String barcode) {
		Reviews reviews = new Reviews();
		ProductInfo pi = new ProductInfo();
		List<review> tempList = new ArrayList<review>();
		
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
		// System.out.print(date);

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

		// System.out.print(title);

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
		// System.out.print(indi_ratings);

		// get customer name
		List<String> cust_names = new ArrayList<String>();

		for (Element e : document.getElementById("productReviews")
				.getElementsByTag("a")) {
			if (e.getElementsByAttribute("style").hasText())
				cust_names.add(e.getElementsByAttribute("style").text());

		}
		// System.out.print(cust_names);

		
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
			review tempReview = new review();
			tempReview.date = dates.get(i);
			tempReview.customerName = cust_names.get(i);
			tempReview.rating = indi_ratings.get(i);
			tempReview.title = titles.get(i);
			tempReview.content = contents.get(i);
			tempList.add(tempReview);
			
		}
		
		this.reviewList = tempList;
		
		return reviews;

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
