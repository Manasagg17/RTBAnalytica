package assg.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import assg.Main;

public interface GetStringFromInputStream {

	public default String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
			Main.infoLog.error("Error in Converting Stream to String : " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					Main.infoLog.error("Error in Converting Stream to String : " + e);
				}
			}
		}

		return sb.toString();
	}

}
