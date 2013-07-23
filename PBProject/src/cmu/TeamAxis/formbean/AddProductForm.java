package cmu.TeamAxis.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddProductForm extends FormBean{

	private String price;
	private String type;
	private String description;
	
	public String getPrice() {
		return price;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setPrice(String s) {
		price  = trimAndConvert(s,"<>\"");
	}
	
	public void setType(String s) {
		type = trimAndConvert(s,"<>\"");
	}
	
	public void setDescription(String s) {
		description = trimAndConvert(s,"<>\"");
	}
	
	 public List<String> getValidationErrors() {
         List<String> errors = new ArrayList<String>();

         if (price == null || price.length() == 0) {
                 errors.add("Price cannot be empty.");
         }
         
         if (type == null || type.length() == 0) {
             errors.add("Product type cannot be empty.");
         }
	 
	 
         return errors;
	 }
}
