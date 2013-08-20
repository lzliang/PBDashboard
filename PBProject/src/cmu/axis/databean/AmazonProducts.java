package cmu.axis.databean;

import com.google.appengine.api.datastore.Text;

public class AmazonProducts {

	private long productID;
	private String barcode;
	private String productName;
	private Text productDescription;
	private Text review;
	private Text similarProducts;

	public AmazonProducts() {

	}

	public AmazonProducts(String bCode, String pName, Text pDesc, Text rev,
			Text simProds) {
		this.barcode = bCode;
		this.productName = pName;
		this.productDescription = pDesc;
		this.review = rev;
		this.similarProducts = simProds;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getBarCode() {
		return barcode;
	}

	public void setBarCode(String barCode) {
		this.barcode = barCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Text getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(Text productDescription) {
		this.productDescription = productDescription;
	}

	public Text getReview() {
		return review;
	}

	public void setReview(Text review) {
		this.review = review;
	}

	public Text getSimilarProducts() {
		return similarProducts;
	}

	public void setSimilarProducts(Text similarProducts) {
		this.similarProducts = similarProducts;
	}

}
