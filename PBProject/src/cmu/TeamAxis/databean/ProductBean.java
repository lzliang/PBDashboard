package cmu.TeamAxis.databean;

public class ProductBean {
    private long productID;
    private String productName;
    private String productType;
    private String productDescription;
    private String barCode;
    private double price;

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

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
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

}
