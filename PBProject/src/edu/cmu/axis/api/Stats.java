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

import cmu.axis.databean.RequestStats;
import cmu.axis.model.RequestDAO;

import com.google.gson.Gson;

@Path("/stats")
public class Stats {
	static Gson gson = new Gson();
	RequestDAO rd = new RequestDAO();
	@GET
	@Path("/servno/{intvl}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getInfo(@PathParam("intvl")  String intvl) {
		
		Map<String, Object> rt = new HashMap<String, Object>();
		RequestStats[] stats;
		try{
			stats = rd.getRequestStats();
		}
		catch (Exception e){
			e.printStackTrace();
			return Util.returnError(null, e);
		}
		
		if(intvl.trim().toLowerCase().equals("all")){
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < stats.length; i++){
				
				sb.append(stats[i].getDay());
				sb.append(",");
				sb.append(stats[i].getNumberOfServedRequests());
				sb.append(",");
			}
			if(sb.charAt(sb.length()-1) == ','){
				sb.substring(0, sb.length()-1);
			}
			rt.put("data",sb.toString());
		}
		rt.put("status", "success");
		//String s = "[1147651200000,67.79],[1147737600000,64.98],[1147824000000,65.26]";
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(gson.toJson(rt)).build();
	}
	

}
