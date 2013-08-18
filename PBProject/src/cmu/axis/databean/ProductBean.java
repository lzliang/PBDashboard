package cmu.axis.databean;

public class ProductBean {
    private long productID;
    private String barCode;
    private String productName;
    private String productDescription;
    private String review;
    private String similarProducts;

    public ProductBean() {

    }

    public ProductBean(String productName, String review,
	    String productDescription, String barCode, String simProds) {
	this.productName = productName;
	this.review = review;
	this.productDescription = productDescription;
	this.barCode = barCode;
	this.setSimilarProducts(simProds);
    }

    public long getProductID() {
	return productID;
    }

    public void setProductID(long productID) {
	this.productID = productID;
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

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public String getBarCode() {
	return barCode;
    }

    public void setBarCode(String barCode) {
	this.barCode = barCode;
    }

    public String getSimilarProducts() {
	return similarProducts;
    }

    public void setSimilarProducts(String similarProducts) {
	this.similarProducts = similarProducts;
    }

}
