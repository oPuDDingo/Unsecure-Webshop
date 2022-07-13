package de.fhws.biedermann.webshop.api;

import de.fhws.biedermann.webshop.DataHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pictures") public class PictureService
{
	@Path("{id}") @GET @Produces(MediaType.TEXT_PLAIN) public Response getPictures(
		@PathParam("id") final int id,
		@QueryParam( "uuid" ) final String uuid
	)
	{
		String picture = DataHandler.getPicture(id);
		return Response.ok(picture).build();
	}
}
