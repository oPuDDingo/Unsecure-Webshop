package backend.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserTests
{
	HttpURLConnection doRequest(String url_param) {return doRequest(url_param, null, "GET");}
	HttpURLConnection doRequest(String url_param, String session, String method)
	{
		try
		{
			URL url = new URL(url_param);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			if (session != null) con.setRequestProperty("sessionid", session);
			con.setRequestMethod(method);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	String getSession() {
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

	@Test
	void testFlaws() throws IOException
	{
		int status = doRequest("http://localhost:8080/api/flaws").getResponseCode();
		Assertions.assertEquals(status, 200);
	}

	@Test
	void testLogin() throws IOException
	{
		HttpURLConnection request;
		request = doRequest("http://localhost:8080/api/user/login");
		Assertions.assertEquals(request.getResponseCode(), 400);
		request = doRequest("http://localhost:8080/api/user/login?"
			+ "mail=Test1@test.de&"
			+ "password=123456789");
		Assertions.assertEquals(request.getResponseCode(), 200);
	}

	@Test
	void testLoginCookie()
	{
		Assertions.assertNotNull(getSession());
	}

	@Test
	void testLogout() throws IOException
	{
		HttpURLConnection request;
		request = doRequest("http://localhost:8080/api/user/logout/", getSession(), "POST");
		Assertions.assertEquals(request.getResponseCode(), 200);
		request = doRequest("http://localhost:8080/api/user/logout/", null, "POST");
		Assertions.assertEquals(request.getResponseCode(), 401);
	}

	@Test
	void testNewsletter() throws IOException
	{
		HttpURLConnection request;
		request = doRequest("http://localhost:8080/api/user/newsletter", getSession(), "POST");
		Assertions.assertEquals(request.getResponseCode(), 204);
		request = doRequest("http://localhost:8080/api/user/newsletter",getSession(), "DELETE");
		Assertions.assertEquals(request.getResponseCode(), 204);
		request = doRequest("http://localhost:8080/api/user/newsletter", null, "POST");
		Assertions.assertEquals(request.getResponseCode(), 401);
		request = doRequest("http://localhost:8080/api/user/newsletter",null, "DELETE");
		Assertions.assertEquals(request.getResponseCode(), 401);
	}
}
