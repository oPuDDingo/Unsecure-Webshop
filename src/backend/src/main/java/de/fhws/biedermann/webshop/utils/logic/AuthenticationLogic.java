package de.fhws.biedermann.webshop.utils.logic;

import de.fhws.biedermann.webshop.database.AuthorizationType;
import de.fhws.biedermann.webshop.database.DataAccessAdminPanel;
import de.fhws.biedermann.webshop.database.DataAccessShopDatabase;
import de.fhws.biedermann.webshop.utils.LogicAdminPanel;
import de.fhws.biedermann.webshop.utils.SecurityBreachDetection;
import de.fhws.biedermann.webshop.utils.handler.FlawHandler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthenticationLogic
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

}
