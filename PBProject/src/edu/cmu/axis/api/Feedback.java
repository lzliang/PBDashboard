package edu.cmu.axis.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mortbay.util.ajax.JSON;

import cmu.axis.databean.RequestBean;
import cmu.axis.model.RequestDAO;

import com.google.gson.Gson;

@Path("/feedback")
public class Feedback {
	Gson gson = new Gson();
	RequestDAO rd = new RequestDAO();
	private final static Logger LOGGER = Logger.getLogger(Feedback.class.getName());
	
	//finished
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateFeedback(String inputRequest) {
		//{"machineID":"", "feedback":"","rating":"","bought":"true/false"}
		
		HashMap<String, String> fb = gson
				.fromJson(inputRequest, HashMap.class);
		LOGGER.severe("fb: " + fb);
		Map<String,String> rt = new HashMap<String,String>();
		
		try{
		LOGGER.severe("machineID: " +fb.get("machineID"));	
		RequestBean rb = rd.getRequestbyMachineId(fb.get("machineID"));
		fb.remove("machineID");
		rd.updateRequest(rb.getRequestID(), gson.toJson(fb));
		}
		catch(Exception e){
			LOGGER.severe("exception: "+ e.getMessage());
			return Util.returnError(null, e);
		}
		rt.put("status", "sucess");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
		
		
	}
}
