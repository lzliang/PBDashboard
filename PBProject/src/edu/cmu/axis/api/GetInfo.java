package edu.cmu.axis.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
public class GetInfo {
	
	@GET
	@Path("{machineID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getInfo(@PathParam("machineID") int machineID) {
	//public String info() {
		return machineID+"";
	}
}
