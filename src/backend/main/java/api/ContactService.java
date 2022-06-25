package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.FlawHandler;
import backend.main.java.VulnerabilityCheck;
import backend.main.java.models.Contact;

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
		@Context UriInfo uriInfo,
		final Contact contact,
		@Context HttpServletRequest request
	)
	{
		VulnerabilityCheck vc = new VulnerabilityCheck();
		if (!contact.getMail().contains("@")) {
			FlawHandler.emailWithoutAt(request.getRemoteAddr());
		}
		if(vc.checkBlindSqlInjection(contact.getMessage())){
			FlawHandler.blindSqlInjection(request.getRemoteAddr());
		}
		DataHandler.createContact(contact);
		return Response.ok().build();
	}
}
