package de.fhws.biedermann.webshop.api.services;

import de.fhws.biedermann.webshop.utils.handler.DataHandler;
import de.fhws.biedermann.webshop.models.Order;
import de.fhws.biedermann.webshop.api.states.OrderState;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("orders") public class OrderService
{
	@Context UriInfo uriInfo;

	@GET @Produces(MediaType.APPLICATION_JSON) public Response getOrders(
		@HeaderParam("sessionid") String session
	)
	{
		if (session == null) return Response.status(401).build();
		return Response.ok(DataHandler.getOrders(session)).build();
	}

	@GET @Produces(MediaType.APPLICATION_JSON) @Path("{id}") public Response getOrderByID(
		@PathParam("id") final int id
	)
	{
		return Response.ok(DataHandler.getOrder(id)).build();
	}

	@POST @Consumes(MediaType.APPLICATION_JSON) public Response createOrder(
		@Context UriInfo uriInfo,
		@QueryParam("cleanUpWishlist") final boolean cleanup,
		@HeaderParam("sessionid") String session,
		@HeaderParam( "uuid" ) final String uuid,
		final Order order, @Context HttpServletRequest request
	)
	{
		if (order == null) return Response.status(400).build();
		if (session == null) return Response.status(401).build();
		OrderState.checkPrice(order, uuid);
		int orderNumber = DataHandler.createOrder(order, session, cleanup);
		URI location = uriInfo.getAbsolutePathBuilder().path("id").path(String.valueOf(orderNumber)).build();
		return Response.created(location).build();
	}
}
