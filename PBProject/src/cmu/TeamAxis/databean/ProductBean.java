package cmu.TeamAxis.databean;

public class ProductBean {
	 private String description;
     private String price;
     private String type;
     
     public ProductBean(String description, String price, String type) {
    	 this.description = description;
    	 this.price = price;
    	 this.type = type;
     }
	
     public String getDescription() {
    	 return description;
     }

     public String getPrice() {
    	 return price;
     }
     
     public String getType() {
    	 return type;
     }
   
     public void setDescription(String s) {
    	 description = s;
     }
     
     public void setPrice(String s) {
    	 price = s;
     }
     
     public void setType(String s) {
    	 type = s;
     }
}
