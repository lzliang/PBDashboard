package cmu.TeamAxis.databean;

public class RequestBean {
    private long requestID;
    private long customerID;
    private long employeeID;
    private int storeID;
    private int aisleNum;
    private String queryType;
    private long productID;
    private String timeStamp;
    private String status;

    public long getRequestID() {
	return requestID;
    }

    public void setRequestID(long requestID) {
	this.requestID = requestID;
    }

    public long getCustomerID() {
	return customerID;
    }

    public void setCustomerID(long customerID) {
	this.customerID = customerID;
    }

    public long getEmployeeID() {
	return employeeID;
    }

    public void setEmployeeID(long employeeID) {
	this.employeeID = employeeID;
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

    public String getQueryType() {
	return queryType;
    }

    public void setQueryType(String queryType) {
	this.queryType = queryType;
    }

    public long getProductID() {
	return productID;
    }

    public void setProductID(long productID) {
	this.productID = productID;
    }

    public String getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
	this.timeStamp = timeStamp;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

}
