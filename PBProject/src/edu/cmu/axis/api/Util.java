package edu.cmu.axis.api;

import java.sql.Timestamp;
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
}
