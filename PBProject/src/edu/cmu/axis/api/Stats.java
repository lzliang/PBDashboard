package edu.cmu.axis.api;

import java.util.HashMap;
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
			rt.put("data",stats);
		}
		rt.put("status", "success");
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(gson.toJson(rt)).build();
	}
	

}
