package cmu.TeamAxis.databean;

public class OtherPricesBean {
    private long productID;
    private String retailerName;
    private double price;

    public long getProductID() {
	return productID;
    }

    public void setProductID(long productID) {
	this.productID = productID;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public String getRetailerName() {
	return retailerName;
    }

    public void setRetailerName(String retailerName) {
	this.retailerName = retailerName;
    }

}
