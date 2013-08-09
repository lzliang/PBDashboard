package cmu.axis.databean;

public class RequestBean {
    private long requestID;
    private long customerID;
    private long employeeID;
    private String employeeName;
    private String customerName;
    private int storeID;
    private String inStoreLocation;
    private String deviceId;
    private String query;
    private String barcode;
    private String helpRequestTime;
    private String helpReceivedTime;
    private String day;
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

    public String getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
    }

    public String getTimeStamp() {
	return helpRequestTime;
    }

    public void setTimeStamp(String timeStamp) {
	this.helpRequestTime = timeStamp;
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

    public String getHelpReceivedTime() {
	return helpReceivedTime;
    }

    public void setHelpReceivedTime(String helpReceivedTime) {
	this.helpReceivedTime = helpReceivedTime;
    }

    public String getInStoreLocation() {
	return inStoreLocation;
    }

    public void setInStoreLocation(String inStoreLocation) {
	this.inStoreLocation = inStoreLocation;
    }

    public String getCustomerName() {
	return customerName;
    }

    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

    public String getDay() {
	return day;
    }

    public void setDay(String day) {
	this.day = day;
    }

}
