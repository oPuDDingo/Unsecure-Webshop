package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.database.DataAccessAdminPanel;
import de.fhws.biedermann.webshop.utils.authentication.MyKeyGenerator;

import javax.ws.rs.InternalServerErrorException;
import java.security.Key;
import java.util.Base64;

public class UserState extends AbstractState
{
	static DataAccessAdminPanel daap = new DataAccessAdminPanel();

	private UserState( AbstractStateBuilder builder )
	{
		super( builder );
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

	@Override void lookForFlaw( )
	{

	}

	public static class Builder extends AbstractStateBuilder{
		@Override
		public AbstractState build(){
			return new UserState( this );
		}
	}
}
