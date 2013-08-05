package cmu.axis.databean;

public class EmployeeRatingBean {
    private long employeeId;
    private int numOfRatings;
    private double averageRating;

    public long getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(long employeeId) {
	this.employeeId = employeeId;
    }

    public int getNumOfRatings() {
	return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
	this.numOfRatings = numOfRatings;
    }

    public double getAverageRating() {
	return averageRating;
    }

    public void setAverageRating(double averageRating) {
	this.averageRating = averageRating;
    }

}
