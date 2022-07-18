import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import de.fhws.biedermann.webshop.models.Article;
import de.fhws.biedermann.webshop.models.ArticleVersion;
import de.fhws.biedermann.webshop.models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CartTests
{
	@Test
	void testGetCart() throws IOException
	{
		System.out.println(TestHelper.getSession());
		HttpURLConnection con = TestHelper.doRequest("http://localhost:8080/api/cart/items", TestHelper.getSession(), "GET", null);
		Assertions.assertEquals(con.getResponseCode(), 200);

		String body = TestHelper.getBody(con);
		Assertions.assertNotNull(body);
		List<ArticleVersion> cartItems = new Genson().deserialize(body, new GenericType<>(){});
		Assertions.assertNotNull(cartItems);
	}

	@Test
	void testPostCart() throws IOException
	{
		ArticleVersion articleVersion = new ArticleVersion(3, 3, 20, 128, "red", "Galaxy Z Fold3", 1393.0, 1);
		HttpURLConnection con = TestHelper.doRequest("http://localhost:8080/api/cart/items", TestHelper.getSession(), "POST", new Genson().serialize(articleVersion));
		Assertions.assertEquals(con.getResponseCode(), 204);
	}
}
