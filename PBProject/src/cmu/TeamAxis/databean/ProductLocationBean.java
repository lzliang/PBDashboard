package cmu.TeamAxis.databean;

public class ProductLocationBean {
    private long prodlocID;
    private long productID;
    private int storeID;
    private int aisleNum;

    public long getProductID() {
	return productID;
    }

    public void setProductID(long productID) {
	this.productID = productID;
    }

    public int getStoreID() {
	return storeID;
    }

    public void setStoreID(int storeID) {
	this.storeID = storeID;
    }

    public int getAisleNum() {
	return aisleNum;
    }

    public void setAisleNum(int aisleNum) {
	this.aisleNum = aisleNum;
    }

    public long getProdlocID() {
	return prodlocID;
    }

    public void setProdlocID(long prodlocID) {
	this.prodlocID = prodlocID;
    }

}
