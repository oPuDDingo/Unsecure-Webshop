package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.models.IModel;
import de.fhws.biedermann.webshop.utils.ErrorMessages;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractStateBuilder
{

	String session = "";
	String uuid = "";
	Object responseBody;
	IModel modelToWorkWith;
	int idToWorkWith;
	boolean validInputData = true;
	String invalidInputDataMessage;

	UriInfo uriInfo;


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
		if (model == null) {
			throw new BadRequestException( "" );
		}
		this.modelToWorkWith = model;
		return this;
	}

	public AbstractStateBuilder withId( final int id ){
		this.idToWorkWith = id;
		return this;
	}

	public AbstractStateBuilder defineResponseBody( final Object responseBody ){
		this.responseBody = responseBody;
		return this;
	}

	public AbstractStateBuilder withUriInfo( final UriInfo uriInfo ){
		this.uriInfo = uriInfo;
		return this;
	}

	public AbstractStateBuilder withNotNull( final Object... objects ){
		if ( Arrays.stream( objects ).anyMatch( Objects::isNull ) ) throw new BadRequestException( "" );
		return this;
	}

}
