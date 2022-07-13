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
		this.execute();
		if (this.responseBody == null) return Response.status( Response.Status.NOT_FOUND ).build();
		return Response.ok( this.responseBody ).build();
	}

	public Response create(){
		this.execute();
		return Response.status( Response.Status.CREATED ).build();
	}

	public Response noContent(){
		this.execute();
		return Response.noContent().build();
	}

	private void execute() {
		lookForFlaw();
	}

	abstract void lookForFlaw();

}
