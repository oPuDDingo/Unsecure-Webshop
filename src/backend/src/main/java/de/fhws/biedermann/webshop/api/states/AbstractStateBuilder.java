package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.models.IModel;
import de.fhws.biedermann.webshop.utils.ErrorMessages;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.InternalServerErrorException;

public abstract class AbstractStateBuilder
{

	String session = "";
	String uuid = "";
	Object responseBody;
	IModel modelToWorkWith;
	long idToWorkWith;
	boolean validInputData = true;
	String invalidInputDataMessage;


	public AbstractState build(){
		throw new InternalServerErrorException(  );
	}

	public AbstractStateBuilder withSession( final String session ){
		this.session = session;
		if ( !this.validInputData ){
			return this;
		} else if ( StringUtils.isEmpty( uuid ) ) {
			this.invalidInputDataMessage = ErrorMessages.MISSING_SESSION;
			this.validInputData = false;
		}

		return this;
	}

	public AbstractStateBuilder withUuid( final String uuid ){
		this.uuid = uuid;
		if ( !this.validInputData ){
			return this;
		} else if ( StringUtils.isEmpty( uuid ) ) {
			this.invalidInputDataMessage = ErrorMessages.MISSING_UUID;
			this.validInputData = false;
		}

		return this;
	}

	public AbstractStateBuilder withModel( final IModel model ){
		this.modelToWorkWith = model;
		return this;
	}

	public AbstractStateBuilder defineResponseBody( final Object responseBody ){
		this.responseBody = responseBody;
		return this;
	}

}
