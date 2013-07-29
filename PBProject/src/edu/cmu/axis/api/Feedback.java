package edu.cmu.axis.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/feedback")
public class Feedback {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFeedback(String inputRequest){
		//do something with gson TODO
		
		return "";
	}
}
