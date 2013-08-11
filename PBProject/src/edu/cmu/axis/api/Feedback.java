package edu.cmu.axis.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	//finished
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateFeedback(String inputRequest) {
		//{"machineID":"", "feedback":"","rating":"","bought":"true/false"}
		
		Map<String,String> fb = gson.fromJson(inputRequest, Map.class);
		Map<String,String> rt = new HashMap<String,String>();
		fb.remove("machineID");
		try{
		RequestBean rb = rd.getRequestbyMachineId(fb.get("machineID"));
		
		rd.updateRequest(rb.getRequestID(), gson.toJson(fb));
		}
		catch(Exception e){
			return Util.returnError(null, e);
		}
		rt.put("status", "sucess");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
		
		
	}
}
