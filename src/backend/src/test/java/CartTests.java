package backend.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static backend.test.java.TestHelper.*;

public class CartTests
{
	@Test
	void testGetCart() throws IOException
	{
		int status = doRequest("http://localhost:8080/api/cart/items", getSession(), "GET", null).getResponseCode();
		Assertions.assertEquals(status, 200);
	}

	@Test
	void testPostCart() throws IOException
	{
		String json = "{\n" + "        \"amount\": 1,\n" + "        \"articleNumber\": 1,\n"
			+ "        \"color\": \"red\",\n" + "        \"gbSize\": 128,\n" + "        \"id\": 1,\n"
			+ "        \"name\": \"Iphone\",\n" + "        \"picture\": \"picture\",\n" + "        \"quantity\": 1\n"
			+ "}";
		int status = doRequest("http://localhost:8080/api/cart/items", getSession(), "POST", json).getResponseCode();
		Assertions.assertEquals(status, 200);
	}
}
