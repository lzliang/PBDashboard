package cmu.axis.databean;

public class RequestBean {
    private long requestID;
    private long customerID;
    private long employeeID;
    private String employeeName;
    private int storeID;
    private String location; // Remove it
    private String query;
    private long productID; //not necessary
    private String barcode;
    private String timeStamp;//time received help request, and time the help request is taken care of
    //add another time stamp
    private String status;
    private String customerFeedback;

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

    public String getQueryType() {
	return location;
    }

    public void setQueryType(String queryType) {
	this.location = queryType;
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

    public String getQuery() {
	return query;
    }

    public void setQuery(String query) {
	this.query = query;
    }

    public String getCustomerFeedback() {
	return customerFeedback;
    }

    public void setCustomerFeedback(String customerFeedback) {
	this.customerFeedback = customerFeedback;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

}
