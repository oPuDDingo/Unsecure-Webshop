package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.Picture;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pictures") public class PictureService
{
	@Path("{id}") @GET @Produces(MediaType.APPLICATION_JSON) public Response getPictures(
		@PathParam("id") final int id
	)
	{
		Picture picture = DataHandler.getPicture(id);
		return Response.ok(picture).build();
	}
}
