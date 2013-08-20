package cmu.axis.databean;

import com.google.appengine.api.datastore.Text;

public class AmazonProducts {
<<<<<<< HEAD
	private long productID;
	private String barcode;
	private String productName;
	private Text productDescription;
	private Text review;
	private Text similarProducts;
=======
    private long productID;
    private String barcode;
    private String productName;
    private Text productDescription;
    private Text review;
    private String similarProducts;
>>>>>>> branch 'master' of https://github.com/lzliang/PBDashboard.git

	public AmazonProducts() {

	}

<<<<<<< HEAD
	public AmazonProducts(String bCode, String pName, Text pDesc, Text rev,
			Text simProds) {
		this.barcode = bCode;
		this.productName = pName;
		this.productDescription = pDesc;
		this.review = rev;
		this.similarProducts = simProds;
	}
=======
    public AmazonProducts(String bCode, String pName, Text pDesc, Text rev,
	    String simProds) {
	this.barcode = bCode;
	this.productName = pName;
	this.productDescription = pDesc;
	this.review = rev;
	this.similarProducts = simProds;
    }
>>>>>>> branch 'master' of https://github.com/lzliang/PBDashboard.git

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getBarCode() {
		return barcode;
	}

	public void setBarCode(String barCode) {
		this.barcode = barCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

<<<<<<< HEAD
	public Text getProductDescription() {
		return productDescription;
	}
=======
    public Text getProductDescription() {
	return productDescription;
    }
>>>>>>> branch 'master' of https://github.com/lzliang/PBDashboard.git

<<<<<<< HEAD
	public void setProductDescription(Text productDescription) {
		this.productDescription = productDescription;
	}
=======
    public void setProductDescription(Text productDescription) {
	this.productDescription = productDescription;
    }
>>>>>>> branch 'master' of https://github.com/lzliang/PBDashboard.git

<<<<<<< HEAD
	public Text getReview() {
		return review;
	}
=======
    public Text getReview() {
	return review;
    }
>>>>>>> branch 'master' of https://github.com/lzliang/PBDashboard.git

<<<<<<< HEAD
	public void setReview(Text review) {
		this.review = review;
	}
=======
    public void setReview(Text review) {
	this.review = review;
    }
>>>>>>> branch 'master' of https://github.com/lzliang/PBDashboard.git

	public Text getSimilarProducts() {
		return similarProducts;
	}

	public void setSimilarProducts(Text similarProducts) {
		this.similarProducts = similarProducts;
	}

}
