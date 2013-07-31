package cmu.axis.databean;

public class EmployeeBean {
    private long employeeID;
    private String userName;
    private String password;
    private String designation;
    private String picURL;
    private long storeID;

    public long getEmployeeID() {
	return employeeID;
    }

    public void setEmployeeID(long employeeID) {
	this.employeeID = employeeID;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getDesignation() {
	return designation;
    }

    public void setDesignation(String designation) {
	this.designation = designation;
    }

    public String getPicURL() {
	return picURL;
    }

    public void setPicURL(String picURL) {
	this.picURL = picURL;
    }

    public long getStoreID() {
	return storeID;
    }

    public void setStoreID(long storeID) {
	this.storeID = storeID;
    }

}
