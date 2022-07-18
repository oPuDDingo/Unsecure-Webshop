import com.owlike.genson.Genson;
import de.fhws.biedermann.webshop.models.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ArticleTests
{
	@Test void testGetArticles() throws IOException
	{
		int status = TestHelper.doRequest("http://localhost:8080/api/articles").getResponseCode();
		Assertions.assertEquals(status, 200);
	}

	@Test void testGetSingleArticle() throws IOException
	{
		HttpURLConnection con = TestHelper.doRequest("http://localhost:8080/api/articles/1");
		Assertions.assertEquals(con.getResponseCode(), 200);

		String json = TestHelper.getBody(con);
		System.out.println(json);
		Assertions.assertNotNull(json);
		Article article = new Genson().deserialize(json, Article.class);
		Assertions.assertEquals(article.getModelName(), "Galaxy Z Fold3");
		Assertions.assertNotNull(article);

	}
}
