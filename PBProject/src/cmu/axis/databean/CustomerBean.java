package cmu.axis.databean;

public class CustomerBean {
    private long customerID;
    private String customerName;
    private String IMEI;

    public long getCustomerID() {
	return customerID;
    }

    public void setCustomerID(long customerID) {
	this.customerID = customerID;
    }

    public String getCustomerName() {
	return customerName;
    }

    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

    public String getIMEI() {
	return IMEI;
    }

    public void setIMEI(String iMEI) {
	IMEI = iMEI;
    }

}
