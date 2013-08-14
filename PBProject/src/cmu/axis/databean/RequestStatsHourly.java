package cmu.axis.databean;

public class RequestStatsHourly {
    private int numberOfServedRequests;
    private int staringHour;
    private int endingHour;

    public int getNumberOfServedRequests() {
	return numberOfServedRequests;
    }

    public void setNumberOfServedRequests(int numberOfServedRequests) {
	this.numberOfServedRequests = numberOfServedRequests;
    }

    public int getStaringHour() {
	return staringHour;
    }

    public void setStaringHour(int staringHour) {
	this.staringHour = staringHour;
    }

    public int getEndingHour() {
	return endingHour;
    }

    public void setEndingHour(int endingHour) {
	this.endingHour = endingHour;
    }

}
