package edu.cmu.axis.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
	private final static Logger LOGGER = Logger.getLogger(Feedback.class.getName());
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
		LOGGER.severe("RequestStats got from DAO: " + gson.toJson(stats));
		List<Long[]> statsList = new ArrayList<Long[]>();
//		Long[] s = {1153440000000L,60L};  
//		Long[] s1 = {1153699200000L,61L};
//		statsList.add(s);
//		statsList.add(s1);
		if(intvl.trim().toLowerCase().equals("all")){
			for(int i = 0; i < stats.length; i++){
				Long[] curr = new Long[2];
				curr[0] = Long.parseLong(stats[i].getDay());
				curr[1] = (long)(stats[i].getNumberOfServedRequests());
				statsList.add(curr);
			}
			rt.put("data",statsList);
		}
		rt.put("status", "success");
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(gson.toJson(rt)).build();
	}
	

}
