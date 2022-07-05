package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.Logic;
import backend.main.java.models.Order;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("orders") public class OrderService
{
	@Context UriInfo uriInfo;

	@GET @Produces(MediaType.APPLICATION_JSON) public Response getOrders(
		@HeaderParam("sessionid") String session
	)
	{
		if (session == null) return Response.status(401).build();
		List<Order> orders = DataHandler.getOrders(session);
		return Response.ok(orders).build();
	}

	@GET @Produces(MediaType.APPLICATION_JSON) @Path("{id}") public Response getOrderByID(
		@PathParam("id") final int id
	)
	{
		Order order = DataHandler.getOrder(id);
		return Response.ok(order).build();
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
		Logic.checkPrice(order, uuid);
		int orderNumber = DataHandler.createOrder(order, session, cleanup);
		URI location = uriInfo.getAbsolutePathBuilder().path("id").path(String.valueOf(orderNumber)).build();
		return Response.created(location).build();
	}
}
