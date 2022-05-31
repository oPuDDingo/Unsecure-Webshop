package backend.main.java.api;

import backend.main.java.DataHandler;
import backend.main.java.models.Order;

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
		@CookieParam("sessionID") final String session
	)
	{
		List<Order> orders = DataHandler.getOrders(session);
		return Response.ok(orders).build();
	}

	@GET @Produces(MediaType.APPLICATION_JSON) @Path("{id}") public Response getOrderbyID(
		@PathParam("id") final int id
	)
	{
		Order order = DataHandler.getOrder(id);
		return Response.ok(order).build();
	}

	@POST@Consumes(MediaType.APPLICATION_JSON) public Response createOrder(
		@Context UriInfo uriInfo,
		@QueryParam("cleanUpWishlist") final boolean cleanup,
		@CookieParam("sessionID") final String session,
		final Order order
	)
	{
		int orderNumber = DataHandler.createOrder(order, session, cleanup);
		URI location = uriInfo.getAbsolutePathBuilder().path("id").path(String.valueOf(orderNumber)).build();
		return Response.created(location).build();
	}
}
