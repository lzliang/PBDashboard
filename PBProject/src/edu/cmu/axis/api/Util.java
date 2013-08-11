package edu.cmu.axis.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

public class Util {
	static Gson gson = new Gson();
	public static Response returnError(String msg, Exception e){
		Map<String, String> rt = new HashMap<String,String>();
		rt.put("status", "error");
		if(msg == null){
			rt.put("reason", e.getMessage());
		}else{
			rt.put("reason", msg);
		}
		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}
}
