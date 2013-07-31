package cmu.axis.databean;

public class StoreBean {
    private long storeID;
    private String longitude;
    private String latitude;

    public long getStoreID() {
	return storeID;
    }

    public void setStoreID(long storeID) {
	this.storeID = storeID;
    }

    public String getLatitude() {
	return latitude;
    }

    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }

    public String getLongitude() {
	return longitude;
    }

    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }

}
