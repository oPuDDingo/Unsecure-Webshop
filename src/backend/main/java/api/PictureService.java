package backend.main.java.api;

import backend.main.java.DataHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pictures") public class PictureService
{
	@Path("{id}") @GET @Produces(MediaType.TEXT_PLAIN) public Response getPictures(
		@PathParam("id") final int id
	)
	{
		String picture = DataHandler.getPicture(id);
		return Response.ok(picture).build();
	}
}
