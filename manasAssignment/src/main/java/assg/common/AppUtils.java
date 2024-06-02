package assg.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import assg.Main;

public class AppUtils {

	public static String readFile(String filePath) {
		String jsonString = null;
		try {
			jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			Main.infoLog.error("Error while reading file from path : "+ filePath + "\nException : "+ e);
		}
		return jsonString;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	public String getAPIData(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// Process JSON response
			Main.infoLog.info("Data Fetched from Url : " + url + "\t is : " + content.toString());

			return content.toString();

		} catch (Exception e) {
			Main.infoLog.error("Error while fetching data from apiUrl : "+ apiUrl+ " \nException : "+ e);
		}
		return null;

	}

	public static Map<String, String> getReqParam(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		Map<String, String> reqParam = new HashMap<String, String>();

		Iterator<String> i = params.keySet().iterator();
		while (i.hasNext()) {
			String key = (String) i.next();
			String value = ((String[]) params.get(key))[0];
			reqParam.put(key, value);
		}

		return reqParam;
	}
}
