package api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("image") public class ImageService
{
	@GET @Produces(MediaType.APPLICATION_JSON) public Response getImage(
		@QueryParam("name") final String name
	)
	{
		// find in database by name
		String image = "example image";
		return Response.ok(image).build();
	}
}
