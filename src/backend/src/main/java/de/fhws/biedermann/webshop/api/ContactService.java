package de.fhws.biedermann.webshop.api;

import de.fhws.biedermann.webshop.DataHandler;
import de.fhws.biedermann.webshop.FlawHandler;
import de.fhws.biedermann.webshop.VulnerabilityCheck;
import de.fhws.biedermann.webshop.models.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("contact") public class ContactService
{
	@Context protected UriInfo uriInfo;

	@POST @Consumes(MediaType.APPLICATION_JSON) public Response createContact(
		final Contact contact,
		@HeaderParam( "uuid" ) final String uuid,
		@Context UriInfo uriInfo,
		@Context HttpServletRequest request
	)
	{
		VulnerabilityCheck vc = new VulnerabilityCheck();
		if (!contact.getMail().contains("@")) {
			FlawHandler.emailWithoutAt( uuid );
		}
		if(vc.checkBlindSqlInjection(contact.getMessage())){
			FlawHandler.blindSqlInjection(uuid);
		}
		DataHandler.createContact(contact);
		return Response.ok().build();
	}
}
