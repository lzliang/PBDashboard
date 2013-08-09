package edu.cmu.axis.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cmu.axis.databean.RequestBean;
import cmu.axis.model.DAOException;
import cmu.axis.model.RequestDAO;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gson.Gson;

@Path("/help")
public class GetHelp {
	RequestDAO rd = new RequestDAO();

	// /**
	// * This is an example of GET
	// * The url of this method is:
	// * http://localhost:8888/_api/help, method get
	// * @return a json string
	// */
	// @GET
	// @Path("{machineID}")
	// @Produces(MediaType.TEXT_PLAIN)
	// public String getHelpingEmployee() {
	// return "Hello Jersey on Google App Engine";
	// }

	/**
	 * The url of this method is: http://localhost:8888/_api/help, method post
	 * 
	 * @param pData
	 * @return
	 */
	@POST
	// @Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public Response helpRequest(String helpData) {
		// {"machineID":"", "barcode":"",
		// "location":"","customerName":"","storeID":""}

		Gson gson = new Gson();
		HashMap<String, String> helpMap = gson
				.fromJson(helpData, HashMap.class);
		RequestDAO rd = new RequestDAO();
		RequestBean rb = new RequestBean();
		rb.setBarcode(helpMap.get("barCode"));
		// rb.setStoreID(storeID)(helpMap.get("machineID"));
		// boolean saveRequest = saveHelpRequest(helpMap);
		// return Response.status(200).header("Access-Control-Allow-Origin",
		// "*").entity( ).build();
		return null;
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response testPut(String pData) {
		// Use gson to convert input string into maps would do all our work
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(pData).build();
	}

	@GET
	@Path("{machineID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getHelpingEmployee(@PathParam("machineID") String machineID) {
		// Map<String, String> rt = getHelpingEmployee(machineID);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(machineID).build();
	}

	@GET
	@Path("/serving/{requestID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response serving(@PathParam("requestID") String requestID) {
		// Map<String, String> rt = getHelpingEmployee(machineID);
		String s = "{\"status\":\"sucess\"}";
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(s).build();
	}

	@GET
	@Path("/done/{requestID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response done(@PathParam("requestID") String requestID) {
		// Map<String, String> rt = getHelpingEmployee(machineID);
		String s = "{\"status\":\"sucess\"}";
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(s).build();
	}

	@GET
	@Path("/status/{requestID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response requestStatus(@PathParam("requestID") String requestID) {
		RequestBean rb = null;
		Map<String, String> rt = new HashMap<String, String>();
		Gson gson = new Gson();
		try {
			rb = rd.getRequest(requestID);
		} catch (Exception e) {
			rt.put("status", "error");
			return Response.status(200)
					.header("Access-Control-Allow-Origin", "*")
					.entity(gson.toJson(rt)).build();
		}

		rt.put("status", "sucess");
		rt.put("requestStatus", rb.getStatus());
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}

}
