package cmu.axis.databean;

public class RequestStats {
    private int numberOfServedRequests;
    private String Day;

    public RequestStats() {

    }

    public int getNumberOfServedRequests() {
	return numberOfServedRequests;
    }

    public void setNumberOfServedRequests(int numberOfServedRequests) {
	this.numberOfServedRequests = numberOfServedRequests;
    }

    public String getDay() {
	return Day;
    }

    public void setDay(String day) {
	Day = day;
    }

}
