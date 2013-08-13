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

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.amazonapi.Reviews;

import com.google.gson.Gson;
@Path("/info")
public class GetInfo {
	Gson gson = new Gson();
	//finished
	@GET
	@Path("/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getInfo(@PathParam("barcode")  String barcode) {
		ProductInfo pi = new ProductInfo();
		Map<String, Object> rt = new HashMap<String, Object>();
		Map<String, String> productInfo = pi.getProductInfoByBarcode(barcode);
		if(productInfo==null || productInfo.size() == 0){
			return Util.returnError("Can not get the product from Amazon", null);	
		}else{
			Reviews r= new Reviews();
			List<Map<String,String>> reviews = new ArrayList<Map<String, String>>();
			try{
				reviews = r.getReviews(barcode);
			}catch (Exception e){
				
			}
			productInfo.remove("Reviews");			
			rt.put("Reviews", reviews);
			productInfo.put("Status","success");
		}
		rt.putAll(productInfo);
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(gson.toJson(rt)).build();
	}
	
	@Deprecated
	@GET
	@Path("/lon/{lon}/lat/{lat}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getStoreID(@PathParam("lon") String lon,@PathParam("lat")String lat){
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(lon + "_"+lat).build();
	}
}

