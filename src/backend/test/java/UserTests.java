package backend.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static backend.test.java.TestHelper.*;

public class UserTests
{
	@Test
	void getUser() throws IOException {
		HttpURLConnection con = doRequest("http://localhost:8080/api/user", getSession(), "GET");
		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		System.out.println(content);
		in.close();
		con.disconnect();
		Assertions.assertEquals(content, "test");
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
