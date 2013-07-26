package cmu.axis.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddProductForm extends FormBean{

	private String productName;
	private String productType;
	private String productDescription;
	private String barCode;
	private String price;
	
	public String getProductName() { return productName; }
	public String getProductType() { return productType; }
	public String getProductDescription() { return productDescription; }
	public String getBarCode() { return barCode; }
	public String getPrice() { return price;	}
	

	public void setProductName(String s) {
		productName = trimAndConvert(s,"<>\"");
	}
	
	public void setProductType(String s) {
		productType = trimAndConvert(s,"<>\"");
	}
	
	public void setProductDescription(String s) {
		productDescription = trimAndConvert(s,"<>\"");
	}
	
	public void setBarCode(String s) {
		barCode = trimAndConvert(s,"<>\"");
	}
	
	public void setPrice(String s) {
		price  = trimAndConvert(s,"<>\"");
	}
	
	 public List<String> getValidationErrors() {
         List<String> errors = new ArrayList<String>();

         if (productName == null || productName.length() == 0) {
             errors.add("Product Name cannot be empty.");
         }
         
         if (barCode == null || barCode.length() == 0) {
        	 errors.add("Barcode cannot be empty.");
         }
         
         if (productType == null || productType.length() == 0) {
             errors.add("Product type cannot be empty.");
         }
	 
         if (price == null || price.length() == 0) {
        	 errors.add("Price cannot be empty.");
         }
	 
         return errors;
	 }
}
