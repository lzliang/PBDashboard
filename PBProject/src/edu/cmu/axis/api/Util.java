package edu.cmu.axis.api;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {
	static Gson gson = new GsonBuilder().setPrettyPrinting()
			.disableHtmlEscaping().create();;

	public static Response returnError(String msg, Exception e) {
		Map<String, String> rt = new HashMap<String, String>();
		rt.put("status", "error");
		if (msg == null) {
			rt.put("reason", e.getMessage());
		} else {
			rt.put("reason", msg);
		}
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(gson.toJson(rt)).build();
	}

	public static long trimTimeStampToDay(long currentMilisec) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(currentMilisec);
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		return calendar.getTimeInMillis();
	}
	
	public static String randomLocation() {
		StringBuilder sb = new StringBuilder();
		sb.append((int) (Math.random() * 5.0));
		sb.append((int) (Math.random() * 10.0));
		sb.append("-");
		int random = (int) (Math.random() * 12.0);
		sb.append((char) ('A' + random));
		sb.append((int) (Math.random() * 10.0));
		sb.append((int) (Math.random() * 10.0));
		return sb.toString();
	}

	public static void main(String[] args){
		//"numberOfServedRequests":10,"Day":"1376524800000"},{"numberOfServedRequests":26,"Day":"1376481600000"},{"numberOfServedRequests":13,"Day":"1376568000000"},{"numberOfServedRequests":2,"Day":"1376654400000"},{"numberOfServedRequests":30,"Day":"1376611200000"},{"numberOfServedRequests":10,"Day":"1376438400000"}
		String[] s = {"1376524800000","1376481600000","1376568000000","1376654400000","1376611200000","1376438400000"};
		Arrays.sort(s);
		System.out.println(Arrays.toString(s));
	}
}
