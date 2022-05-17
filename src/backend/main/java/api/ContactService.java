package backend.main.java.api;

import backend.main.java.models.Contact;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("contact") public class ContactService
{
	@POST @Consumes(MediaType.APPLICATION_JSON) public Response createContact(
		final Contact contact
	) throws URISyntaxException
	{
		// create data in database

		return Response.created(new URI("placeholder")).build();
	}
}
