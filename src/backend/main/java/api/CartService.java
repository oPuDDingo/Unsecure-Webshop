package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.ArticleVersion;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("cart") public class CartService
{
	@Context protected UriInfo uriInfo;

	@Path("items") @GET @Produces(MediaType.APPLICATION_JSON)  public Response getCartItems()
	{ return Response.ok(DataHandler.getCartItems()).build(); }

	@Path("items") @POST @Consumes(MediaType.APPLICATION_JSON)  public Response createCartItem(
		@Context final UriInfo uriInfo,
		final ArticleVersion articleVersion
	)
	{
		int id = DataHandler.createCartItem(articleVersion);
		URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
		return Response.created(location).build();
	}

	@Path("items/{id}")@PUT @Consumes(MediaType.APPLICATION_JSON)  public Response modifyCartItem(
		@PathParam("id") final int id, final ArticleVersion articleVersion)
	{
		DataHandler.modifyCartItem(id, articleVersion);
		return Response.ok(articleVersion).build();
	}

	@Path("items/{id}")@DELETE @Consumes(MediaType.APPLICATION_JSON)  public Response deleteCartItem(@PathParam("id") final int id)
	{
		DataHandler.deleteCartItem(id);
		return Response.noContent().build();
	}
}
