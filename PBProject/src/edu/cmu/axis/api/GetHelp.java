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

import cmu.axis.databean.EmployeeBean;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.EmployeeDAO;
import cmu.axis.model.RequestDAO;

import com.google.gson.Gson;

@Path("/help")
public class GetHelp {
	RequestDAO rd = new RequestDAO();
	EmployeeDAO ed = new EmployeeDAO();
	Gson gson = new Gson();
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
		@SuppressWarnings("unchecked")
		HashMap<String, String> helpMap = gson
				.fromJson(helpData, HashMap.class);
		RequestBean rb = new RequestBean();
		rb.setBarcode(helpMap.get("barCode"));
		rb.setDeviceId(helpMap.get("machineID"));
		rb.setStatus("Need Help");
		if(helpMap.containsKey("customerName")){
			rb.setCustomerName(helpMap.get("customerName"));
		}
		try{
			rd.addRequest(rb);
		}catch(Exception e){
			return Util.returnError(null, e);
		}
		Map<String,String> rt = new HashMap<String, String>();
		rt.put("status", "sucess");
		rt.put("time","3");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}

	//currently not taken
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response testPut(String pData) {
		// Use gson to convert input string into maps would do all our work
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(pData).build();
	}
//test
	@GET
	@Path("{machineID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getHelpingEmployee(@PathParam("machineID") String machineID) {
		RequestBean rb;
		Map<String,String> rt = new HashMap<String, String>();
		
		try {
			rb = rd.getRequestbyMachineId(machineID);
			String name = rb.getEmployeeName();
			EmployeeBean e = ed.getEmployee(rb.getCustomerID());
			rt.put("status", "success");
			rt.put("employeeName", name);
			rt.put("employeePic", e.getPicURL());
		} catch (Exception e) {
			rt.put("status", "error");
			return Response.status(200)
					.header("Access-Control-Allow-Origin", "*")
					.entity(gson.toJson(rt)).build();
		} 
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(rt).build();
	}

	//need test
	@GET
	@Path("/serving/{requestID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response serving(@PathParam("requestID") long requestID) {
		Map<String, String> rt = new HashMap<String,String>();
		try{
			RequestBean rb = rd.getRequest(requestID);
			if(rb.getStatus().equals("Need Help")){
				rb.setStatus("Serving");
				rd.updateRequest(requestID, rb);
			}else{
				throw new RuntimeException();
			}
		}
		catch (RuntimeException e){
			return Util.returnError("The request is not in the status to be switched to Serving", null);
		}catch(Exception e){
			return Util.returnError(null, e);
		}
		rt.put("status", "success");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}
	//test
	@GET
	@Path("/done/{requestID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response done(@PathParam("requestID") long requestID) {
		Map<String, String> rt = new HashMap<String,String>();
		try{
			RequestBean rb = rd.getRequest(requestID);
			if(rb.getStatus().equals("Serving")){
				rb.setStatus("Done");
				rd.updateRequest(requestID, rb);
			}else{
				throw new RuntimeException();
			}
		}
		catch (RuntimeException e){
			return Util.returnError("The request is not in the status to be switched to Done", null);
		}catch(Exception e){
			return Util.returnError(null, e);
		}
		rt.put("status", "sucess");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}
	//finished
	@GET
	@Path("/status/{requestID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response requestStatus(@PathParam("requestID") long requestID) {
		RequestBean rb = null;
		Map<String, String> rt = new HashMap<String, String>();
		Gson gson = new Gson();
		try {
			rb = rd.getRequest(requestID);
		} catch (Exception e) {
			return Util.returnError(null, e);
		}
		if(rb == null){
			return Util.returnError("Can not get the request with given ID", null);
		}
		rt.put("status", "sucess");
		rt.put("requestStatus", rb.getStatus());
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}

}
