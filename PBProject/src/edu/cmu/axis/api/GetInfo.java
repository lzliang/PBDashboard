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
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.amazonapi.Reviews;
@Path("/info")
public class GetInfo {
	
	@GET
	@Path("/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getInfo(@PathParam("barcode")  String barcode) {
		ProductInfo pi = new ProductInfo();
		Gson gson = new Gson();
		Map<String, Object> rt = new HashMap<String, Object>();
		Map<String, String> productInfo = pi.getProductInfoByBarcode(barcode);
		if(productInfo.size() == 0){
			productInfo.put("Status","error");
			productInfo.put("Reason", "Can not get the product from Amazon");		
		}else{
			Reviews r= new Reviews();
			List<Map<String,String>> reviews = r.getReviews(barcode);
			productInfo.remove("Reviews");
			rt.put("Reviews", reviews);
			
			productInfo.put("Status","success");
		}
		rt.putAll(productInfo);
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(gson.toJson(rt)).build();
		//return gson.toJson(rt);
	}
	
	@GET
	@Path("/lon/{lon}/lat/{lat}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getStoreID(@PathParam("lon") String lon,@PathParam("lat")String lat){
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(lon + "_"+lat).build();
	}
}

