package cmu.axis.databean;

import java.util.Date;

public class RatingHistoryBean {
    private long customerId;
    private long employeeId;
    private double rating;
    private Date timeStamp;

    public long getCustomerId() {
	return customerId;
    }

    public void setCustomerId(long customerId) {
	this.customerId = customerId;
    }

    public long getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(long employeeId) {
	this.employeeId = employeeId;
    }

    public double getRating() {
	return rating;
    }

    public void setRating(double rating) {
	this.rating = rating;
    }

    public Date getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
	this.timeStamp = timeStamp;
    }

}
