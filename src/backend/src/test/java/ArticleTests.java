package backend.test.java;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static backend.test.java.TestHelper.*;

public class ArticleTests
{
	@Test
	void testGetArticles() throws IOException
	{
		int status = doRequest("http://localhost:8080/api/articles").getResponseCode();
		Assertions.assertEquals(status, 200);
	}

	@Test
	void testGetSingleArticle() throws IOException
	{
		int status = doRequest("http://localhost:8080/api/articles/1").getResponseCode();
		Assertions.assertEquals(status, 200);
	}
}
