package cmu.axis.databean;

public class AmazonProducts {
    private long productID;
    private String barCode;
    private String productName;
    private String productDescription;
    private String review;
    private String similarProducts;

    public AmazonProducts() {

    }

    public AmazonProducts(String bCode, String pName, String pDesc, String rev,
	    String simProds) {
	this.barCode = bCode;
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
	return barCode;
    }

    public void setBarCode(String barCode) {
	this.barCode = barCode;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public String getProductDescription() {
	return productDescription;
    }

    public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
    }

    public String getReview() {
	return review;
    }

    public void setReview(String review) {
	this.review = review;
    }

    public String getSimilarProducts() {
	return similarProducts;
    }

    public void setSimilarProducts(String similarProducts) {
	this.similarProducts = similarProducts;
    }

}
