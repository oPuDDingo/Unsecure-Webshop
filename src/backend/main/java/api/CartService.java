package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.ArticleVersion;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("cart") public class CartService
{
	@Context protected UriInfo uriInfo;

	@Path("items") @GET @Produces(MediaType.APPLICATION_JSON) public Response getCartItems(
		@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		return Response.ok(DataHandler.getCartItems(session)).build();
	}

	@Path("items") @POST @Consumes(MediaType.APPLICATION_JSON) public Response createCartItem(
		@Context final UriInfo uriInfo, @CookieParam("sessionID") final String session,
		final ArticleVersion articleVersion)
	{
		if (session == null) return Response.status(403).build();
		DataHandler.createCartItem(articleVersion, session);
		return Response.ok().build();
	}

	@Path("items/{id}") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyCartItem(
		@PathParam("id") final int id, final ArticleVersion articleVersion,
		@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		DataHandler.modifyCartItem(id, articleVersion);
		return Response.ok(articleVersion).build();
	}

	@Path("items/{id}") @DELETE @Consumes(MediaType.APPLICATION_JSON) public Response deleteCartItem(
		@PathParam("id") final int id,
		@CookieParam("sessionID") final String session)
	{
		if (session == null) return Response.status(403).build();
		DataHandler.deleteCartItem(id);
		return Response.noContent().build();
	}
}
