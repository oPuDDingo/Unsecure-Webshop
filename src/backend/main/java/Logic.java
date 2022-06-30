package backend.main.java;

import backend.main.java.database.AuthorizationType;
import backend.main.java.database.DataAccessShopDatabase;
import backend.main.java.models.ArticleVersion;
import backend.main.java.models.Order;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;
import java.security.SecureRandom;

public class Logic
{
	static DataAccessShopDatabase Database = new DataAccessShopDatabase();

	public static Response login(final String mail, final String password, final String ip)
	{
		SecurityBreachDetection.detectUserSecurityBreach( mail, password, ip );
		AuthorizationType session = Database.checkAuthData(mail, password);
		if ( session == AuthorizationType.FALSE_PASSWORD || session == AuthorizationType.FALSE_USER )
		{
			if ( LogicAdminPanel.level < 2 )
			{
				if ( session == AuthorizationType.FALSE_PASSWORD )
					throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).entity( "Das " +
						"Passwort stimmt nicht mit dem Benutzer überein! Versuche es erneut." ).build() );
				else
					throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).entity( "Der " +
						"Benutzer existiert nicht! Versuche es erneut." ).build() );
			}
			else
				throw new WebApplicationException( Response.status( Response.Status.BAD_REQUEST ).entity( "Benutzer" +
					" oder Passwort falsch! Versuche es erneut." ).build() );
		}
		if (session == AuthorizationType.AUTHORIZED_DUMMY_USER ) FlawHandler.guessUserLogin( ip );
		String sessionID = createSessionId();
		Database.postSession(sessionID, mail, ip);
		return Response.ok(sessionID).header("sessionid", sessionID).build();
	}

	public static Response logout(String session) {
		Database.deleteSession(session);
		return Response.ok().build();
	}

	static String createSessionId()
	{
		SecureRandom GENERATOR = new SecureRandom();
		String session = "";
		do {
		byte[] token = new byte[32];
		GENERATOR.nextBytes(token);
		session = Base64.getEncoder().encodeToString(token);
		}
		while (Database.sessionExists(session));
		return session;
	}


	public static double computePrice(List<ArticleVersion> articles)
	{
		double sum = 0;
		for (ArticleVersion article : articles)
		{
			sum += article.getAmount() * article.getQuantity();
		}
		return sum;
	}

	public static boolean preventCheckXSS(int level, String request)
	{
		if (level == 1)
		{
			request = request.replace("script", "");
			return request.contains("<script>");
		}
		else if (level == 2)
		{
			request = request.replace("<script>", "");
			return request.contains("<script>");
		} else if (level == 3) {
			return request.contains("<img src=") && request.contains("onerror=");
		}
		return false;
	}


	public static void checkPrice(Order order, String remoteAddr)
	{
		List<ArticleVersion> articles = order.getSpecifiedItems();
		System.out.println(computePrice(articles));
		System.out.println(order.getAmount());
		if (computePrice(articles) != order.getAmount()) {
			FlawHandler.priceOrder(remoteAddr);
		}
	}
}
