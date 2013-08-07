package edu.cmu.axis.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.amazonapi.Reviews;
@Path("/info")
public class GetInfo {
	
	@GET
	@Path("/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getInfo(@PathParam("barcode")  String barcode) {
		ProductInfo pi = new ProductInfo();
		Gson gson = new Gson();
		Map<String, String> productInfo = pi.getProductInfoByBarcode(barcode);
		if(productInfo.size() == 0){
			productInfo.put("Status","error");
			productInfo.put("Reason", "Can not get the product from Amazon");
		}else{
			productInfo.remove("Reviews");
			productInfo.put("Status","success");
			Reviews r= new Reviews();
			List<Map<String,String>> reviews = r.getReviews(barcode);
			String strReview = gson.toJson(reviews);
			productInfo.put("Reviews", strReview);
		}
		return gson.toJson(productInfo);
	}
	
	@GET
	@Path("/lon/{lon}/lat/{lat}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getStoreID(@PathParam("lon") String lon,@PathParam("lat")String lat){
		return lon + "_"+lat;
	}
}

