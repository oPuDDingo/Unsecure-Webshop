package de.fhws.biedermann.webshop.api;

import de.fhws.biedermann.webshop.DataHandler;
import de.fhws.biedermann.webshop.models.ArticleVersion;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("cart") public class CartService
{
	@Context protected UriInfo uriInfo;

	@Path("items") @GET @Produces(MediaType.APPLICATION_JSON) public Response getCartItems(
		@HeaderParam("sessionid") String session,
		@Context HttpServletRequest request)
	{
		//request.getHeaders( "sessionid" ).asIterator().forEachRemaining( System.out::println );
		if (session == null) return Response.status(401).build();
		return Response.ok(DataHandler.getCartItems(session)).build();
	}

	@Path("items") @POST @Consumes(MediaType.APPLICATION_JSON) public Response createCartItem(
		@Context final UriInfo uriInfo,
		@HeaderParam("sessionid") String session,
		final ArticleVersion articleVersion)
	{
		if (session == null) return Response.status(401).build();
		DataHandler.createCartItem(articleVersion, session);
		return Response.ok().build();
	}

	@Path("items/{id}") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyCartItem(
		@PathParam("id") final int id, final ArticleVersion articleVersion,
		@HeaderParam("sessionid") String session)
	{
		if (session == null) return Response.status(401).build();
		DataHandler.modifyCartItem(id, articleVersion);
		return Response.ok(articleVersion).build();
	}

	@Path("items/{id}") @DELETE @Consumes(MediaType.APPLICATION_JSON) public Response deleteCartItem(
		@PathParam("id") final int id,
		@HeaderParam("sessionid") String session)
	{
		if (session == null) return Response.status(401).build();
		DataHandler.deleteCartItem(session, id);
		return Response.noContent().build();
	}
}
