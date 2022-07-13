package backend.test.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test
{
	static HttpURLConnection doRequest(String url_param) {return doRequest(url_param, null, "GET", false);}
	static HttpURLConnection doRequest(String url_param, String session, String method, boolean json)
	{
		try
		{
			URL url = new URL(url_param);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			if (session != null) con.setRequestProperty("sessionid", session);
			if (json) con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod(method);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static String getSession() {
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
	public static void main(String[] args) throws IOException, InterruptedException
	{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("http://localhost:8080/api/user"))
			.header("sessionid", getSession())
			.build();
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());
	}
}
