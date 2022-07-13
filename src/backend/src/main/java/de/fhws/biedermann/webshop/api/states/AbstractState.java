package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.models.IModel;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

public abstract class AbstractState
{

	final String session;
	final String uuid;
	final IModel modelToWorkWith;
	final long idToWorkWith;
	Object responseBody;
	final boolean validInputData;
	final String invalidInputDataMessage;

	public AbstractState( final AbstractStateBuilder builder ){
		if ( !builder.validInputData ) throw new NotAuthorizedException( builder.invalidInputDataMessage );

		this.session = builder.session;
		this.uuid = builder.uuid;
		this.modelToWorkWith = builder.modelToWorkWith;
		this.idToWorkWith = builder.idToWorkWith;
		this.responseBody = builder.responseBody;
		this.validInputData = true;
		this.invalidInputDataMessage = builder.invalidInputDataMessage;
	}

	public Response ok(){
		return Response.ok( this.responseBody ).build();
	}

	public Response noContent(){
		return Response.noContent().build();
	}

	abstract void lookForFlaw();

}
