package de.fhws.biedermann.webshop;

import de.fhws.biedermann.webshop.database.DataAccessAdminPanel;
import de.fhws.biedermann.webshop.database.DataAccessShopDatabase;
import de.fhws.biedermann.webshop.database.AuthorizationType;
import de.fhws.biedermann.webshop.models.ArticleVersion;
import de.fhws.biedermann.webshop.models.Order;
import de.fhws.biedermann.webshop.utils.MyKeyGenerator;
import org.json.JSONObject;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Base64;
import java.security.SecureRandom;
import java.util.List;

public class Logic
{
	static DataAccessShopDatabase Database = new DataAccessShopDatabase();
	static DataAccessAdminPanel daap = new DataAccessAdminPanel();

	public static Response login(final String mail, final String password, final String ip)
	{
		SecurityBreachDetection.detectLoginSecurityBreach( ip );
		SecurityBreachDetection.detectDummyUserInHtmlSecurityBreach( mail, password, ip ); //Fixed by Levin
		AuthorizationType session = Database.checkAuthData(mail, password);
		if ( session == AuthorizationType.FALSE_PASSWORD || session == AuthorizationType.FALSE_USER )
		{
			if ( LogicAdminPanel.getInstance().level < 2 )
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
		} else if (session == AuthorizationType.AUTHORIZED_DUMMY_USER) {
			FlawHandler.guessUserLogin( ip );
		}
		String sessionID = createSessionId();
		Database.postSession(sessionID, mail, ip);
		return Response.ok(sessionID).header("sessionid", sessionID).build();
	}

	public static Response logout(String session) {
		Database.deleteSession(session);
		return Response.ok().build();
	}

	public static String createSessionId( )
	{
		SecureRandom GENERATOR = new SecureRandom();
		String session = "";
		do {
			byte[] token = new byte[32];
			GENERATOR.nextBytes(token);
			session = Base64.getEncoder().encodeToString(token);
		} while (Database.sessionExists(session));
		return session;
	}


	public static double computePrice( List<ArticleVersion> articles)
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


	public static void checkPrice( Order order, String remoteAddr)
	{
		List<ArticleVersion> articles = order.getSpecifiedItems();
		if (computePrice(articles) != order.getAmount()) {
			FlawHandler.priceOrder(remoteAddr);
		}
	}

	public static Key createNewUser() {
		// toDo if a uuid is given, store this one in the database
		final Key uuid = MyKeyGenerator.getNewKey();
		if (uuid == null) {
			// toDo: handle on frontend
			throw new InternalServerErrorException( "" );
		}
		daap.lookForClient( Base64.getEncoder().encodeToString(uuid.getEncoded() ) );
		return uuid;
	}

	public static JSONObject getDummyDataForInternApi(){
		JSONObject response = new JSONObject(  );
		response.put( "backup_version", "1.0" );
		response.put( "current_version", "1.6" );
		return response;
	}
}
