package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.models.Contact;
import de.fhws.biedermann.webshop.utils.VulnerabilityCheck;
import de.fhws.biedermann.webshop.utils.handler.FlawHandler;

import javax.ws.rs.BadRequestException;

public class ContactState extends AbstractState
{
	private ContactState( AbstractStateBuilder builder )
	{
		super( builder );
	}

	@Override void lookForFlaw( )
	{
		if ( this.modelToWorkWith instanceof Contact ){
			throw new BadRequestException( "Invalid model was given!" );
		}
		Contact contact = ( Contact ) this.modelToWorkWith;
		VulnerabilityCheck vc = new VulnerabilityCheck();
		if ( !contact.getMail().contains("@") ) {
			FlawHandler.emailWithoutAt( uuid );
		}
		if(vc.checkBlindSqlInjection(contact.getMessage())){
			FlawHandler.blindSqlInjection(uuid);
		}
	}

	public static class Builder extends AbstractStateBuilder{
		@Override
		public AbstractState build(){
			return new ContactState( this );
		}
	}
}
