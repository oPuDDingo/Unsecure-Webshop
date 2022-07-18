package backend.test.java;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHelper
{
	public static HttpURLConnection doRequest(String url_param) {return doRequest(url_param, null, "GET", null);}

	public static HttpURLConnection doRequest(String url_param, String session, String method) {return doRequest(url_param, null, "GET", null);}

	public static HttpURLConnection doRequest(String url_param, String session, String method, String json)
	{
		try
		{
			URL url = new URL(url_param);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			if (session != null) con.setRequestProperty("sessionid", session);
			if (json != null) {
				con.addRequestProperty("Content-Type", "application/json");
				con.setDoOutput(true);
				con.setRequestMethod(method);
				OutputStream os = con.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				osw.write(json);
				osw.flush();
				osw.close();
				os.close();  //don't forget to close the OutputStream
			}

			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSession() {
		try
		{
			HttpURLConnection con = doRequest("http://localhost:8080/api/user/login?"
				+ "mail=Test1@test.de&"
				+ "password=123456789");
			String cookiesHeader = con.getHeaderField("sessionid");
			return HttpCookie.parse(cookiesHeader).get(0).getValue();
		} catch (Exception e) {
			return null;
		}
	}
}
