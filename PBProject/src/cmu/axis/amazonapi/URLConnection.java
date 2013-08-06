package cmu.axis.amazonapi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class URLConnection {

	public static String getData(String url) {
		HttpURLConnection connection = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		String line = null;
		String data = null;
		URL address = null;

		try {
			// use HttpURLConnection to get connection with the website
			address = new URL(url);
			connection = null;
			connection = (HttpURLConnection) address.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			connection.connect();

			// recieve data from server

			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line.trim() + '\n');
			}
			data = sb.toString().trim();
			return data;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			// close connection
			connection.disconnect();
			br = null;
			sb = null;
			connection = null;
		}
	}

}
