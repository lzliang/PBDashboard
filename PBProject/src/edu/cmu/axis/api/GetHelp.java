package edu.cmu.axis.api;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.datanucleus.sco.backed.Map;

import com.google.gson.Gson;

@Path("/help")
public class GetHelp {

//	/**
//	 * This is an example of GET
//	 * The url of this method is: 
//	 * http://localhost:8888/_api/help, method get
//	 * @return a json string
//	 */
//	@GET
//	@Path("{machineID}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getHelpingEmployee() {
//		return "Hello Jersey on Google App Engine";
//	}

	/**
	 * The url of this method is: 
	 * http://localhost:8888/_api/help, method post
	 * @param pData
	 * @return
	 */
	@POST
	//@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String helpRequest(String pData) {
	    Gson gson = new Gson();
	    HashMap<String, String> request = gson.fromJson(pData, HashMap.class);
	   // if(saveHelpRequest(request)){ //TODO
	    	
	   // }else{
	    	
	  //  }
	    return pData;
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String testPut(String pData) {
	    //Use gson to convert input string into maps would do all our work
	    return pData;
	}
	
	@GET
	@Path("{machineID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHelpingEmployee(@PathParam("machineID") String machineID) {
	//	Map<String, String> rt = getHelpingEmployee(machineID);
		return machineID+"";
	}
}