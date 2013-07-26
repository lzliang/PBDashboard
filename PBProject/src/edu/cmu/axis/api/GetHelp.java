package edu.cmu.axis.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@Path("/help")
public class GetHelp {

	/**
	 * This is an example of GET
	 * The url of this method is: 
	 * http://localhost:8888/_api/help, method get
	 * @return a json string
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String info() {
		return "Hello Jersey on Google App Engine";
	}

	/**
	 * The url of this method is: 
	 * http://localhost:8888/_api/help, method post
	 * @param pData
	 * @return
	 */
	@POST
	//@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String processRequset(String pData) {
	    //Use gson to convert input string into maps would do all our work
	    return pData;
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String testPut(String pData) {
	    //Use gson to convert input string into maps would do all our work
	    return pData;
	}
}