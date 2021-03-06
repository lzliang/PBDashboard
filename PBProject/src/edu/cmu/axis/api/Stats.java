package edu.cmu.axis.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cmu.axis.amazonapi.ProductInfo;
import cmu.axis.amazonapi.Reviews;
import cmu.axis.databean.RequestBean;
import cmu.axis.model.AmazonProductsDAO;
import cmu.axis.model.DAOException;
import cmu.axis.model.RequestDAO;

import com.google.gson.Gson;

@Path("/stats")
public class Stats {
	static Gson gson = new Gson();
	RequestDAO rd = new RequestDAO();
	private final static Logger LOGGER = Logger.getLogger(Feedback.class
			.getName());

	@GET
	@Path("/servno/{intvl}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getInfo(@PathParam("intvl") String intvl) {

		Map<String, Object> rt = new HashMap<String, Object>();
		Map<String, Integer> stats;
		try {
			stats = rd.getRequestStats();
		} catch (Exception e) {
			e.printStackTrace();
			return Util.returnError(null, e);
		}
		LOGGER.severe("RequestStats got from DAO: " + gson.toJson(stats));
		List<Long[]> statsList = new ArrayList<Long[]>();
		if (intvl.trim().toLowerCase().equals("all")) {
			Object[] objKeys = stats.keySet().toArray();
			String[] keys = new String[objKeys.length];
			for (int i = 0; i < keys.length; i++) {
				keys[i] = (String) objKeys[i];
			}
			Arrays.sort(keys);
			LOGGER.severe("Sorted keys: " + Arrays.toString(keys));
			for (String day : keys) {
				Long[] curr = new Long[2];
				curr[0] = Long.parseLong(day);
				curr[1] = (long) (stats.get(day));
				statsList.add(curr);
			}
			rt.put("data", statsList);
		}
		rt.put("status", "success");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}

	@GET
	@Path("/feedback/{employeeName}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getEmployeeFeedbacks(
			@PathParam("employeeName") String employeeName) throws DAOException {
		LOGGER.severe("getting feedbacks for employee: " + employeeName);
		RequestBean[] requests = null;
		try {
			requests = rd.getRequest(employeeName, "Done");
			LOGGER.severe("requests with employee " + gson.toJson(requests));
		} catch (Exception e) {
			Util.returnError(null, e);
		}
		Map<String, Object> rt = new HashMap<String, Object>();
		List<Map<String, String>> lstReq = new ArrayList<Map<String, String>>();
		LOGGER.severe("requests " + gson.toJson(requests));
		if (requests != null && requests.length != 0) {
			for (int i = requests.length - 1; i >= 0; i--) {
				if (requests[i] != null) {
					String feedback = requests[i].getCustomerFeedback();
					String cusName = requests[i].getCustomerName();
					//String date = requests[i].getDay();
					long datel = Long.parseLong(requests[i].getDay());
					DateFormat dfm = new SimpleDateFormat("MM-dd-yy");
					dfm.setTimeZone(TimeZone.getTimeZone("GMT-4"));
					String date = dfm.format(new Date(datel));
					HashMap<String, String> currReq = gson.fromJson(feedback,
							HashMap.class);
					if (currReq == null || currReq.get("feedback") == null
							|| currReq.get("feedback").equals("")) {
						continue;
					}
					currReq.put("customerName", cusName);
					currReq.put("date", date);
					lstReq.add(currReq);
				}
			}
		}
		rt.put("status", "success");
		rt.put("data", lstReq);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}

	@GET
	@Path("/test/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response testAPI(@PathParam("barcode") String barcode)
			throws DAOException {
		AmazonProductsDAO apd = new AmazonProductsDAO();
		ProductInfo pi = new ProductInfo();
		// Reviews r = new Reviews();
		//
		// Map<String, Map<String, String>> sim = pi.getSimilarities(barcode);
		// LOGGER.info(gson.toJson(sim));
		// // TESTing traverse
		// for (String key : sim.keySet()) {
		// Map<String, String> m = sim.get(key);
		// for (String mk : m.keySet()) {
		// String v = m.get(mk);
		// }
		// }
		// List<Map<String, String>> reviews = r.getReviews(barcode);
		// if (reviews != null) {
		// // testing list traverse
		// for (Map<String, String> m : reviews) {
		// for (String mk : m.keySet()) {
		// String v = m.get(mk);
		// }
		// }
		// }
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(apd.getProducts())).build();
	}

}
