package edu.cmu.axis.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mortbay.util.ajax.JSON;

import com.google.gson.Gson;


@Path("/feedback")
public class Feedback {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFeedback(String inputRequest) {
		//do something with gson TODO
		Gson gson = new Gson();
		Feedback fb = gson.fromJson(inputRequest, Feedback.class);
		Map<String,String> returnmap = new HashMap<String,String>();
		//if(saveFeedback(fb)){
			returnmap.put("status","success");
		//}else{
			returnmap.put("status","error");
		//}
		
		return JSON.toString(returnmap);
	}
}
